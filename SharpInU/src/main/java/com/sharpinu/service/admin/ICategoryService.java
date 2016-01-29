package com.sharpinu.service.admin;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sharpinu.persist.domain.Category;
import com.sharpinu.service.IBaseService;
import com.sharpinu.web.form.admin.CategoryForm;

/**
 * 
 * @author nhatnguyen
 *
 */
public interface ICategoryService extends IBaseService {

	String saveCategoryForm(CategoryForm categoryForm);

	/**
	 * Find categories with pageIndex criteria
	 * 
	 * @param pageIndex
	 * @return
	 */
	Page<Category> getCategoryPageInfo(int pageIndex);

	/**
	 * Find categories with pageIndex criteria
	 * 
	 * @param pageIndex
	 * @param categories
	 * @return
	 */
	List<Category> findCategoryViewBean(int pageIndex, Page<Category> categories);

	/**
	 * Find all categories
	 * 
	 * @return
	 */
	public List<Category> findAll();

	/**
	 * Find one category by id
	 * 
	 * @return
	 */
	public Category findById(Integer id);
}
