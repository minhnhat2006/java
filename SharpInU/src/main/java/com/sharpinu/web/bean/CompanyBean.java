package com.sharpinu.web.bean;

import java.util.Date;

import org.directwebremoting.annotations.DataTransferObject;
import org.springframework.beans.BeanUtils;

import com.sharpinu.persist.domain.Company;
import com.sharpinu.persist.domain.Ticket;

@DataTransferObject
public class CompanyBean {

	private Integer companyId;

	private Integer userId;

	private String info;

	private String website;

	private String email;

	private String phone;

	private String situation;

	private String expectation;

	private Date createdDate;

	private String additionalInfo;

	private Ticket ticket;

	public CompanyBean(Company company) {
		BeanUtils.copyProperties(company, this);
	}

	public Integer getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSituation() {
		return this.situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public String getExpectation() {
		return this.expectation;
	}

	public void setExpectation(String expectation) {
		this.expectation = expectation;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public Ticket getTicket() {
		return this.ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
}
