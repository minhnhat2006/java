package com.sharpinu.web.form.admin;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class UserForm {

	@NotBlank(message = "First Name is required.")
	@Size(min = 0, max = 255, message = "Length of First Name must be from 1 to 255 characters.")
	private String firstName;

	@NotBlank(message = "Last Name is required.")
	@Size(min = 0, max = 255, message = "Length of Last Name must be from 1 to 255 characters.")
	private String lastName;

	@NotBlank(message = "Email is required.")
	@Size(min = 0, max = 255, message = "Length of Email must be from 1 to 255 characters.")
	@Email(message = "Email address must be well-formed.")
	private String userEmail;

	private int userId;

	private Date dateCreated;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getCreatedDate() {
		return this.dateCreated;
	}

	public void setCreatedDate(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
}
