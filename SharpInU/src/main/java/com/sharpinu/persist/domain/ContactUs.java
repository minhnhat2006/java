package com.sharpinu.persist.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

import com.sharpinu.web.form.ContactUsForm;

@Entity
@Table(name = "contact_us")
public class ContactUs implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CONTACT_ID")
	private Integer contactId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "COMPANY_ID")
	private String companyId;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "MESSAGE")
	private String message;

	@Column(name = "PHONE")
	private String phone;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	public ContactUs() {
	}

	public ContactUs(ContactUsForm contactUsForm) {
		super();
		this.name = contactUsForm.getName();
		this.companyId = contactUsForm.getCompany();
		this.email = contactUsForm.getEmail();
		this.message = contactUsForm.getMessage();
		this.phone = contactUsForm.getPhone();
		this.createdDate = new Date();
	}
	
	public Integer getContactId() {
		return this.contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
