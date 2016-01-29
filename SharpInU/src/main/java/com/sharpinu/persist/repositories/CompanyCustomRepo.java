package com.sharpinu.persist.repositories;

import java.util.Date;
import java.util.List;

import com.sharpinu.persist.BaseCustomRepo;
import com.sharpinu.persist.domain.Company;

/**
 *
 * @author Mark
 *
 */
public interface CompanyCustomRepo<Company, Integer> extends BaseCustomRepo {

	List<Company> findInDateRange(Date from, Date to);
}