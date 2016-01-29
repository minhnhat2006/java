package com.sharpinu.web.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Contact Us form
 * 
 * @author Mark
 *
 */
public class ContactUsForm {
	@NotBlank(message = "Name is required.")
	@Size(min = 1, max = 254, message = "Length of name must be from 1 to 254 characters")
	private String name;

	@Size(min = 1, max = 255, message = "Length of email must be from 1 to 255 characters")
	private String company;

	@NotBlank(message = "Email is required.")
	@Size(min = 1, max = 255, message = "Length of email must be from 1 to 255 characters")
	@Pattern(message = "Invalid email format", regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	private String email;
	
	@NotBlank(message = "Message is required.")
	@Size(min = 0, max = 1024, message = "Length of Message must be from 1 to 1024 characters")
	private String message;
	
	@NotBlank(message = "Phone is required.")
	@Pattern(message = "Invalid phone format", regexp="^(\\+)?(([\\s])?([0-9])([\\s])?){0,20}$")
	private String phone;

	private int contactUsId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getContactUsId() {
		return contactUsId;
	}

	public void setContactUsId(int contactUsId) {
		this.contactUsId = contactUsId;
	}
}