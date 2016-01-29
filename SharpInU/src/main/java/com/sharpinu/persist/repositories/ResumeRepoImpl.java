package com.sharpinu.persist.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sharpinu.persist.BaseRepoImpl;
import com.sharpinu.persist.domain.Resume;

/**
 *
 * @author Mark
 *
 */
public class ResumeRepoImpl extends BaseRepoImpl implements ResumeCustomRepo<Resume, Integer> {

	@Autowired
	private ResumeRepo resumeRepo;

	public List<Resume> findInDateRange(Date from, Date to) {
		try {
			List<Resume> results = new ArrayList<Resume>();
			if (from == null && to == null) {
				results = resumeRepo.findAll();
			} else {
				results = resumeRepo.findByCreatedDateBetween(from, to);
			}
			return results;
		} catch (Exception e) {
			throw new RuntimeException("Failed to find Resume", e);
		}
	}

}