package com.sharpinu.persist.repositories;

import java.util.Date;
import java.util.List;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.Resume;

/**
 *
 * @author Mark
 *
 */
public interface ResumeRepo extends BaseRepo<Resume, Integer>, ResumeCustomRepo<Resume, Integer> {

	List<Resume> findByCreatedDateBetween(Date from, Date to);

}