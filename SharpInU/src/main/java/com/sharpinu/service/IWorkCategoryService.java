package com.sharpinu.service;

import java.util.List;

import com.sharpinu.persist.domain.WorkCategory;

/**
 *
 * @author Mark
 *
 */
public interface IWorkCategoryService extends IBaseService {
	public List<WorkCategory> findAll();
}