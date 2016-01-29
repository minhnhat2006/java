package com.sharpinu.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sharpinu.config.ConfigManager;
import com.sharpinu.constant.OurPracticeConst;
import com.sharpinu.constant.SQLQueryConst;
import com.sharpinu.constant.StringConst;
import com.sharpinu.persist.domain.OurPractice;
import com.sharpinu.persist.domain.OurPracticeCategory;
import com.sharpinu.persist.repositories.OurPracticeCategoryRepo;
import com.sharpinu.persist.repositories.OurPracticeRepo;
import com.sharpinu.util.DateUtil;
import com.sharpinu.util.Lib;
import com.sharpinu.web.bean.OurPracticeBean;
import com.sharpinu.web.bean.OurPracticeCategoryBean;
import com.sharpinu.web.bean.OurPracticePageBean;

@Service
public class OurPracticeService extends BaseService implements IOurPracticeService {

	@Autowired
	@Qualifier("ourPracticeCategoryRepo")
	private OurPracticeCategoryRepo ourPracticeCategoryRepo;

	@Autowired
	@Qualifier("ourPracticeRepo")
	private OurPracticeRepo ourPracticeRepo;

	public OurPracticePageBean loadDefault() {

		try {
			PageRequest pageRequest = new PageRequest(0, OurPracticeConst.DEFAULT_POST_PAGE_SIZE, Sort.Direction.DESC, "createdDate");
			Page<OurPractice> page = this.findOurPracticesByContent(null, pageRequest);
			OurPracticePageBean ourPracticePageBean = this.buildOurPracticePageBean(page.getContent().get(0).getOurPracticeId(), page);

			return ourPracticePageBean;

		} catch (Exception e) {
			throw new RuntimeException("Failed to load default ourPractice page", e);
		}
	}

	public OurPracticePageBean loadSelectedOurPracticeAndOurPracticesByOurPracticeCategory(Integer ourPracticeId, Integer ourPracticeCategoryId, Integer pageIndex) {
		try {
			if (pageIndex == null) {
				pageIndex = 1;
			}
			PageRequest pageRequest = new PageRequest(pageIndex - 1, OurPracticeConst.DEFAULT_CATEGORY_POST_PAGE_SIZE, Sort.Direction.DESC, "createdDate");
			OurPracticeCategory ourPracticeCategory = ourPracticeCategoryRepo.findOne(ourPracticeCategoryId);
			Page<OurPractice> page = ourPracticeRepo.findByOurPracticeCategory(ourPracticeCategory, pageRequest);
			OurPracticePageBean ourPracticePageBean = buildOurPracticePageBean(ourPracticeId, page);

			return ourPracticePageBean;
		} catch (Exception e) {
			throw new RuntimeException(String.format("Failed to load ourPractice[ourPracticeId = %s, ourPracticeCategoryId = %s, pageIndex = %s]", ourPracticeId, ourPracticeCategoryId, pageIndex), e);
		}
	}

	public OurPracticePageBean loadAllOurPracticesAndCategories(Integer pageIndex) {
		try {
			OurPracticePageBean ourPracticePageBean = new OurPracticePageBean();
			PageRequest pageRequest = new PageRequest(pageIndex - 1, OurPracticeConst.DEFAULT_POST_PAGE_SIZE, Sort.Direction.DESC, "createdDate");
			Page<OurPractice> page = this.findOurPracticesByContent(null, pageRequest);
			List<OurPracticeBean> ourPracticeBeans = this.buildListOurPracticeBean(page.getContent(), ConfigManager.getNumOfTopWords());
			ourPracticePageBean.setOurPracticeBeans(ourPracticeBeans);
			ourPracticePageBean.setHasMoreOurPractice(page.getTotalPages() > 1);

			List<OurPracticeCategoryBean> ourPracticeCategoryBeans = this.getCategoriesHasOurPractice(StringConst.EMPTY, OurPracticeConst.DEFAULT_CATEGORY_PAGE_SIZE);
			ourPracticePageBean.setHasMoreOurPracticeCategory(ourPracticeCategoryBeans.size() >= OurPracticeConst.DEFAULT_CATEGORY_PAGE_SIZE);
			ourPracticePageBean.setOurPracticeCategoryBeans(ourPracticeCategoryBeans);

			return ourPracticePageBean;
		} catch (Exception e) {
			throw new RuntimeException(String.format("Failed to load all ourPractices and categories [pageIndex = %s]", pageIndex), e);
		}
	}

