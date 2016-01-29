package com.sharpinu.web.form.admin;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import com.sharpinu.persist.domain.Trend;

public class TrendForm {

	@NotBlank(message = "Summary is required.")
	@Size(min = 0, max = 1024, message = "Length of Summary must be from 1 to 1024 characters.")
	private String summary;

	@NotBlank(message = "Content is required.")
	@Size(min = 0, max = 65536, message = "Length of Content must be from 1 to 65536 characters.")
	private String content;

	private Date createdDate;
	private int userId;
	private int trendId;

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTrendId() {
		return trendId;
	}

	public void setTrendId(int trendId) {
		this.trendId = trendId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public static TrendForm fromTrend(Trend trend) {
		TrendForm trendForm = new TrendForm();
		BeanUtils.copyProperties(trend, trendForm);
		trendForm.setTrendId(trend.getTrendId());
		return trendForm;
	}
}
