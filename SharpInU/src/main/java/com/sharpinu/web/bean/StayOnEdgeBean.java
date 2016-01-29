package com.sharpinu.web.bean;

import java.util.List;

public class StayOnEdgeBean {
	List<CategoryBean> categoryBeans;
	Boolean hasMoreCategory;
	PostBean postBean;
	List<PostBean> postBeans;
	Boolean hasMorePost;

	// for pagination
	Integer currentIndex;
	Integer totalPages;
	Integer beginIndex;
	Integer endIndex;

	public StayOnEdgeBean(List<CategoryBean> categoryBeans, PostBean latestPost) {
		super();
		this.categoryBeans = categoryBeans;
		this.postBean = latestPost;
	}

	public StayOnEdgeBean() {
	}

	public List<CategoryBean> getCategoryBeans() {
		return this.categoryBeans;
	}

	public void setCategoryBeans(List<CategoryBean> categoryBeans) {
		this.categoryBeans = categoryBeans;
	}

	public Boolean getHasMoreCategory() {
		return this.hasMoreCategory;
	}

	public void setHasMoreCategory(Boolean hasMoreCategory) {
		this.hasMoreCategory = hasMoreCategory;
	}

	public PostBean getPostBean() {
		return postBean;
	}

	public void setPostBean(PostBean postBean) {
		this.postBean = postBean;
	}

	public List<PostBean> getPostBeans() {
		return postBeans;
	}

	public void setPostBeans(List<PostBean> postBeans) {
		this.postBeans = postBeans;
	}

	public Boolean getHasMorePost() {
		return this.hasMorePost;
	}

	public void setHasMorePost(Boolean hasMorePost) {
		this.hasMorePost = hasMorePost;
	}

	public Integer getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(Integer currentIndex) {
		this.currentIndex = currentIndex;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(Integer beginIndex) {
		this.beginIndex = beginIndex;
	}

	public Integer getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}

}
