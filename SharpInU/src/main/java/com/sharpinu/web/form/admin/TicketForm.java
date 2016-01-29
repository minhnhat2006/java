/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.web.form.admin;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import com.sharpinu.persist.domain.Ticket;

/**
 *
 * @author administrator
 */
public class TicketForm {

	@NotBlank(message = "Title is required.")
	@Size(min = 0, max = 255, message = "Length of Title must be from 1 to 255 characters.")
	private String title;

	@NotBlank(message = "Content is required.")
	@Size(min = 0, max = 1024000, message = "Length of Content must be from 1 to 1024000 characters.")
	private String content;

	private Integer status;

	private Integer fromUser;

	private Integer toUser;

	private String type;

	private Integer refId;

	private Date createdDate;

	private Integer ticketId;

	private String subject;

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param title
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

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
	 * @return the formUser
	 */
	public Integer getFromUser() {
		return fromUser;
	}

	/**
	 * @param formUser
	 *            the formUser to set
	 */
	public void setFormUser(Integer formUser) {
		this.fromUser = formUser;
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
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public Integer getTypeAsNumber() {
		if ("career".equals(type)) {
			return Ticket.TYPE_CAREER;
		} else if ("company".equals(type)) {
			return Ticket.TYPE_COMPANY;
		} else if ("resume".equals(type)) {
			return Ticket.TYPE_RESUME;
		} else {
			return -1;
		}
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
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
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

	public static TicketForm fromTicket(Ticket ticket) {
		TicketForm ticketForm = new TicketForm();
		BeanUtils.copyProperties(ticket, ticketForm);
		ticketForm.setTicketId(ticket.getTicketId());
		ticketForm.setType(getTypeAsString(ticket.getType()));

		return ticketForm;
	}

	public static String getTypeAsString(Integer type) {
		if (Ticket.TYPE_CAREER == type) {
			return "career";
		} else if (Ticket.TYPE_COMPANY == type) {
			return "company";
		} else if (Ticket.TYPE_RESUME == type) {
			return "resume";
		} else {
			return null;
		}
	}
}
