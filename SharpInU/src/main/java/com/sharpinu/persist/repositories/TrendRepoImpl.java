package com.sharpinu.persist.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import com.sharpinu.persist.BaseRepoImpl;
import com.sharpinu.persist.domain.Trend;

/**
 *
 * @author Mark
 *
 */
public class TrendRepoImpl extends BaseRepoImpl implements TrendCustomRepo<Trend, Integer> {

	public Trend findLatestTrend() {
		try {
			String queryString = "SELECT p FROM " + TrendCustomRepo.TABLE + " p ORDER BY createdDate DESC";
			Query query = this.em.createQuery(queryString).setMaxResults(1);
			return (Trend) query.getSingleResult();
		} catch (Exception e) {
			throw new RuntimeException("Failed to find latest Trend", e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Trend> findTrendsByDate(Date offset, int pageSize) {
		try {
			String queryString = "SELECT p FROM Trend p WHERE p.createdDate < :offset ORDER BY p.createdDate DESC";
			Query query = this.em.createQuery(queryString).setParameter("offset", offset);

			if (pageSize > 0) {
				query.setMaxResults(pageSize);
			}

			return query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException(String.format("Failed to find trends by date[date=%1$tm %1$te,%1$tY]", offset), e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Trend> findByContentContainingOrderCreatedDesc(String term, Date createdDate, int limit) {
		String queryString = "SELECT p FROM Trend p WHERE p.summary LIKE :term OR p.content LIKE :term ORDER BY createdDate DESC";
		Query query = this.em.createQuery(queryString).setMaxResults(limit).setParameter("term", "%" + term + "%");
		return query.getResultList();
	}

	public int countContentContainingOrderCreatedDesc(String term, Date createdDate) {
		String queryString = "SELECT COUNT(p) FROM " + TrendCustomRepo.TABLE + " p WHERE p.summary LIKE :term OR p.content LIKE :term ORDER BY createdDate DESC";
		Query query = this.em.createQuery(queryString).setParameter("term", "%" + term + "%");
		return ((Long) query.getSingleResult()).intValue();
	}
}