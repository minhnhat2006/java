package com.sharpinu.web.dwr;

import java.util.Date;
import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;

import com.sharpinu.constant.StayOnTheEdgeConst;
import com.sharpinu.service.IStayOnEdgeService;
import com.sharpinu.web.bean.CategoryBean;
import com.sharpinu.web.bean.PostBean;

@RemoteProxy
public class DwrStayOnEdgeService implements IDwrStayOnEdgeService {

	@Autowired
	IStayOnEdgeService stayOnEdgeService;

	@RemoteMethod
	public List<CategoryBean> getCategories(long offset, int pageSize) {
		Date date = new Date(offset);
		return stayOnEdgeService.getCategoriesHasPost(date, pageSize);
	}

	@RemoteMethod
	public List<PostBean> searchPosts(String term, Date createdDate) {
		return stayOnEdgeService.searchPostsSortByCreated(term, createdDate);
	}

	@RemoteMethod
	public PostBean getPostById(int postId) {
		return stayOnEdgeService.getPost(postId);
	}

	@RemoteMethod
	public PostBean getPostBySlug(String slug) {
		return stayOnEdgeService.getPostBySlug(slug);
	}

	@RemoteMethod
	public List<PostBean> loadMorePosts(int categoryId, int pageIndex) {
		return stayOnEdgeService.getPostsByCategory(categoryId, pageIndex);
	}

	@RemoteMethod
	public List<PostBean> loadMorePostsRecent(long offset) {
		Date date = new Date(offset);
		return stayOnEdgeService.getPostsByDate(date, StayOnTheEdgeConst.DEFAULT_POST_PAGE_SIZE);
	}
}
