package com.sharpinu.web.bean.admin;

import java.util.Date;

public class StatisticDetailsBean {
	private Integer id;
	private String info;
	private String email;
	private Date submitDate;
	private int pageViews;

	public StatisticDetailsBean(Integer id, String info, String email, Date submitDate) {
		this.id = id;
		this.info = info;
		this.email = email;
		this.submitDate = submitDate;
	}

	public StatisticDetailsBean() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public int getPageViews() {
		return pageViews;
	}

	public void setPageViews(int pageViews) {
		this.pageViews = pageViews;
	}

	public void increasePageViews(int number) {
		this.pageViews += number;
	}
}
