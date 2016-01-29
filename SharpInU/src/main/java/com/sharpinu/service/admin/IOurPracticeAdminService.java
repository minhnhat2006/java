package com.sharpinu.service.admin;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sharpinu.persist.domain.OurPractice;
import com.sharpinu.service.IBaseService;
import com.sharpinu.web.bean.OurPracticeBean;
import com.sharpinu.web.form.admin.OurPracticeForm;

/**
 * 
 * @author nhatnguyen
 *
 */
public interface IOurPracticeAdminService extends IBaseService {

	String saveOurPracticeForm(OurPracticeForm ourPracticeForm);

	/**
	 * Find ourPractices with pageIndex criteria
	 * 
	 * @param pageIndex
	 * @return
	 */
	Page<OurPractice> getOurPracticePageInfo(int pageIndex);

	/**
	 * Find ourPractices with pageIndex criteria
	 * 
	 * @param pageIndex
	 * @param ourPracticeBeans
	 * @return
	 */
	List<OurPracticeBean> findOurPracticeViewBean(int pageIndex, Page<OurPractice> ourPractices);
}
