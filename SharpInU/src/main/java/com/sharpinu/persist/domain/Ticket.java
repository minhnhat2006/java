/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.persist.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.BeanUtils;

import com.sharpinu.web.form.admin.TicketForm;

/**
 *
 * @author khanh nguyen
 */
@Entity
@Table(name = "ticket")
public class Ticket implements java.io.Serializable {

	public static int STATUS_OPEN = 0;
	public static int STATUS_CLOSE = 1;
	public static int TYPE_CAREER = 1;
	public static int TYPE_COMPANY = 2;
	public static int TYPE_RESUME = 3;

	public static String STATUS_NAME_OPEN = "Open";
	public static String STATUS_NAME_CLOSE = "Close";

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ticket_id")
	private Integer ticketId;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@Column(name = "status")
	private Integer status;

	@Column(name = "from_user")
	private Integer fromUser;

	@Column(name = "to_user")
	private Integer toUser;

	@Column(name = "type")
	private Integer type;

	@Column(name = "ref_id")
	private Integer refId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ticket")
	private List<Comment> comments;

	public Ticket() {

	}

	public Ticket(TicketForm ticketForm) {
		super();
		BeanUtils.copyProperties(ticketForm, this);
		this.ticketId = ticketForm.getTicketId();
		this.createdDate = ticketForm.getCreatedDate();
		this.fromUser = ticketForm.getFromUser();
		this.toUser = ticketForm.getToUser();
		this.type = ticketForm.getTypeAsNumber();
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
	 * @return the comments
	 */
	public List<Comment> getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
