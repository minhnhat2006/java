/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.persist.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.BeanUtils;

import com.sharpinu.web.form.CommentForm;

/**
 *
 * @author khanh nguyen
 */
@Entity
@Table(name = "ticket_conv")
public class Comment implements java.io.Serializable {

	private static long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "TICKET_CONV_ID")
	private Integer ticketConvId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TICKET_ID")
	private Ticket ticket;

	@Column(name = "owner")
	private Integer owner;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date")
	private Date createdDate;

	@Column(name = "CONTENT")
	private String content;

	public Comment() {

	}

	public Comment(CommentForm commentForm) {
		super();
		BeanUtils.copyProperties(commentForm, this);
		this.setCreatedDate(new Date());

	}

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
	 *            the ticketId to set
	 */
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}
