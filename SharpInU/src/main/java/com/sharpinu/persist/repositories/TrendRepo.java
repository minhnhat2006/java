package com.sharpinu.persist.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.Trend;

/**
 *
 * @author Mark
 *
 */
public interface TrendRepo extends BaseRepo<Trend, Integer>, TrendCustomRepo<Trend, Integer> {

	public Page<Trend> findByContentContaining(String term, Pageable pageable);

}