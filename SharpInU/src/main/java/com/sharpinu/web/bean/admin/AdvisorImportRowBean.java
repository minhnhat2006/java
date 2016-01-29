package com.sharpinu.web.bean.admin;

import java.util.Date;

public class AdvisorImportRowBean {

	private String token;

	private int advisorId;

	private int ticketId;

	private Date fromDate;

	private Date toDate;

	private String urls;

	public AdvisorImportRowBean(int advisorId, int ticketId, String token, Date fromDate, Date toDate, String urls) {
		this.advisorId = advisorId;
		this.ticketId = ticketId;
		this.token = token;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.urls = urls;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getAdvisorId() {
		return advisorId;
	}

	public void setAdvisorId(int advisorId) {
		this.advisorId = advisorId;
	}

	public int getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getUrls() {
		return this.urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}
}
