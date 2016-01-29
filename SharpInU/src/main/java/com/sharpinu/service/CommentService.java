/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.service;

import java.util.ArrayList;
import java.util.List;

import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sharpinu.email.IMailSender;
import com.sharpinu.persist.domain.Comment;
import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.repositories.CommentRepo;
import com.sharpinu.persist.repositories.TicketRepo;
import com.sharpinu.persist.repositories.UserRepo;
import com.sharpinu.processor.email.MailProcessor;
import com.sharpinu.web.bean.CommentBean;
import com.sharpinu.web.bean.session.UserPreferences;
import com.sharpinu.web.common.util.SecurityUtil;
import com.sharpinu.web.form.CommentForm;

/**
 *
 * @author khanhnguyen
 */
@Service
public class CommentService extends BaseService implements ICommentService {

	@Autowired
	private TicketRepo ticketRepo;

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	@Qualifier("mailSender")
	IMailSender sender;

	@Autowired
	private UserPreferences userPreferences;

	public CommentBean addComment(CommentForm commentForm, String contextPath) {
		Comment comment = new Comment(commentForm);
		Comment c = commentRepo.save(comment);
		// System.err.println("addComment");
		// System.out.println(c.getContent());

		if (userPreferences.getIsAdmin()) {
			this.sendReplyTicketMailToUser(commentForm, contextPath);
		} else {
			this.sendReplyTicketMailToAdmin(commentForm, contextPath);
		}

		return this.buildCommentBean(c);
	}

	public List<CommentBean> getCommentsByTicketId(int ticketId, int offset, int pageSize) {

		List<Comment> comments = commentRepo.getCommentByTicketId(ticketId, offset, pageSize);
		List<CommentBean> commentBeans = this.buildListCommentBean(comments);
		return commentBeans;
	}

	private List<CommentBean> buildListCommentBean(List<Comment> comments) {
		List<CommentBean> commenttBeans = new ArrayList<CommentBean>();
		for (Comment c : comments) {
			CommentBean commenttBean = buildCommentBean(c);
			commenttBeans.add(commenttBean);
		}
		return commenttBeans;
	}

	public int getNumOfCommentsByTicketId(int ticketId) {
		return commentRepo.getCountCommentsByTicketId(ticketId);

	}

	private CommentBean buildCommentBean(Comment comment) {
		PrettyTime p = new PrettyTime();
		User user = userRepo.findOne(comment.getOwner());
		CommentBean commentBean = new CommentBean();
		commentBean.setContent(comment.getContent());
		commentBean.setOwner(comment.getOwner());
		commentBean.setCreatedDate(p.format(comment.getCreatedDate()));
		commentBean.setTicketConvId(comment.getTicketConvId());

		// commentBean.setTicket(comment.getTicket());
		commentBean.setUserName(user.getFirstName() + " " + user.getLastName());
		return commentBean;

	}

	private void sendReplyTicketMailToUser(CommentForm commentForm, String contextPath) {
		try {
			MailProcessor mailProcessor = new MailProcessor(MailProcessor.MailType.TICKET_REPLY_USER, commentForm, sender);
			User user = userRepo.findOne(commentForm.getTicket().getToUser());
			mailProcessor.setCurrentUser(user);
			mailProcessor.setContextPath(contextPath);
			Thread t = new Thread(mailProcessor);
			t.start();
		} catch (Exception e) {
			log.warn(String.format("Failed to send reply email of Ticket[ticketId= %s] to user", commentForm.getTicketId()), e);
		}
	}

	private void sendReplyTicketMailToAdmin(CommentForm commentForm, String contextPath) {
		try {
			MailProcessor mailProcessor = new MailProcessor(MailProcessor.MailType.TICKET_REPLY_ADMIN, commentForm, sender);
			mailProcessor.setCurrentUser(SecurityUtil.getCurrentUser());
			mailProcessor.setContextPath(contextPath);
			Thread t = new Thread(mailProcessor);
			t.start();
		} catch (Exception e) {
			log.warn(String.format("Failed to send reply email of Ticket[ticketId= %s] to admin", commentForm.getTicketId()), e);
		}
	}
}
