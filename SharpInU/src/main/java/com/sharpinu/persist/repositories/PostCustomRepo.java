package com.sharpinu.persist.repositories;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Mark
 *
 */
@SuppressWarnings("hiding")
public interface PostCustomRepo<Post, Integer> {
	public static final String TABLE = "Post";

	public Post findLatestPost();

	public Post findOneBySlug(String slug);

	public List<Post> findLatestPostByCategory(Integer categoryId, Date offset, int pageSize);

	public List<Post> findPostsByDate(Date offset, int pageSize);

	public List<Post> findByContentContainingOrderCreatedDesc(String term, Date createdDate, int limit);

	public int countContentContainingOrderCreatedDesc(String term, Date createdDate);

	public int countByCategory(int categoryId);
	
	public Object findByAdvisorTokenAndUserId(String advisorToken, int userId);
}