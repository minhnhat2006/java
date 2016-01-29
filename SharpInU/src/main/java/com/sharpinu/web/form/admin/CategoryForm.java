package com.sharpinu.web.form.admin;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import com.sharpinu.persist.domain.Category;

public class CategoryForm {

	@NotBlank(message = "Name is required.")
	@Size(min = 0, max = 255, message = "Length of Name must be from 1 to 255 characters.")
	private String name;
	private Date createdDate;
	private int categoryId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public static CategoryForm fromCategory(Category category) {
		CategoryForm categoryForm = new CategoryForm();
		BeanUtils.copyProperties(category, categoryForm);
		categoryForm.setCategoryId(category.getCategoryId());
		return categoryForm;
	}
}
