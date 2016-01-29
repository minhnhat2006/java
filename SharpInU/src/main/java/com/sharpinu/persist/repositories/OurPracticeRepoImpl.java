package com.sharpinu.persist.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import com.sharpinu.persist.BaseRepoImpl;
import com.sharpinu.persist.domain.OurPractice;

/**
 *
 * @author Mark
 *
 */
public class OurPracticeRepoImpl extends BaseRepoImpl implements OurPracticeCustomRepo<OurPractice, Integer> {

	public OurPractice findLatestOurPractice() {
		try {
			String queryString = "SELECT p FROM " + OurPracticeCustomRepo.TABLE + " p ORDER BY createdDate DESC";
			Query query = this.em.createQuery(queryString).setMaxResults(1);
			return (OurPractice) query.getSingleResult();
		} catch (Exception e) {
			throw new RuntimeException("Failed to find latest OurPractice", e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<OurPractice> findLatestOurPracticeByCategory(Integer ourPracticeCategoryId, Date offset, int pageSize) {
		try {
			String queryString = "SELECT p FROM " + OurPracticeCustomRepo.TABLE
					+ " p WHERE p.ourPracticeCategory.ourPracticeCategoryId = :ourPracticeCategoryId AND p.createdDate <= :createdDate ORDER BY p.createdDate DESC";
			Query query = this.em.createQuery(queryString).setParameter("ourPracticeCategoryId", ourPracticeCategoryId).setParameter("createdDate", offset);

			if (pageSize > 0) {
				query.setMaxResults(pageSize);
			}

			return query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException(String.format("Failed to find latest ourPractices by[ourPracticeCategoryId=%s]", ourPracticeCategoryId), e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<OurPractice> findOurPracticesByDate(Date offset, int pageSize) {
		try {
			String queryString = "SELECT p FROM OurPractice p WHERE p.createdDate < :offset ORDER BY p.createdDate DESC";
			Query query = this.em.createQuery(queryString).setParameter("offset", offset);

			if (pageSize > 0) {
				query.setMaxResults(pageSize);
			}

			return query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException(String.format("Failed to find ourPractices by date[date=%1$tm %1$te,%1$tY]", offset), e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<OurPractice> findByContentContainingOrderCreatedDesc(String term, Date createdDate, int limit) {
		String queryString = "SELECT p FROM OurPractice p WHERE (p.summary LIKE :term OR p.content LIKE :term OR p.ourPracticeCategory.name LIKE :term) AND p.createdDate < :offset ORDER BY createdDate DESC";
		Query query = this.em.createQuery(queryString).setMaxResults(limit).setParameter("term", "%" + term + "%").setParameter("offset", createdDate);
		return query.getResultList();
	}

	public int countContentContainingOrderCreatedDesc(String term, Date createdDate) {
		String queryString = "SELECT COUNT(p) FROM " + OurPracticeCustomRepo.TABLE
				+ " p WHERE (p.summary LIKE :term OR p.content LIKE :term OR p.ourPracticeCategory.name LIKE :term) AND p.createdDate < :offset ORDER BY createdDate DESC";
		Query query = this.em.createQuery(queryString).setParameter("term", "%" + term + "%").setParameter("offset", createdDate);
		return ((Long) query.getSingleResult()).intValue();
	}

	public int countByCategory(int ourPracticeCategoryId) {
		String queryString = "SELECT COUNT(p) FROM " + OurPracticeCustomRepo.TABLE + " p WHERE p.ourPracticeCategory.ourPracticeCategoryId = ?";
		Query query = this.em.createQuery(queryString).setParameter(1, ourPracticeCategoryId);
		return ((Long) query.getSingleResult()).intValue();
	}

	public OurPractice findOneBySlug(String slug) {
		try {
			String queryString = "SELECT p FROM " + OurPracticeCustomRepo.TABLE + " p WHERE slug = :slug";
			Query query = this.em.createQuery(queryString).setParameter("slug", slug).setMaxResults(1);

			return (OurPractice) query.getSingleResult();
		} catch (Exception e) {
			throw new RuntimeException("Failed to find Our Practice by slug: " + slug, e);
		}
	}
}