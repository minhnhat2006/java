package com.sharpinu.persist.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.sharpinu.web.form.admin.TrendForm;

@Entity
@Table(name = "trend")
public class Trend implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "trend_id")
	private Integer trendId;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "content")
	private String content;

	@Column(name = "summary")
	private String summary;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	public Trend() {
	}

	public Trend(TrendForm trendForm) {
		super();
		BeanUtils.copyProperties(trendForm, this);
		if (trendForm.getTrendId() > 0) {
			this.setTrendId(trendForm.getTrendId());
			this.setUserId(trendForm.getUserId());
			this.setCreatedDate(trendForm.getCreatedDate());
		} else {
			this.setCreatedDate(new Date());
			this.setUserId(trendForm.getUserId());
		}

		this.setUpdatedDate(new Date());
	}

	public Integer getTrendId() {
		return this.trendId;
	}

	public void setTrendId(Integer trendId) {
		this.trendId = trendId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
