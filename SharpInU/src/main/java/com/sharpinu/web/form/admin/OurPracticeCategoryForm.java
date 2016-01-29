package com.sharpinu.web.form.admin;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import com.sharpinu.persist.domain.OurPracticeCategory;

public class OurPracticeCategoryForm {

	@NotBlank(message = "Name is required.")
	@Size(min = 0, max = 255, message = "Length of Name must be from 1 to 255 characters.")
	private String name;
	private Date createdDate;
	private int ourPracticeCategoryId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOurPracticeCategoryId() {
		return ourPracticeCategoryId;
	}

	public void setOurPracticeCategoryId(int ourPracticeCategoryId) {
		this.ourPracticeCategoryId = ourPracticeCategoryId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public static OurPracticeCategoryForm fromOurPracticeCategory(OurPracticeCategory ourPracticeCategory) {
		OurPracticeCategoryForm ourPracticeCategoryForm = new OurPracticeCategoryForm();
		BeanUtils.copyProperties(ourPracticeCategory, ourPracticeCategoryForm);
		ourPracticeCategoryForm.setOurPracticeCategoryId(ourPracticeCategory.getOurPracticeCategoryId());
		return ourPracticeCategoryForm;
	}
}
