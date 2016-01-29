package com.sharpinu.persist.repositories;

import java.util.Date;
import java.util.List;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.Career;

/**
 *
 * @author Mark
 *
 */
public interface CareerRepo extends BaseRepo<Career, Integer>, CareerCustomRepo<Career, Integer> {

	List<Career> findByCreatedDateBetween(Date from, Date to);

}