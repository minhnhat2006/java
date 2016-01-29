package com.sharpinu.persist.repositories;

import java.util.Date;
import java.util.List;

import com.sharpinu.persist.BaseCustomRepo;
import com.sharpinu.persist.domain.Resume;

/**
 *
 * @author Mark
 *
 */
public interface ResumeCustomRepo<Resume, Integer> extends BaseCustomRepo {
	List<Resume> findInDateRange(Date from, Date to);
}