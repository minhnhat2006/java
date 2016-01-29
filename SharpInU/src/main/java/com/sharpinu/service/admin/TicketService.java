/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.service.admin;

import java.util.ArrayList;
import java.util.List;

import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sharpinu.email.IMailSender;
import com.sharpinu.persist.domain.Ticket;
import com.sharpinu.persist.repositories.TicketRepo;
import com.sharpinu.persist.repositories.UserRepo;
import com.sharpinu.processor.email.MailProcessor;
import com.sharpinu.service.BaseService;
import com.sharpinu.util.Lib;
import com.sharpinu.web.bean.admin.TicketBean;
import com.sharpinu.web.form.admin.TicketForm;

/**
 *
 * @author administrator
 */
@Service
public class TicketService extends BaseService implements ITicketService {
	final int PAGE_SIZE = 10;

	@Autowired
	private TicketRepo ticketRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	@Qualifier("mailSender")
	IMailSender sender;

	public List<TicketBean> getTicketsByUser(int userId, int offset, int pageSize) {
		List<Ticket> tickets = ticketRepo.findAllTicketByUserId(userId, offset, pageSize);
		return buildListTicketBean(tickets, 250);
	}

	public String saveTicketForm(TicketForm ticketForm, String contextPath) {
		try {
			Ticket ticket = new Ticket(ticketForm);
			ticketRepo.save(ticket);
			ticketForm.setTicketId(ticket.getTicketId());
			this.sendNewTicketMailToUser(ticket, contextPath);
		} catch (Exception e) {
			throw new RuntimeException("Failed to save Ticket Form", e);
		}

		return null;
	}

	private void sendNewTicketMailToUser(Ticket ticket, String contextPath) {
		try {
			MailProcessor mailProcessor = new MailProcessor(MailProcessor.MailType.NEW_TICKET_USER, ticket, sender);
			mailProcessor.setCurrentUser(userRepo.findOne(ticket.getToUser()));
			mailProcessor.setContextPath(contextPath);
			Thread t = new Thread(mailProcessor);
			t.start();
		} catch (Exception e) {
			log.warn(String.format("Failed to send email of Ticket[ticketId= %s] to user", ticket.getTicketId()), e);
		}
	}

	public List<TicketBean> buildListTicketBean(List<Ticket> tickets, int numOfTopWords) {
		System.out.println("buildListTicketBean");
		List<TicketBean> ticketBeans = new ArrayList<TicketBean>();
		for (Ticket t : tickets) {

			TicketBean ticketBean = buildTicketBean(t, numOfTopWords);
			ticketBeans.add(ticketBean);
		}
		return ticketBeans;

	}

	public TicketBean buildTicketBean(Ticket ticket, int numOfTopWords) {
		PrettyTime p = new PrettyTime();
		TicketBean ticketBean = new TicketBean();
		ticketBean.setFromUser(ticket.getFromUser());
		ticketBean.setRefId(ticket.getRefId());
		ticketBean.setCreatedDate(p.format(ticket.getCreatedDate()));
		ticketBean.setStatus(ticket.getStatus());
		ticketBean.setTicketId(ticket.getTicketId());
		ticketBean.setType(ticket.getType());
		ticketBean.setToUser(ticket.getToUser());
		ticketBean.setTitle(ticket.getTitle());
		if (numOfTopWords > 0) {
			ticketBean.setContent(Lib.getStringLimitWords(ticket.getContent(), numOfTopWords));
		} else {
			ticketBean.setContent(ticket.getContent());
		}
		return ticketBean;
	}

	public long getTotalTickets() {
		return ticketRepo.count();
	}
}
