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

import com.sharpinu.persist.domain.Category;
import com.sharpinu.persist.repositories.CategoryRepo;
import com.sharpinu.service.BaseService;
import com.sharpinu.service.IRepositoryService;
import com.sharpinu.web.form.admin.CategoryForm;

/**
 * 
 * @author Mark
 *
 */
@Service
public class CategoryService extends BaseService implements ICategoryService {
	final int PAGE_SIZE = 5;

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	IRepositoryService repositoryService;

	public String saveCategoryForm(CategoryForm categoryForm) {
		try {
			Category category = new Category(categoryForm);
			categoryRepo.save(category);
			categoryForm.setCategoryId(category.getCategoryId());
		} catch (Exception e) {
			throw new RuntimeException("Failed to save Category Form", e);
		}

		return null;
	}

	/**
	 * pageIndex: this value is ZERO base
	 */
	public Page<Category> getCategoryPageInfo(int pageIndex) {
		Sort sort = new Sort(new Order(Direction.ASC, "name"));
		Pageable pageable = new PageRequest(pageIndex, PAGE_SIZE, sort);
		Page<Category> page = categoryRepo.findAll(pageable);
		return page;
	}

	public List<Category> findCategoryViewBean(int pageIndex, Page<Category> page) {
		List<Category> categoryBeans = new ArrayList<Category>();

		for (Category category : page) {
			categoryBeans.add(category);
		}

		return categoryBeans;
	}

	/**
	 * Find all categories
	 * 
	 * @return
	 */
	public List<Category> findAll() {
		return categoryRepo.findAll();
	}

	/**
	 * Find one category by id
	 * 
	 * @return
	 */
	public Category findById(Integer id) {
		return categoryRepo.findOne(id);
	}
}
