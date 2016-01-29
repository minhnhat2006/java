package com.sharpinu.persist.repositories;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.WorkCategory;

/**
 * 
 * @author Mark
 *
 */
public interface WorkCategoryRepo extends BaseRepo<WorkCategory, Integer>, WorkCategoryCustomRepo<WorkCategory, Integer> {

}
