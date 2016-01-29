/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.web.form;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import com.sharpinu.persist.domain.Ticket;

/**
 *
 * @author administrator
 */
public class CommentForm {

	private Integer ticketConvId;

	private Integer ticketId;

	private Ticket ticket;

	private Integer owner;

	private Date createdDate;

	private MultipartFile csvFile;

	private MultipartFile attachedFile;

	@NotBlank(message = "Content is required.")
	@Size(min = 0, max = 1024000, message = "Length of Content must be from 1 to 1024000 characters.")
	private String content;

	/**
	 * @return the ticketConvId
	 */
	public Integer getTicketConvId() {
		return ticketConvId;
	}

	/**
	 * @param ticketConvId
	 *            the ticketConvId to set
	 */
	public void setTicketConvId(Integer ticketConvId) {
		this.ticketConvId = ticketConvId;
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

	/**
	 * @return the owner
	 */
	public Integer getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(Integer owner) {
		this.owner = owner;
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
	 * @return the ticket
	 */
	public Ticket getTicket() {
		return ticket;
	}

	/**
	 * @param ticket
	 *            the ticket to set
	 */
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public MultipartFile getAttachedFile() {
		return attachedFile;
	}

	public void setAttachedFile(MultipartFile attachedFile) {
		this.attachedFile = attachedFile;
	}

	public MultipartFile getCsvFile() {
		return csvFile;
	}

	public void setCsvFile(MultipartFile csvFile) {
		this.csvFile = csvFile;
	}
}
