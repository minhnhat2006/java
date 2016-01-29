package com.sharpinu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharpinu.persist.domain.WorkCategory;
import com.sharpinu.persist.repositories.WorkCategoryRepo;

/**
 *
 * @author Mark
 *
 */
@Service("workCategoryService")
public class WorkCategoryService extends BaseService implements IWorkCategoryService {
	@Autowired
	WorkCategoryRepo workCategoryRepo;
	
	public List<WorkCategory> findAll() {
		return workCategoryRepo.findAll();
	}

}
