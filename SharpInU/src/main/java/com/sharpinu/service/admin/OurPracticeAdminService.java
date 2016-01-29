package com.sharpinu.service.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.sharpinu.persist.domain.OurPractice;
import com.sharpinu.persist.domain.OurPracticeCategory;
import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.repositories.OurPracticeCategoryRepo;
import com.sharpinu.persist.repositories.OurPracticeRepo;
import com.sharpinu.persist.repositories.UserRepo;
import com.sharpinu.service.BaseService;
import com.sharpinu.service.IRepositoryService;
import com.sharpinu.web.bean.OurPracticeBean;
import com.sharpinu.web.form.admin.OurPracticeForm;

/**
 * 
 * @author Mark
 *
 */
@Service
public class OurPracticeAdminService extends BaseService implements com.sharpinu.service.admin.IOurPracticeAdminService {
	final int PAGE_SIZE = 5;
	@Autowired
	OurPracticeRepo ourPracticeRepo;

	@Autowired
	OurPracticeCategoryRepo ourPracticeCategoryRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	IRepositoryService repositoryService;

	public String saveOurPracticeForm(OurPracticeForm ourPracticeForm) {
		try {
			OurPractice ourPractice = new OurPractice(ourPracticeForm);
			ourPracticeRepo.save(ourPractice);
			ourPracticeForm.setOurPracticeId(ourPractice.getOurPracticeId());
		} catch (Exception e) {
			throw new RuntimeException("Failed to save OurPractice Form", e);
		}

		return null;
	}

	public Page<OurPractice> getOurPracticePageInfo(int pageIndex) {
		Sort sort = new Sort(new Order(Direction.ASC, "summary"));
		Pageable pageable = new PageRequest(pageIndex, PAGE_SIZE, sort);
		Page<OurPractice> page = ourPracticeRepo.findAll(pageable);
		return page;
	}

	public List<OurPracticeBean> findOurPracticeViewBean(int pageIndex, Page<OurPractice> ourPractices) {
		try {
			List<OurPracticeBean> ourPracticeBeans = new ArrayList<OurPracticeBean>();

			for (OurPractice ourPractice : ourPractices) {
				OurPracticeCategory cate = ourPracticeCategoryRepo.findOne(ourPractice.getOurPracticeCategory().getOurPracticeCategoryId());
				User user = userRepo.findOne(ourPractice.getUserId());
				OurPracticeBean ourPracticeBean = new OurPracticeBean(ourPractice.getOurPracticeId(), "", ourPractice.getContent(), ourPractice.getSummary());

				if (cate != null) {
					ourPracticeBean.setOurPracticeCategoryId(cate.getOurPracticeCategoryId());
					ourPracticeBean.setOurPracticeCategoryName(cate.getName());
				}

				if (user != null) {
					ourPracticeBean.setUserId(user.getUserId());
					ourPracticeBean.setUserEmail(user.getUserEmail());
				}

				ourPracticeBeans.add(ourPracticeBean);
			}

			return ourPracticeBeans;
		} catch (Exception e) {
			log.error("Failed to findOurPracticeViewBean", e);
			throw new RuntimeException("Failed to findOurPracticeViewBean", e);
		}
	}
}
