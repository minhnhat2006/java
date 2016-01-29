package com.sharpinu.persist;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sharpinu.cache.CacheHelper;

/**
 *
 * @author Mark
 *
 */
public class BaseRepoImpl implements BaseCustomRepo {
	@PersistenceContext
	protected EntityManager em;
	protected CacheHelper cacheHelper = CacheHelper.getInstance();

	public int countInDateRange(Date from, Date to, String entityClass) {
		String queryString = "SELECT COUNT(e) FROM " + entityClass + " e WHERE e.createdDate BETWEEN :from AND :to";
		Query query = this.em.createQuery(queryString).setParameter("from", from).setParameter("to", to);
		return ((Long) query.getSingleResult()).intValue();
	}
}
