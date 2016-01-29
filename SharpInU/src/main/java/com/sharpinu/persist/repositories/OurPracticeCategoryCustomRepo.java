package com.sharpinu.persist.repositories;

import java.util.List;

/**
 *
 * @author Mark
 *
 */
public interface OurPracticeCategoryCustomRepo<OurPracticeCategory, Integer> {
	public static final String TABLE = "OurPracticeCategory";

	public List<OurPracticeCategory> findHasOurPracticeByOffset(String offset, int pageSize, String order);
}