	public List<OurPracticeCategoryBean> getCategoriesHasOurPractice(String offset, int pageSize) {
		List<OurPracticeCategory> categories = ourPracticeCategoryRepo.findHasOurPracticeByOffset(offset, pageSize, SQLQueryConst.ORDER_ASC);
		List<OurPracticeCategoryBean> ourPracticeCategoryBeans = new ArrayList<OurPracticeCategoryBean>();
		for (OurPracticeCategory ourPracticeCategory : categories) {
			Integer ourPracticeCategoryId = ourPracticeCategory.getOurPracticeCategoryId();
			List<OurPractice> ourPractices = ourPracticeRepo.findLatestOurPracticeByCategory(ourPracticeCategoryId, new Date(), OurPracticeConst.DEFAULT_CATEGORY_POST_PAGE_SIZE);
			Boolean hasMoreOurPractice = ourPracticeRepo.countByCategory(ourPracticeCategoryId) > OurPracticeConst.DEFAULT_CATEGORY_POST_PAGE_SIZE;

			if (hasMoreOurPractice) {
				ourPractices = ourPractices.subList(0, OurPracticeConst.DEFAULT_CATEGORY_POST_PAGE_SIZE);
			}

			List<OurPracticeBean> ourPracticeBeans = buildListOurPracticeBean(ourPractices, 0);
			OurPracticeCategoryBean ourPracticeCategoryBean = new OurPracticeCategoryBean(ourPracticeCategoryId, ourPracticeCategory.getName());
			ourPracticeCategoryBean.setHasMoreOurPractice(hasMoreOurPractice);
			ourPracticeCategoryBean.setOurPracticeBeans(ourPracticeBeans);
			ourPracticeCategoryBeans.add(ourPracticeCategoryBean);
		}

		return ourPracticeCategoryBeans;
	}

	public OurPracticePageBean searchOurPracticesSortByCreated(String term) {
		try {
			OurPracticePageBean ourPracticePageBean = new OurPracticePageBean();
			Calendar now = Calendar.getInstance();
			now.set(9999, 1, 1);
			List<OurPractice> ourPractices = ourPracticeRepo.findByContentContainingOrderCreatedDesc(term, now.getTime(), OurPracticeConst.DEFAULT_CATEGORY_POST_PAGE_SIZE);
			if (ourPractices != null && !ourPractices.isEmpty()) {
				List<OurPracticeBean> ourPracticeBeans = this.buildListOurPracticeBean(ourPractices, 0);
				ourPracticePageBean.setOurPracticeBeans(ourPracticeBeans);
				ourPracticePageBean.setOurPracticeBean(ourPracticeBeans.get(0));

				int totalItems = ourPracticeRepo.countContentContainingOrderCreatedDesc(term, now.getTime());
				int totalPages = (int) Math.round(totalItems / OurPracticeConst.DEFAULT_CATEGORY_POST_PAGE_SIZE) + 1;

				ourPracticePageBean.setCurrentIndex(1);
				ourPracticePageBean.setBeginIndex(1);
				ourPracticePageBean.setEndIndex(totalPages >= OurPracticeConst.DEFAULT_PAGE_RANGE ? OurPracticeConst.DEFAULT_PAGE_RANGE : totalPages);
				ourPracticePageBean.setTotalPages(totalPages);
			}

			return ourPracticePageBean;
		} catch (Exception e) {
			throw new RuntimeException(String.format("Failed to load ourPractices[term = '%s']", term), e);
		}
	}

	public List<OurPracticeBean> searchOurPracticesSortByCreated(String term, Date createdDate) {
		List<OurPractice> ourPractices = ourPracticeRepo.findByContentContainingOrderCreatedDesc(term, createdDate, OurPracticeConst.DEFAULT_CATEGORY_POST_PAGE_SIZE);
		List<OurPracticeBean> ourPracticeBeans = this.buildListOurPracticeBean(ourPractices, 0);

		return ourPracticeBeans;
	}

	public OurPracticeBean getOurPractice(int ourPracticeId) {
		OurPractice ourPractice = ourPracticeRepo.findOne(ourPracticeId);

		if (ourPractice != null) {
			OurPracticeBean ourPracticeBean = this.buildOurPracticeBean(ourPractice, 0);

			if (ourPractice.getSlug() == null) {
				ourPractice.setSlug(Lib.toSlug(ourPractice.getSummary()));
				ourPracticeRepo.save(ourPractice);
			}

			return ourPracticeBean;

		} else {

			return null;
		}
	}

	public List<OurPracticeBean> getOurPracticesByOurPracticeCategory(int ourPracticeCategoryId, int pageIndex) {
		OurPracticeCategory ourPracticeCategory = ourPracticeCategoryRepo.findOne(ourPracticeCategoryId);

		if (ourPracticeCategory == null) {
			return null;
		}

		PageRequest pageRequest = new PageRequest(pageIndex - 1, OurPracticeConst.DEFAULT_CATEGORY_POST_PAGE_SIZE, Sort.Direction.DESC, "createdDate");
		Page<OurPractice> pageOurPractice = ourPracticeRepo.findByOurPracticeCategory(ourPracticeCategory, pageRequest);
		List<OurPracticeBean> ourPracticeBeans = this.buildListOurPracticeBean(pageOurPractice.getContent(), OurPracticeConst.DEFAULT_POST_WORDS_LIMIT);

		return ourPracticeBeans;
	}

