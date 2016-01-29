package com.sharpinu.web.dwr;

import java.util.Date;
import java.util.List;

import com.sharpinu.web.bean.CategoryBean;
import com.sharpinu.web.bean.PostBean;

public interface IDwrStayOnEdgeService {

	public List<CategoryBean> getCategories(long offset, int pageSize);

	public List<PostBean> searchPosts(String term, Date createdDate);

	public PostBean getPostById(int postId);

	public List<PostBean> loadMorePosts(int categoryId, int pageIndex);

	public List<PostBean> loadMorePostsRecent(long offset);

}
