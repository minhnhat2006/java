package com.sharpinu.persist.repositories;

import java.util.Date;
import java.util.List;

import com.sharpinu.persist.BaseCustomRepo;
import com.sharpinu.persist.domain.Career;

/**
 *
 * @author Mark
 *
 */
public interface CareerCustomRepo<Career, Integer> extends BaseCustomRepo {
	List<Career> findInDateRange(Date from, Date to);
}