package com.sharpinu.web.bean;

import java.util.Date;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class OurPracticeBean {
	Integer ourPracticeId;
	String content;
	String summary;
	String slug;
	Date createdDate;
	String updatedDate;
	int ourPracticeCategoryId;
	String ourPracticeCategoryName;
	String userEmail;
	int userId;

	public OurPracticeBean(Integer ourPracticeId, String title, String content, String summary) {
		super();
		this.ourPracticeId = ourPracticeId;
		this.content = content;
		this.summary = summary;
	}

	public OurPracticeBean() {
	}

	public Integer getOurPracticeId() {
		return ourPracticeId;
	}

	public void setOurPracticeId(Integer ourPracticeId) {
		this.ourPracticeId = ourPracticeId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getOurPracticeCategoryId() {
		return ourPracticeCategoryId;
	}

	public String getOurPracticeCategoryName() {
		return ourPracticeCategoryName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public int getUserId() {
		return userId;
	}

	public void setOurPracticeCategoryId(int ourPracticeCategoryId) {
		this.ourPracticeCategoryId = ourPracticeCategoryId;
	}

	public void setOurPracticeCategoryName(String ourPracticeCategoryName) {
		this.ourPracticeCategoryName = ourPracticeCategoryName;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
