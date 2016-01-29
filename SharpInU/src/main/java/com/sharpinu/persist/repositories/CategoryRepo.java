package com.sharpinu.persist.repositories;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.Category;

/**
 *
 * @author Mark
 *
 */
public interface CategoryRepo extends BaseRepo<Category, Integer>, CategoryCustomRepo<Category, Integer> {

}