package com.sharpinu.persist.repositories;

import java.util.Date;
import java.util.List;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.Advisor;

/**
 *
 * @author Mark
 *
 */
public interface AdvisorRepo extends BaseRepo<Advisor, Integer>, AdvisorCustomRepo<Advisor, Integer> {

	List<Advisor> findByCreatedDateBetween(Date from, Date to);

}