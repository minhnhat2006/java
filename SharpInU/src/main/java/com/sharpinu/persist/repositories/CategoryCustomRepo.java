package com.sharpinu.persist.repositories;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Mark
 *
 */
public interface CategoryCustomRepo<Category, Integer> {
	public static final String TABLE = "Category";

	public List<Category> findHasPostByOffset(Date offset, int pageSize);
}