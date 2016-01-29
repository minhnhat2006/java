package com.sharpinu.persist.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.Category;
import com.sharpinu.persist.domain.Post;

/**
 *
 * @author Mark
 *
 */
public interface PostRepo extends BaseRepo<Post, Integer>, PostCustomRepo<Post, Integer> {
	public Page<Post> findByCategory(Category category, Pageable pageable);

	public Page<Post> findByContentContaining(String term, Pageable pageable);
}