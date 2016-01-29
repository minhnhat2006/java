package com.sharpinu.service.admin;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sharpinu.persist.domain.OurPracticeCategory;
import com.sharpinu.service.IBaseService;
import com.sharpinu.web.form.admin.OurPracticeCategoryForm;

/**
 * 
 * @author nhatnguyen
 *
 */
public interface IOurPracticeCategoryAdminService extends IBaseService {

	String saveOurPracticeCategoryForm(OurPracticeCategoryForm ourPracticeCategoryForm);

	/**
	 * Find categories with pageIndex criteria
	 * 
	 * @param pageIndex
	 * @return
	 */
	Page<OurPracticeCategory> getOurPracticeCategoryPageInfo(int pageIndex);

	/**
	 * Find categories with pageIndex criteria
	 * 
	 * @param pageIndex
	 * @param categories
	 * @return
	 */
	List<OurPracticeCategory> findOurPracticeCategoryViewBean(int pageIndex, Page<OurPracticeCategory> categories);

	/**
	 * Find all categories
	 * 
	 * @return
	 */
	public List<OurPracticeCategory> findAll();

	/**
	 * Find one ourPracticeCategory by id
	 * 
	 * @return
	 */
	public OurPracticeCategory findById(Integer id);
}
