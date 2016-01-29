package com.sharpinu.web.form.admin;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import com.sharpinu.persist.domain.Category;
import com.sharpinu.persist.domain.Post;

public class PostForm {

	@NotBlank(message = "Summary is required.")
	@Size(min = 0, max = 1024, message = "Length of Summary must be from 1 to 1024 characters.")
	private String summary;

	@NotBlank(message = "Content is required.")
	@Size(min = 0, max = 100000, message = "Length of Content must be from 1 to 100000 characters.")
	private String content;

	@Min(value = 1, message = "Must select a catefory of Post.")
	private Integer categoryId;

	private Date createdDate;
	private int userId;
	private Category category;
	private int postId;
	private boolean isPublic;

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	
	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public static PostForm fromPost(Post post) {
		PostForm postForm = new PostForm();
		BeanUtils.copyProperties(post, postForm);
		postForm.setPostId(post.getPostId());
		return postForm;
	}
}
