package com.sharpinu.web.bean;

import java.util.Date;

import org.directwebremoting.annotations.DataTransferObject;
import org.springframework.beans.BeanUtils;

import com.sharpinu.persist.domain.Resume;
import com.sharpinu.persist.domain.Ticket;

@DataTransferObject
public class ResumeBean {

	private Integer resumeId;

	private Integer userId;

	private String name;

	private String image;

	private String currentTitle;

	private String contact;

	private String summarySkill;

	private String objectives;

	private String education;

	private String experience;

	private String certificate;

	private String additionalInfo;

	private String cv;

	private String phone;

	private String address;

	private String achievement;

	private Date createdDate;

	private Ticket ticket;

	public ResumeBean(Resume resume) {
		BeanUtils.copyProperties(resume, this);
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

	public Ticket getTicket() {
		return this.ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
}
