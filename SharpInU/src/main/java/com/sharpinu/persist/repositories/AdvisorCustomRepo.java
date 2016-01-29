package com.sharpinu.persist.repositories;

import java.util.Date;
import java.util.List;

import com.sharpinu.persist.BaseCustomRepo;
import com.sharpinu.persist.domain.Advisor;

/**
 *
 * @author Mark
 *
 */
@SuppressWarnings("hiding")
public interface AdvisorCustomRepo<Advisor, Integer> extends BaseCustomRepo {
	List<Advisor> findInDateRange(Date from, Date to);
}