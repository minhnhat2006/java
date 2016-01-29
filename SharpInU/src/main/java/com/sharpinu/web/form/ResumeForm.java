package com.sharpinu.web.form;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

/**
 * Resume form
 * 
 * @author Mark
 *
 */
public class ResumeForm {
	@NotBlank(message = "Name is required.")
	@Size(min = 1, max = 254, message = "Length of name must be from 1 to 254 characters")
	private String name;

	@Pattern(message = "Invalid phone format", regexp="^(\\+)?(([\\s])?([0-9])([\\s])?){0,20}$")
	private String phone;

	@Size(min = 0, max = 255, message = "Length of Address must be from 1 to 255 characters")
	private String address;

	@Size(min = 0, max = 255, message = "Length of Current Title must be from 1 to 255 characters")
	private String currentTitle;

	private String contactInfomation;

	@Size(min = 0, max = 1024, message = "Length of Summary Skill must be from 1 to 1024 characters")
	private String summarySkill;

	@NotBlank(message = "Objectives is required.")
	@Size(min = 0, max = 1024, message = "Length of Objectives must be from 1 to 1024 characters")
	private String objectives;

	@Size(min = 0, max = 255, message = "Length of Achievement must be from 1 to 255 characters")
	private String achievement;

	private String education;

	private String experience;

	private String certificate;

	private String additionalInfo;

	private MultipartFile cvAttachment;

	private MultipartFile uploadPhoto;

	private int resumeId;
	
	private int userId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrentTitle() {
		return currentTitle;
	}

	public void setCurrentTitle(String currentTitle) {
		this.currentTitle = currentTitle;
	}

	public String getContactInfomation() {
		return contactInfomation;
	}

	public void setContactInfomation(String contactInfomation) {
		this.contactInfomation = contactInfomation;
	}

	public String getSummarySkill() {
		return summarySkill;
	}

	public void setSummarySkill(String summarySkill) {
		this.summarySkill = summarySkill;
	}

	public String getObjectives() {
		return objectives;
	}

	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public MultipartFile getCvAttachment() {
		return cvAttachment;
	}

	public void setCvAttachment(MultipartFile cvAttachment) {
		this.cvAttachment = cvAttachment;
	}

	public MultipartFile getUploadPhoto() {
		return uploadPhoto;
	}

	public void setUploadPhoto(MultipartFile uploadPhoto) {
		this.uploadPhoto = uploadPhoto;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public int getResumeId() {
		return resumeId;
	}

	public void setResumeId(int resumeId) {
		this.resumeId = resumeId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@AssertTrue(message = "CV is required.")
	public boolean isCvAttachment() {
		return (cvAttachment != null) && (!cvAttachment.isEmpty());
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