package com.sharpinu.web.form.admin;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import com.sharpinu.persist.domain.OurPractice;
import com.sharpinu.persist.domain.OurPracticeCategory;

public class OurPracticeForm {

	@NotBlank(message = "Summary is required.")
	@Size(min = 0, max = 1024, message = "Length of Summary must be from 1 to 1024 characters.")
	private String summary;

	@NotBlank(message = "Content is required.")
	@Size(min = 0, max = 65536, message = "Length of Content must be from 1 to 65536 characters.")
	private String content;

	@Min(value = 1, message = "Must select a catefory of OurPractice.")
	private Integer ourPracticeCategoryId;

	private Date createdDate;
	private int userId;
	private OurPracticeCategory ourPracticeCategory;
	private int ourPracticeId;

	public Integer getOurPracticeCategoryId() {
		return this.ourPracticeCategoryId;
	}

	public void setOurPracticeCategoryId(Integer ourPracticeCategoryId) {
		this.ourPracticeCategoryId = ourPracticeCategoryId;
	}

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

	public OurPracticeCategory getOurPracticeCategory() {
		return ourPracticeCategory;
	}

	public void setOurPracticeCategory(OurPracticeCategory ourPracticeCategory) {
		this.ourPracticeCategory = ourPracticeCategory;
	}

	public int getOurPracticeId() {
		return ourPracticeId;
	}

	public void setOurPracticeId(int ourPracticeId) {
		this.ourPracticeId = ourPracticeId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public static OurPracticeForm fromOurPractice(OurPractice ourPractice) {
		OurPracticeForm ourPracticeForm = new OurPracticeForm();
		BeanUtils.copyProperties(ourPractice, ourPracticeForm);
		ourPracticeForm.setOurPracticeId(ourPractice.getOurPracticeId());
		return ourPracticeForm;
	}
}
