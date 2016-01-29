package com.sharpinu.web.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class CompanyForm {

	@NotBlank(message = "Info is required.")
	@Size(min = 0, max = 1024, message = "Length of Info must be from 1 to 1024 characters.")
	private String info;

	@NotBlank(message = "Website is required.")
	@Size(min = 0, max = 255, message = "Length of Website must be from 1 to 255 characters")
	@Pattern(message = "Website URL must be well-formed.", regexp = "(https?://)?(www\\.)?[-a-zA-Z0-9@%._\\+~#=]{2,256}\\.[a-z]{2,4}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)")
	private String website;

	@NotBlank(message = "Email is required.")
	@Size(min = 0, max = 255, message = "Length of Email must be from 1 to 255 characters")
	@Email(message = "Email address must be well-formed.")
	private String email;

	@Pattern(message = "Invalid phone format", regexp = "^(\\+)?(([\\s])?([0-9])([\\s])?){0,20}$")
	private String phone;

	@NotBlank(message = "Situation is required.")
	@Size(min = 0, max = 1024, message = "Length of Situation must be from 1 to 1024 characters.")
	private String situation;

	@NotBlank(message = "Your expectation is required.")
	@Size(min = 0, max = 1024, message = "Length of Your expectation must be from 1 to 1024 characters.")
	private String expectation;
	
	private MultipartFile additionInfoFile;

	private int companyId;

	private int userId;

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public String getExpectation() {
		return expectation;
	}

	public void setExpectation(String expectation) {
		this.expectation = expectation;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public MultipartFile getAdditionInfoFile() {
		return additionInfoFile;
	}

	public void setAdditionInfoFile(MultipartFile additionInfoFile) {
		this.additionInfoFile = additionInfoFile;
	}

}
