package com.sharpinu.web.bean;

import java.util.List;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class OurPracticeCategoryBean {
	Integer ourPracticeCategoryId;
	String ourPracticeCategoryName;
	List<OurPracticeBean> ourPracticeBeans;
	Boolean hasMoreOurPractice;

	public OurPracticeCategoryBean(Integer ourPracticeCategoryId, String ourPracticeCategoryName) {
		super();
		this.ourPracticeCategoryId = ourPracticeCategoryId;
		this.ourPracticeCategoryName = ourPracticeCategoryName;
	}

	public Integer getOurPracticeCategoryId() {
		return ourPracticeCategoryId;
	}

	public void setOurPracticeCategoryId(Integer ourPracticeCategoryId) {
		this.ourPracticeCategoryId = ourPracticeCategoryId;
	}

	public String getOurPracticeCategoryName() {
		return ourPracticeCategoryName;
	}

	public void setOurPracticeCategoryName(String ourPracticeCategoryName) {
		this.ourPracticeCategoryName = ourPracticeCategoryName;
	}

	public List<OurPracticeBean> getOurPracticeBeans() {
		return ourPracticeBeans;
	}

	public void setOurPracticeBeans(List<OurPracticeBean> ourPracticeBeans) {
		this.ourPracticeBeans = ourPracticeBeans;
	}

	public Boolean getHasMoreOurPractice() {
		return hasMoreOurPractice;
	}

	public void setHasMoreOurPractice(Boolean hasMoreOurPractice) {
		this.hasMoreOurPractice = hasMoreOurPractice;
	}

}
