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

import com.sharpinu.web.form.admin.OurPracticeForm;

@Entity
@Table(name = "ourPractice")
public class OurPractice implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ourPractice_id")
	private Integer ourPracticeId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ourPracticeCategory_id")
	private OurPracticeCategory ourPracticeCategory;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "content")
	private String content;

	@Column(name = "summary")
	private String summary;

	@Column(name = "slug")
	private String slug;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	public OurPractice() {
	}

	public OurPractice(OurPracticeForm ourPracticeForm) {
		super();
		BeanUtils.copyProperties(ourPracticeForm, this);
		if (ourPracticeForm.getOurPracticeId() > 0) {
			this.setOurPracticeId(ourPracticeForm.getOurPracticeId());
			this.setUserId(ourPracticeForm.getUserId());
			this.setOurPracticeCategory(ourPracticeForm.getOurPracticeCategory());
			this.setCreatedDate(ourPracticeForm.getCreatedDate());
		} else {
			this.setCreatedDate(new Date());
			this.setUserId(ourPracticeForm.getUserId());
		}
		this.setUpdatedDate(new Date());
	}

	public Integer getOurPracticeId() {
		return this.ourPracticeId;
	}

	public void setOurPracticeId(Integer ourPracticeId) {
		this.ourPracticeId = ourPracticeId;
	}

	public OurPracticeCategory getOurPracticeCategory() {
		return ourPracticeCategory;
	}

	public void setOurPracticeCategory(OurPracticeCategory ourPracticeCategory) {
		this.ourPracticeCategory = ourPracticeCategory;
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

	public String getSlug() {
		return this.slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
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
