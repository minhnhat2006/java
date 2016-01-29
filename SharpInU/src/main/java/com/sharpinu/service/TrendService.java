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

import com.sharpinu.constant.TrendConst;
import com.sharpinu.persist.domain.Trend;
import com.sharpinu.persist.repositories.TrendRepo;
import com.sharpinu.util.DateUtil;
import com.sharpinu.util.Lib;
import com.sharpinu.web.bean.TrendBean;
import com.sharpinu.web.bean.TrendPageBean;

@Service
public class TrendService extends BaseService implements ITrendService {

	@Autowired
	@Qualifier("trendRepo")
	private TrendRepo trendRepo;

	public TrendPageBean getTrends(Integer pageSize) {
		try {
			PageRequest pageRequest = new PageRequest(0, pageSize, Sort.Direction.DESC, "createdDate");
			Page<Trend> page = this.findTrendsByContent(null, pageRequest);
			return this.buildTrendPageBean(page.getContent().get(0).getTrendId(), page);
		} catch (Exception e) {
			throw new RuntimeException(String.format("Failed to get trends by [pageSize = %s]", pageSize), e);
		}
	}

	public TrendPageBean searchTrendsSortByCreated(String term) {
		try {
			TrendPageBean trendPageBean = new TrendPageBean();
			Calendar now = Calendar.getInstance();
			now.set(9999, 1, 1);
			List<Trend> trends = trendRepo.findByContentContainingOrderCreatedDesc(term, now.getTime(), TrendConst.DEFAULT_CATEGORY_POST_PAGE_SIZE);

			if (trends != null && !trends.isEmpty()) {
				List<TrendBean> trendBeans = this.buildListTrendBean(trends, 0);
				trendPageBean.setTrendBeans(trendBeans);
				trendPageBean.setTrendBean(trendBeans.get(0));

				int totalItems = trendRepo.countContentContainingOrderCreatedDesc(term, now.getTime());
				int totalPages = (int) Math.round(totalItems / TrendConst.DEFAULT_CATEGORY_POST_PAGE_SIZE) + 1;

				trendPageBean.setCurrentIndex(1);
				trendPageBean.setBeginIndex(1);
				trendPageBean.setEndIndex(totalPages >= TrendConst.DEFAULT_PAGE_RANGE ? TrendConst.DEFAULT_PAGE_RANGE : totalPages);
				trendPageBean.setTotalPages(totalPages);
			}

			return trendPageBean;
		} catch (Exception e) {
			throw new RuntimeException(String.format("Failed to load trends[term = '%s']", term), e);
		}
	}

	public List<TrendBean> searchTrendsSortByCreated(String term, Date createdDate) {
		List<Trend> trends = trendRepo.findByContentContainingOrderCreatedDesc(term, createdDate, TrendConst.DEFAULT_CATEGORY_POST_PAGE_SIZE);
		List<TrendBean> trendBeans = this.buildListTrendBean(trends, 0);

		return trendBeans;
	}

	public TrendBean getTrend(int trendId) {
		Trend trend = trendRepo.findOne(trendId);
		TrendBean trendBean = this.buildTrendBean(trend, 0);
		return trendBean;
	}

	public List<TrendBean> getTrendByDate(Date offset, int pageSize) {
		List<Trend> trends = trendRepo.findTrendsByDate(offset, pageSize);
		List<TrendBean> trendBeans = this.buildListTrendBean(trends, TrendConst.DEFAULT_POST_WORDS_LIMIT);

		return trendBeans;
	}

	private Page<Trend> findTrendsByContent(String term, Pageable pageable) {
		Page<Trend> page;
		if (StringUtils.isNotBlank(term)) {
			page = trendRepo.findByContentContaining(term, pageable);
		} else {
			page = trendRepo.findAll(pageable);
		}

		return page;
	}

	private TrendPageBean buildTrendPageBean(Integer trendId, Page<Trend> page) {
		TrendPageBean trendPageBean = new TrendPageBean();
		List<TrendBean> trendBeans = this.buildListTrendBean(page.getContent(), 0);
		Map<Integer, TrendBean> mapTrendBeans = new HashMap<Integer, TrendBean>();
		buildMapTrendBean(trendBeans, mapTrendBeans);
		TrendBean displayTrend = findRelatedTrendToDisplay(trendId, mapTrendBeans);

		trendPageBean.setTrendBeans(trendBeans);
		trendPageBean.setTrendBean(displayTrend);
		trendPageBean.setHasMoreTrend(page.getTotalPages() > 1);

		return trendPageBean;
	}

	private List<TrendBean> buildListTrendBean(List<Trend> trends, int numOfTopWords) {
		List<TrendBean> trendBeans = new ArrayList<TrendBean>();
		for (Trend p : trends) {
			TrendBean trendBean = buildTrendBean(p, numOfTopWords);
			trendBeans.add(trendBean);
		}

		return trendBeans;
	}

	private TrendBean buildTrendBean(Trend trend, int numOfTopWords) {
		TrendBean trendBean = new TrendBean();
		trendBean.setTrendId(trend.getTrendId());
		trendBean.setSummary(trend.getSummary());
		trendBean.setCreatedDate(trend.getCreatedDate());
		trendBean.setUpdatedDate(DateUtil.convertDateTimeToString(trend.getCreatedDate().getTime()));

		if (numOfTopWords > 0) {
			trendBean.setContent(Lib.getStringLimitWords(trend.getContent(), numOfTopWords));
		} else {
			trendBean.setContent(trend.getContent());
		}

		return trendBean;
	}

	private TrendBean findRelatedTrendToDisplay(Integer trendId, Map<Integer, TrendBean> mapTrendBeans) {
		TrendBean trendBean = null;
		if (trendId == null) {
			trendBean = findLastestTrend(mapTrendBeans);
		} else {
			trendBean = buildTrendBean(trendRepo.findOne(trendId), 0);
		}
		return trendBean;
	}

	private TrendBean findLastestTrend(Map<Integer, TrendBean> mapTrendBeans) {
		List<TrendBean> trendBeans = new LinkedList<TrendBean>(mapTrendBeans.values());
		if (trendBeans != null && !trendBeans.isEmpty()) {
			Collections.sort(trendBeans, new Comparator<TrendBean>() {

				public int compare(TrendBean p1, TrendBean p2) {
					Date date1 = p1.getCreatedDate();
					Date date2 = p2.getCreatedDate();
					return date2.compareTo(date1);
				}
			});
			return trendBeans.get(0);
		}
		return null;
	}

	private void buildMapTrendBean(List<TrendBean> trendBeans, Map<Integer, TrendBean> mapTrendBeans) {
		for (TrendBean pb : trendBeans) {
			mapTrendBeans.put(pb.getTrendId(), pb);
		}
	}
}