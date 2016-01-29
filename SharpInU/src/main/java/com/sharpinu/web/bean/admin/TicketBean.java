/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.web.bean.admin;

/**
 *
 * @author khanh nguyen
 */
public class TicketBean {

	public TicketBean() {

	}

	public TicketBean(String title, String content, Integer status, Integer fromUser, Integer toUser, Integer type, Integer refId, String createdDate, Integer ticketId) {
		this.content = content;
		this.ticketId = ticketId;
		this.title = title;
		this.createdDate = createdDate;
		this.status = status;
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.refId = refId;
	}

	private String title;

	private String content;

	private Integer status;

	private Integer fromUser;

	private Integer toUser;

	private Integer type;

	private Integer refId;

	private String createdDate;

	private Integer ticketId;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the fromUser
	 */
	public Integer getFromUser() {
		return fromUser;
	}

	/**
	 * @param fromUser
	 *            the fromUser to set
	 */
	public void setFromUser(Integer fromUser) {
		this.fromUser = fromUser;
	}

	/**
	 * @return the toUser
	 */
	public Integer getToUser() {
		return toUser;
	}

	/**
	 * @param toUser
	 *            the toUser to set
	 */
	public void setToUser(Integer toUser) {
		this.toUser = toUser;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the refId
	 */
	public Integer getRefId() {
		return refId;
	}

	/**
	 * @param refId
	 *            the refId to set
	 */
	public void setRefId(Integer refId) {
		this.refId = refId;
	}

	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the ticketId
	 */
	public Integer getTicketId() {
		return ticketId;
	}

	/**
	 * @param ticketId
	 *            the ticketId to set
	 */
	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}
}
