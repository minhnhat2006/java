package com.sharpinu.persist.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.OurPractice;
import com.sharpinu.persist.domain.OurPracticeCategory;

/**
 *
 * @author Mark
 *
 */
public interface OurPracticeRepo extends BaseRepo<OurPractice, Integer>, OurPracticeCustomRepo<OurPractice, Integer> {
	public Page<OurPractice> findByOurPracticeCategory(OurPracticeCategory ourPracticeCategory, Pageable pageable);

	public Page<OurPractice> findByContentContaining(String term, Pageable pageable);
}