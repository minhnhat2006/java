package com.sharpinu.web.bean;

import java.util.List;

public class OurPracticePageBean {
	List<OurPracticeCategoryBean> ourPracticeCategoryBeans;
	Boolean hasMoreOurPracticeCategory;
	OurPracticeBean ourPracticeBean;
	List<OurPracticeBean> ourPracticeBeans;
	Boolean hasMoreOurPractice;

	// for pagination
	Integer currentIndex;
	Integer totalPages;
	Integer beginIndex;
	Integer endIndex;

	public OurPracticePageBean(List<OurPracticeCategoryBean> ourPracticeCategoryBeans, OurPracticeBean latestOurPractice) {
		super();
		this.ourPracticeCategoryBeans = ourPracticeCategoryBeans;
		this.ourPracticeBean = latestOurPractice;
	}

	public OurPracticePageBean() {
	}

	public List<OurPracticeCategoryBean> getOurPracticeCategoryBeans() {
		return this.ourPracticeCategoryBeans;
	}

	public void setOurPracticeCategoryBeans(List<OurPracticeCategoryBean> ourPracticeCategoryBeans) {
		this.ourPracticeCategoryBeans = ourPracticeCategoryBeans;
	}

	public Boolean getHasMoreOurPracticeCategory() {
		return this.hasMoreOurPracticeCategory;
	}

	public void setHasMoreOurPracticeCategory(Boolean hasMoreOurPracticeCategory) {
		this.hasMoreOurPracticeCategory = hasMoreOurPracticeCategory;
	}

	public OurPracticeBean getOurPracticeBean() {
		return ourPracticeBean;
	}

	public void setOurPracticeBean(OurPracticeBean ourPracticeBean) {
		this.ourPracticeBean = ourPracticeBean;
	}

	public List<OurPracticeBean> getOurPracticeBeans() {
		return ourPracticeBeans;
	}

	public void setOurPracticeBeans(List<OurPracticeBean> ourPracticeBeans) {
		this.ourPracticeBeans = ourPracticeBeans;
	}

	public Boolean getHasMoreOurPractice() {
		return this.hasMoreOurPractice;
	}

	public void setHasMoreOurPractice(Boolean hasMoreOurPractice) {
		this.hasMoreOurPractice = hasMoreOurPractice;
	}

	public Integer getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(Integer currentIndex) {
		this.currentIndex = currentIndex;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(Integer beginIndex) {
		this.beginIndex = beginIndex;
	}

	public Integer getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}

}
