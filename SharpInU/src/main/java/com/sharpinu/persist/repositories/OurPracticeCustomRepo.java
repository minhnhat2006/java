package com.sharpinu.persist.repositories;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Mark
 *
 */
public interface OurPracticeCustomRepo<OurPractice, Integer> {
	public static final String TABLE = "OurPractice";

	public OurPractice findLatestOurPractice();

	public List<OurPractice> findLatestOurPracticeByCategory(Integer ourPracticeCategoryId, Date offset, int pageSize);

	public List<OurPractice> findOurPracticesByDate(Date offset, int pageSize);

	public List<OurPractice> findByContentContainingOrderCreatedDesc(String term, Date createdDate, int limit);

	public int countContentContainingOrderCreatedDesc(String term, Date createdDate);

	public int countByCategory(int ourPracticeCategoryId);

	public OurPractice findOneBySlug(String slug);
}