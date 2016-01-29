package com.sharpinu.web.bean;

import java.util.Date;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class TrendBean {
	Integer trendId;
	String content;
	String summary;
	Date createdDate;
	String updatedDate;
	String userEmail;
	int userId;

	public TrendBean(Integer trendId, String title, String content, String summary) {
		super();
		this.trendId = trendId;
		this.content = content;
		this.summary = summary;
	}

	public TrendBean() {
	}

	public Integer getTrendId() {
		return trendId;
	}

	public void setTrendId(Integer trendId) {
		this.trendId = trendId;
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

	public String getUserEmail() {
		return userEmail;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
