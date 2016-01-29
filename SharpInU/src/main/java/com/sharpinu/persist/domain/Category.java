package com.sharpinu.persist.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;
import org.springframework.beans.BeanUtils;

import com.sharpinu.web.form.admin.CategoryForm;

@Entity
@Table(name = "category")
public class Category implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "category_id")
	@AccessType("property")
	private Integer categoryId;

	@Column(name = "name")
	private String name;

	@Column(name = "created_date")
	private Date createdDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private List<Post> posts;

	public Category() {
	}

	public Category(CategoryForm categoryForm) {
		super();
		BeanUtils.copyProperties(categoryForm, this);
		if (categoryForm.getCategoryId() > 0) {
			this.setCategoryId(categoryForm.getCategoryId());
			this.setCreatedDate(categoryForm.getCreatedDate());
		} else {
			this.createdDate = new Date();
		}
	}

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

}
