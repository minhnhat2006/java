package com.sharpinu.persist.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sharpinu.persist.BaseRepoImpl;
import com.sharpinu.persist.domain.Career;

/**
 *
 * @author Mark
 *
 */
public class CareerRepoImpl extends BaseRepoImpl implements CareerCustomRepo<Career, Integer> {

	@Autowired
	private CareerRepo careerRepo;

	public List<Career> findInDateRange(Date from, Date to) {
		try {
			List<Career> results = new ArrayList<Career>();
			if (from == null && to == null) {
				results = careerRepo.findAll();
			} else {
				results = careerRepo.findByCreatedDateBetween(from, to);
			}
			return results;
		} catch (Exception e) {
			throw new RuntimeException("Failed to find Career", e);
		}
	}

}