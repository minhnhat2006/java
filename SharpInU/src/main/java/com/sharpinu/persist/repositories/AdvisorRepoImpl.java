package com.sharpinu.persist.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sharpinu.persist.BaseRepoImpl;
import com.sharpinu.persist.domain.Advisor;

/**
 *
 * @author Mark
 *
 */
public class AdvisorRepoImpl extends BaseRepoImpl implements AdvisorCustomRepo<Advisor, Integer> {

	@Autowired
	private AdvisorRepo advisorRepo;

	public List<Advisor> findInDateRange(Date from, Date to) {
		try {
			List<Advisor> results = new ArrayList<Advisor>();
			if (from == null && to == null) {
				results = advisorRepo.findAll();
			} else {
				results = advisorRepo.findByCreatedDateBetween(from, to);
			}
			return results;
		} catch (Exception e) {
			throw new RuntimeException("Failed to find Advisor", e);
		}
	}

}