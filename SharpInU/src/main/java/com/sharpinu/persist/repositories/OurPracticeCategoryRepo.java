package com.sharpinu.persist.repositories;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.OurPracticeCategory;

/**
 *
 * @author Mark
 *
 */
public interface OurPracticeCategoryRepo extends BaseRepo<OurPracticeCategory, Integer>, OurPracticeCategoryCustomRepo<OurPracticeCategory, Integer> {

}