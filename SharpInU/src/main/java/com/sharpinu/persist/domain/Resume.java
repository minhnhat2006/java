package com.sharpinu.persist.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

import com.sharpinu.web.form.ResumeForm;

@Entity
@Table(name = "resume")
public class Resume implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "resume_id")
	private Integer resumeId;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "name")
	private String name;

	@Column(name = "image")
	private String image;

	@Column(name = "current_title")
	private String currentTitle;

	@Column(name = "contact")
	private String contact;

	@Column(name = "summary_skill")
	private String summarySkill;

	@Column(name = "objectives")
	private String objectives;

	@Column(name = "education")
	private String education;

	@Column(name = "experience")
	private String experience;

	@Column(name = "certificate")
	private String certificate;

	@Column(name = "additional_info")
	private String additionalInfo;

	@Column(name = "cv")
	private String cv;

	@Column(name = "phone")
	private String phone;

	@Column(name = "address")
	private String address;

	@Column(name = "achievement")
	private String achievement;

	@Column(name = "created_date")
	private Date createdDate;

	public Resume() {
	}

	public Resume(ResumeForm form) {
		super();
		this.name = form.getName();
		this.currentTitle = form.getCurrentTitle();
		this.contact = form.getContactInfomation();
		this.summarySkill = form.getSummarySkill();
		this.objectives = form.getObjectives();
		this.education = form.getEducation();
		this.experience = form.getExperience();
		this.certificate = form.getCertificate();
		this.additionalInfo = form.getAdditionalInfo();
		this.userId = form.getUserId();
		this.createdDate = new Date();
		this.phone = form.getPhone();
		this.address = form.getAddress();
		this.achievement = form.getAchievement();
	}

	public Integer getResumeId() {
		return this.resumeId;
	}

	public void setResumeId(Integer resumeId) {
		this.resumeId = resumeId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCurrentTitle() {
		return this.currentTitle;
	}

	public void setCurrentTitle(String currentTitle) {
		this.currentTitle = currentTitle;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getSummarySkill() {
		return this.summarySkill;
	}

	public void setSummarySkill(String summarySkill) {
		this.summarySkill = summarySkill;
	}

	public String getObjectives() {
		return this.objectives;
	}

	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}

	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getExperience() {
		return this.experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getCertificate() {
		return this.certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getAdditionalInfo() {
		return this.additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getCv() {
		return this.cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

}