	public List<OurPracticeBean> getOurPracticeByDate(Date offset, int pageSize) {
		List<OurPractice> ourPractices = ourPracticeRepo.findOurPracticesByDate(offset, pageSize);
		List<OurPracticeBean> ourPracticeBeans = this.buildListOurPracticeBean(ourPractices, OurPracticeConst.DEFAULT_POST_WORDS_LIMIT);

		return ourPracticeBeans;
	}

	private Page<OurPractice> findOurPracticesByContent(String term, Pageable pageable) {
		Page<OurPractice> page;
		if (StringUtils.isNotBlank(term)) {
			page = ourPracticeRepo.findByContentContaining(term, pageable);
		} else {
			page = ourPracticeRepo.findAll(pageable);
		}

		return page;
	}

	private OurPracticePageBean buildOurPracticePageBean(Integer ourPracticeId, Page<OurPractice> page) {
		OurPracticePageBean ourPracticePageBean = new OurPracticePageBean();
		List<OurPracticeBean> ourPracticeBeans = buildListOurPracticeBean(page.getContent(), 0);
		Map<Integer, OurPracticeBean> mapOurPracticeBeans = new HashMap<Integer, OurPracticeBean>();
		buildMapOurPracticeBean(ourPracticeBeans, mapOurPracticeBeans);
		OurPracticeBean displayOurPractice = findRelatedOurPracticeToDisplay(ourPracticeId, mapOurPracticeBeans);

		ourPracticePageBean.setOurPracticeBeans(ourPracticeBeans);
		ourPracticePageBean.setOurPracticeBean(displayOurPractice);
		ourPracticePageBean.setHasMoreOurPractice(page.getTotalPages() > 1);

		return ourPracticePageBean;
	}

	private List<OurPracticeBean> buildListOurPracticeBean(List<OurPractice> ourPractices, int numOfTopWords) {
		List<OurPracticeBean> ourPracticeBeans = new ArrayList<OurPracticeBean>();
		for (OurPractice p : ourPractices) {
			OurPracticeBean ourPracticeBean = buildOurPracticeBean(p, numOfTopWords);
			ourPracticeBeans.add(ourPracticeBean);
		}

		return ourPracticeBeans;
	}

	private OurPracticeBean buildOurPracticeBean(OurPractice ourPractice, int numOfTopWords) {
		OurPracticeBean ourPracticeBean = new OurPracticeBean();
		ourPracticeBean.setOurPracticeId(ourPractice.getOurPracticeId());
		ourPracticeBean.setSummary(ourPractice.getSummary());
		ourPracticeBean.setSlug(ourPractice.getSlug());
		ourPracticeBean.setCreatedDate(ourPractice.getCreatedDate());
		ourPracticeBean.setUpdatedDate(DateUtil.convertDateTimeToString(ourPractice.getCreatedDate().getTime()));

		if (numOfTopWords > 0) {
			ourPracticeBean.setContent(Lib.getStringLimitWords(ourPractice.getContent(), numOfTopWords));
		} else {
			ourPracticeBean.setContent(ourPractice.getContent());
		}

		return ourPracticeBean;
	}

	private OurPracticeBean findRelatedOurPracticeToDisplay(Integer ourPracticeId, Map<Integer, OurPracticeBean> mapOurPracticeBeans) {
		OurPracticeBean ourPracticeBean = null;
		if (ourPracticeId == null) {
			ourPracticeBean = findLastestOurPractice(mapOurPracticeBeans);
		} else {
			ourPracticeBean = buildOurPracticeBean(ourPracticeRepo.findOne(ourPracticeId), 0);
		}
		return ourPracticeBean;
	}

	private OurPracticeBean findLastestOurPractice(Map<Integer, OurPracticeBean> mapOurPracticeBeans) {
		List<OurPracticeBean> ourPracticeBeans = new LinkedList<OurPracticeBean>(mapOurPracticeBeans.values());
		if (ourPracticeBeans != null && !ourPracticeBeans.isEmpty()) {
			Collections.sort(ourPracticeBeans, new Comparator<OurPracticeBean>() {

				public int compare(OurPracticeBean p1, OurPracticeBean p2) {
					Date date1 = p1.getCreatedDate();
					Date date2 = p2.getCreatedDate();
					return date2.compareTo(date1);
				}
			});
			return ourPracticeBeans.get(0);
		}
		return null;
	}

	private void buildMapOurPracticeBean(List<OurPracticeBean> ourPracticeBeans, Map<Integer, OurPracticeBean> mapOurPracticeBeans) {
		for (OurPracticeBean pb : ourPracticeBeans) {
			mapOurPracticeBeans.put(pb.getOurPracticeId(), pb);
		}
	}

	public OurPracticeBean getOurPracticeBySlug(String slug) {
		OurPractice ourPractice = ourPracticeRepo.findOneBySlug(slug);

		if (ourPractice != null) {
			OurPracticeBean ourPracticeBean = this.buildOurPracticeBean(ourPractice, 0);
			return ourPracticeBean;
		} else {
			return null;
		}
	}
}