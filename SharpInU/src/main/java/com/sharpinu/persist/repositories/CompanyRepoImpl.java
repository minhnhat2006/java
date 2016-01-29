package com.sharpinu.persist.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sharpinu.persist.BaseRepoImpl;
import com.sharpinu.persist.domain.Company;

/**
 *
 * @author Mark
 *
 */
public class CompanyRepoImpl extends BaseRepoImpl implements CompanyCustomRepo<Company, Integer> {
	@Autowired
	private CompanyRepo companyRepo;

	public List<Company> findInDateRange(Date from, Date to) {
		try {
			List<Company> results = new ArrayList<Company>();
			if (from == null && to == null) {
				results = companyRepo.findAll();
			} else {
				results = companyRepo.findByCreatedDateBetween(from, to);
			}
			return results;
		} catch (Exception e) {
			throw new RuntimeException("Failed to find Company", e);
		}
	}
}