package com.sharpinu.web.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class AdvisorForm {

	@NotBlank(message = "Token is required.")
	@Size(min = 0, max = 255, message = "Length of Token must be from 1 to 255 characters.")
	private String token;

	private int advisorId;

	private int ticketId;

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

}
