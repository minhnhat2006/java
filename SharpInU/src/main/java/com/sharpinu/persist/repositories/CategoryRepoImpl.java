package com.sharpinu.persist.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import com.sharpinu.persist.BaseRepoImpl;
import com.sharpinu.persist.domain.Category;

/**
 *
 * @author Mark
 *
 */
public class CategoryRepoImpl extends BaseRepoImpl implements CategoryCustomRepo<Category, Integer> {

	@SuppressWarnings("unchecked")
	public List<Category> findHasPostByOffset(Date offset, int pageSize) {
		try {
			String queryString = "SELECT distinct(c) FROM " + CategoryCustomRepo.TABLE + " c JOIN c.posts p WHERE p.isPublic = true AND c.createdDate < :offset ORDER BY c.createdDate DESC";
			Query query = this.em.createQuery(queryString).setParameter("offset", offset).setMaxResults(pageSize);

			return (List<Category>) query.getResultList();

		} catch (Exception e) {

			throw new RuntimeException("Failed to find Category.", e);
		}
	}
}
