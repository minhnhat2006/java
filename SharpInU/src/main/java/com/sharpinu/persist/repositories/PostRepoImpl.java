package com.sharpinu.persist.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import com.sharpinu.persist.BaseRepoImpl;
import com.sharpinu.persist.domain.Post;

/**
 *
 * @author Mark
 *
 */
public class PostRepoImpl extends BaseRepoImpl implements PostCustomRepo<Post, Integer> {

	public Post findLatestPost() {
		try {
			String queryString = "SELECT p FROM " + PostCustomRepo.TABLE + " p WHERE isPublic = 1 ORDER BY createdDate DESC";
			Query query = this.em.createQuery(queryString).setMaxResults(1);
			return (Post) query.getSingleResult();
		} catch (Exception e) {
			throw new RuntimeException("Failed to find latest Post", e);
		}
	}

	public Post findOneBySlug(String slug) {
		try {
			String queryString = "SELECT p FROM " + PostCustomRepo.TABLE + " p WHERE slug = :slug";
			Query query = this.em.createQuery(queryString).setParameter("slug", slug).setMaxResults(1);

			return (Post) query.getSingleResult();
		} catch (Exception e) {
			throw new RuntimeException("Failed to find Post by slug: " + slug, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Post> findLatestPostByCategory(Integer categoryId, Date offset, int pageSize) {
		try {
			String queryString = "SELECT p FROM Post p WHERE is_public = 1 AND p.category.categoryId = :categoryId AND p.createdDate <= :createdDate ORDER BY p.createdDate DESC";
			Query query = this.em.createQuery(queryString).setParameter("categoryId", categoryId).setParameter("createdDate", offset);

			if (pageSize > 0) {
				query.setMaxResults(pageSize);
			}

			return query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException(String.format("Failed to find latest posts by[categoryId=%s]", categoryId), e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Post> findPostsByDate(Date offset, int pageSize) {
		try {
			String queryString = "SELECT p FROM Post p WHERE is_public = 1 AND p.createdDate < :offset ORDER BY p.createdDate DESC";
			Query query = this.em.createQuery(queryString).setParameter("offset", offset);

			if (pageSize > 0) {
				query.setMaxResults(pageSize);
			}

			return query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException(String.format("Failed to find posts by date[date=%1$tm %1$te,%1$tY]", offset), e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Post> findByContentContainingOrderCreatedDesc(String term, Date createdDate, int limit) {
		String queryString = "SELECT p FROM Post p WHERE is_public = 1 AND (p.summary LIKE :term OR p.content LIKE :term OR p.category.name LIKE :term) AND p.createdDate < :offset ORDER BY createdDate DESC";
		Query query = this.em.createQuery(queryString).setMaxResults(limit).setParameter("term", "%" + term + "%").setParameter("offset", createdDate);
		return query.getResultList();
	}

	public int countContentContainingOrderCreatedDesc(String term, Date createdDate) {
		String queryString = "SELECT COUNT(p) FROM " + PostCustomRepo.TABLE
				+ " p WHERE is_public = 1 AND (p.summary LIKE :term OR p.content LIKE :term OR p.category.name LIKE :term) AND p.createdDate < :offset ORDER BY createdDate DESC";
		Query query = this.em.createQuery(queryString).setParameter("term", "%" + term + "%").setParameter("offset", createdDate);
		return ((Long) query.getSingleResult()).intValue();
	}

	public int countByCategory(int categoryId) {
		String queryString = "SELECT COUNT(p) FROM " + PostCustomRepo.TABLE + " p WHERE is_public = 1 AND p.category.categoryId = ?";
		Query query = this.em.createQuery(queryString).setParameter(1, categoryId);
		return ((Long) query.getSingleResult()).intValue();
	}

	public Object findByAdvisorTokenAndUserId(String advisorToken, int userId) {
		try {
			String queryString = "SELECT p.* FROM post AS p" + " JOIN advisor_post AS ap ON p.post_id = ap.post_id" + " JOIN advisor AS a ON ap.advisor_id = a.advisor_id"
					+ " JOIN ticket AS t ON a.ticket_id = t.ticket_id" + " WHERE a.token = :token AND t.to_user = :user"
					+ " AND CURDATE() BETWEEN a.from_date AND a.to_date";
			Query query = this.em.createNativeQuery(queryString).setParameter("token", advisorToken).setParameter("user", userId).setMaxResults(1);
			@SuppressWarnings("unchecked")
			List<Object> objs = (List<Object>) query.getResultList();

			if (!objs.isEmpty()) {
				return objs.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed to find Post by advisorToken: " + advisorToken, e);
		}
	}
}