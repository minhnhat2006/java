package com.sharpinu.persist.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.sharpinu.web.form.CareerForm;

@Entity
@Table(name = "career")
public class Career implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "career_id")
	private Integer careerId;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "info")
	private String info;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "situation")
	private String situation;

	@Column(name = "expectation")
	private String expectation;

	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "additional_info")
	private String additionalInfo;

	@Column(name = "name")
	private String name;

	public Career() {
	}

	public Career(CareerForm careerForm) {
		super();
		BeanUtils.copyProperties(careerForm, this);
		this.userId = careerForm.getUserId();
		this.createdDate = new Date();
	}

	public Integer getCareerId() {
		return this.careerId;
	}

	public void setCareerId(Integer careerId) {
		this.careerId = careerId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
