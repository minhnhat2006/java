package com.sharpinu.web.bean;

import java.util.Date;
import java.util.List;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class CategoryBean {
	Integer categoryId;
	String categoryName;
	private Date createdDate;
	List<PostBean> postBeans;
	Boolean hasMorePost;

	public CategoryBean(Integer categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<PostBean> getPostBeans() {
		return postBeans;
	}

	public void setPostBeans(List<PostBean> postBeans) {
		this.postBeans = postBeans;
	}

	public Boolean getHasMorePost() {
		return hasMorePost;
	}

	public void setHasMorePost(Boolean hasMorePost) {
		this.hasMorePost = hasMorePost;
	}

}
