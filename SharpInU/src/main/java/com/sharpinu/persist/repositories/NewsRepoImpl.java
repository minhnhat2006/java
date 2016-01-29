/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.persist.repositories;

import com.sharpinu.persist.BaseRepoImpl;

import com.sharpinu.persist.domain.News;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author khanh nguyen
 */
public class NewsRepoImpl extends BaseRepoImpl implements NewsCustomRepo<News, Integer> {
    @Autowired
	@Qualifier("newsRepo")
	NewsRepo newsRepo;

	public News findLatestNews() {
		try {
			String queryString = "SELECT n FROM News n ORDER BY createdDate DESC";
			Query query = this.em.createQuery(queryString).setMaxResults(1);
			return (News) query.getSingleResult();
		} catch (Exception e) {
			throw new RuntimeException("Failed to find latest News", e);
		}
	}
	

	@SuppressWarnings("unchecked")
	public List<News> findByContentContainingOrderCreatedDesc(String term, Date createdDate, int limit) {
		String queryString = "SELECT n FROM News n WHERE n.summary LIKE :term OR n.title LIKE :term OR n.content LIKE :term ORDER BY createdDate DESC";
		Query query = this.em.createQuery(queryString).setMaxResults(limit).setParameter("term", "%" + term + "%");
		return query.getResultList();
	}

	public int countContentContainingOrderCreatedDesc(String term, Date createdDate) {
		String queryString = "SELECT COUNT(p) FROM News p WHERE p.summary LIKE :term OR n.title LIKE :term OR p.content LIKE :term  ORDER BY createdDate DESC";
		Query query = this.em.createQuery(queryString).setParameter("term", "%" + term + "%");
		return ((Long) query.getSingleResult()).intValue();
	}

}
