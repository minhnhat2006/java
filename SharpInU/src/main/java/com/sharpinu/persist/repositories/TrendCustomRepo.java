package com.sharpinu.persist.repositories;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Mark
 *
 */
@SuppressWarnings("hiding")
public interface TrendCustomRepo<Trend, Integer> {
	public static final String TABLE = "Trend";

	public Trend findLatestTrend();

	public List<Trend> findTrendsByDate(Date offset, int pageSize);

	public List<Trend> findByContentContainingOrderCreatedDesc(String term, Date createdDate, int limit);

	public int countContentContainingOrderCreatedDesc(String term, Date createdDate);
}