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

import com.sharpinu.persist.domain.OurPracticeCategory;
import com.sharpinu.persist.repositories.OurPracticeCategoryRepo;
import com.sharpinu.service.BaseService;
import com.sharpinu.service.IRepositoryService;
import com.sharpinu.web.form.admin.OurPracticeCategoryForm;

/**
 * 
 * @author Mark
 *
 */
@Service
public class OurPracticeCategoryAdminService extends BaseService implements IOurPracticeCategoryAdminService {
	final int PAGE_SIZE = 5;

	@Autowired
	OurPracticeCategoryRepo ourPracticeCategoryRepo;

	@Autowired
	IRepositoryService repositoryService;

	public String saveOurPracticeCategoryForm(OurPracticeCategoryForm ourPracticeCategoryForm) {
		try {
			OurPracticeCategory ourPracticeCategory = new OurPracticeCategory(ourPracticeCategoryForm);
			ourPracticeCategoryRepo.save(ourPracticeCategory);
			ourPracticeCategoryForm.setOurPracticeCategoryId(ourPracticeCategory.getOurPracticeCategoryId());
		} catch (Exception e) {
			throw new RuntimeException("Failed to save OurPracticeCategory Form", e);
		}

		return null;
	}

	/**
	 * pageIndex: this value is ZERO base
	 */
	public Page<OurPracticeCategory> getOurPracticeCategoryPageInfo(int pageIndex) {
		Sort sort = new Sort(new Order(Direction.ASC, "name"));
		Pageable pageable = new PageRequest(pageIndex, PAGE_SIZE, sort);
		Page<OurPracticeCategory> page = ourPracticeCategoryRepo.findAll(pageable);
		return page;
	}

	public List<OurPracticeCategory> findOurPracticeCategoryViewBean(int pageIndex, Page<OurPracticeCategory> page) {
		List<OurPracticeCategory> ourPracticeCategoryBeans = new ArrayList<OurPracticeCategory>();

		for (OurPracticeCategory ourPracticeCategory : page) {
			ourPracticeCategoryBeans.add(ourPracticeCategory);
		}

		return ourPracticeCategoryBeans;
	}

	/**
	 * Find all categories
	 * 
	 * @return
	 */
	public List<OurPracticeCategory> findAll() {
		return ourPracticeCategoryRepo.findAll();
	}

	/**
	 * Find one ourPracticeCategory by id
	 * 
	 * @return
	 */
	public OurPracticeCategory findById(Integer id) {
		return ourPracticeCategoryRepo.findOne(id);
	}
}
