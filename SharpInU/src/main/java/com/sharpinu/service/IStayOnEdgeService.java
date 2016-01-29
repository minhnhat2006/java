package com.sharpinu.service;

import java.util.Date;
import java.util.List;

import com.sharpinu.web.bean.CategoryBean;
import com.sharpinu.web.bean.PostBean;
import com.sharpinu.web.bean.StayOnEdgeBean;

public interface IStayOnEdgeService extends IBaseService {

	StayOnEdgeBean loadDefault();

	StayOnEdgeBean loadSelectedPostAndPostsByCategory(Integer postId, Integer categoryId, Integer pageIndex);

	StayOnEdgeBean filterPosts(String term, Integer pageIndex);

	StayOnEdgeBean loadAllPostsAndCategories(Integer pageIndex);

	StayOnEdgeBean searchPostsSortByCreated(String term);

	List<CategoryBean> getCategoriesHasPost(Date offset, int pageSize);

	List<PostBean> searchPostsSortByCreated(String term, Date createdDate);

	List<PostBean> getPostsByCategory(int categoryId, int pageIndex);

	List<PostBean> getPostsByDate(Date offset, int pageSize);

	PostBean getPost(int postId);

	PostBean getPostBySlug(String slug);
}
