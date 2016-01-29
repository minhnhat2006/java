package com.sharpinu.persist.repositories;

import java.util.Date;
import java.util.List;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.Company;

/**
 *
 * @author Mark
 *
 */
public interface CompanyRepo extends BaseRepo<Company, Integer>, CompanyCustomRepo<Company, Integer> {

	List<Company> findByCreatedDateBetween(Date from, Date to);

}