/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharpinu.constant.WebConstants;
import com.sharpinu.persist.domain.Ticket;
import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.repositories.TicketRepo;
import com.sharpinu.service.CommentService;
import com.sharpinu.service.admin.TicketService;
import com.sharpinu.util.Lib;
import com.sharpinu.web.bean.CommentBean;
import com.sharpinu.web.bean.admin.TicketBean;
import com.sharpinu.web.common.util.SecurityUtil;
import com.sharpinu.web.form.CommentForm;

/**
 *
 * @author khanh nguyen
 */
@Controller
@RequestMapping(value = "/ticket")
public class UserTicketController extends BaseController {

	@Autowired
	private TicketService ticketService;

	@Autowired
	private TicketRepo ticketRepo;

	@Autowired
	private CommentService commentService;

	@RequestMapping("/{pageIndex}/user_tickets")
	public String getTickets(HttpServletRequest request, HttpServletResponse response, @PathVariable("pageIndex") int pageIndex, Model model) throws IOException {
		User currentUser = SecurityUtil.getCurrentUser();
		List<TicketBean> ticketBeans;
		int offset = (pageIndex - 1) * 5;
		int limit = 5;
		if (currentUser != null) {
			ticketBeans = ticketService.getTicketsByUser(currentUser.getUserId(), offset, limit);
		} else {
			ticketBeans = new ArrayList<TicketBean>();
			return "redirect:sec/sign_in.in";
		}

		model.addAttribute("tickets", ticketBeans);
		model.addAttribute("currentPage", pageIndex);
		return WebConstants.Views.USER_TICKET;
	}

	@RequestMapping("/{ticketId}/single_ticket")
	public String getSingleTicket(HttpServletRequest request, HttpServletResponse response, @PathVariable("ticketId") int ticketId, Model model) throws IOException {
		Ticket ticket = ticketRepo.findOne(ticketId);
		List<CommentBean> commentBeans = commentService.getCommentsByTicketId(ticket.getTicketId(), 0, 0);
		User currentUser = SecurityUtil.getCurrentUser();
		int totalComments = commentService.getNumOfCommentsByTicketId(ticketId);
		model.addAttribute("ticket", ticketService.buildTicketBean(ticket, 0));
		model.addAttribute("comments", commentBeans);
		model.addAttribute("owner", currentUser.getUserId());
		model.addAttribute("total", totalComments);
		return WebConstants.Views.SINGLE_TICKET;
	}

	@RequestMapping(value = "/load-more", method = RequestMethod.POST)
	public @ResponseBody List<TicketBean> loadMoreTickets(HttpServletRequest request, HttpServletResponse response) {
		User currentUser = SecurityUtil.getCurrentUser();
		int offset = Lib.getIntValue(request.getParameter("offset"));
		int limit = 5;
		List<TicketBean> ticketBeans;
		if (currentUser != null) {
			ticketBeans = ticketService.getTicketsByUser(currentUser.getUserId(), offset, limit);
		} else {
			ticketBeans = new ArrayList<TicketBean>();
		}
		return ticketBeans;
	}

	@RequestMapping(value = "/add-comment", method = RequestMethod.POST)
	public @ResponseBody CommentBean addComment(HttpServletRequest request, HttpServletResponse response) {
		int userOwner = Lib.getIntValue(request.getParameter("owner"));
		int ticketId = Lib.getIntValue(request.getParameter("ticketId"));// content
		String content = request.getParameter("content");
		Ticket ticket = ticketRepo.findOne(ticketId);
		CommentForm commentForm = new CommentForm();
		commentForm.setContent(content);
		commentForm.setCreatedDate(new Date());
		commentForm.setOwner(userOwner);
		commentForm.setTicketId(ticketId);
		commentForm.setTicket(ticket);

		String path = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		CommentBean commentBean = commentService.addComment(commentForm, path);
		commentBean.setTicketId(ticketId);
		return commentBean;
	}
}
