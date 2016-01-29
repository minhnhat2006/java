/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.web.controller.admin;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sharpinu.constant.WebConstants;
import com.sharpinu.email.IMailSender;
import com.sharpinu.persist.domain.Advisor;
import com.sharpinu.persist.domain.AdvisorPost;
import com.sharpinu.persist.domain.Career;
import com.sharpinu.persist.domain.Company;
import com.sharpinu.persist.domain.Post;
import com.sharpinu.persist.domain.Resume;
import com.sharpinu.persist.domain.Ticket;
import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.repositories.AdvisorPostRepo;
import com.sharpinu.persist.repositories.AdvisorRepo;
import com.sharpinu.persist.repositories.CareerRepo;
import com.sharpinu.persist.repositories.CommentRepo;
import com.sharpinu.persist.repositories.CompanyRepo;
import com.sharpinu.persist.repositories.PostRepo;
import com.sharpinu.persist.repositories.ResumeRepo;
import com.sharpinu.persist.repositories.TicketRepo;
import com.sharpinu.persist.repositories.UserRepo;
import com.sharpinu.service.ICommentService;
import com.sharpinu.service.admin.ITicketService;
import com.sharpinu.web.bean.CommentBean;
import com.sharpinu.web.bean.admin.AdvisorImportRowBean;
import com.sharpinu.web.bean.admin.TicketBean;
import com.sharpinu.web.common.util.SecurityUtil;
import com.sharpinu.web.controller.BaseController;
import com.sharpinu.web.form.CommentForm;
import com.sharpinu.web.form.admin.TicketForm;

/**
 *
 * @author nhatnguyen
 */
@Controller
@RequestMapping("/admin/ticket")
public class TicketAdminController extends BaseController {

	@Autowired
	TicketRepo ticketRepo;

	@Autowired
	CommentRepo commentRepo;

	@Autowired
	CareerRepo careerRepo;

	@Autowired
	CompanyRepo companyRepo;

	@Autowired
	ResumeRepo resumeRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	ITicketService ticketService;

	@Autowired
	ICommentService commentService;

	@Autowired
	AdvisorRepo advisorRepo;

	@Autowired
	AdvisorPostRepo advisorPostRepo;

	@Autowired
	PostRepo postRepo;

	@Autowired
	@Qualifier("mailSender")
	IMailSender sender;

	@ModelAttribute
	public TicketForm ticketForm() {
		return new TicketForm();
	}

	@ModelAttribute
	public CommentForm commentForm() {
		return new CommentForm();
	}

	@RequestMapping("/{userId}/{pageIndex}/list")
	public String showTickets(HttpServletRequest request, HttpServletResponse response, @PathVariable("userId") int userId, @PathVariable("pageIndex") int pageIndex, Model model) throws IOException {

		List<TicketBean> ticketBeans = ticketService.getTicketsByUser(userId, (pageIndex - 1) * 10, 10);

		model.addAttribute("user", userRepo.findOne(userId));
		model.addAttribute("tickets", ticketBeans);
		model.addAttribute("totalPages", 10);

		model.addAttribute("beginIndex", 1);
		model.addAttribute("endIndex", 10);
		model.addAttribute("currentIndex", pageIndex);

		return WebConstants.Views.TICKET_LIST;
	}

	@RequestMapping(value = "/{id}/review", method = RequestMethod.GET)
	public String reviewTicketForm(@PathVariable("id") int ticketId, HttpServletRequest request, HttpServletResponse response, Model model) {
		Ticket ticket = ticketRepo.findOne(ticketId);
		TicketForm ticketForm = TicketForm.fromTicket(ticket);
		ticketForm.setSubject(this.getTitleFromItem(this.loadRefItem(ticketForm.getType(), ticket.getRefId())));

		model.addAttribute("ticket", ticketForm);

		List<CommentBean> comments = commentService.getCommentsByTicketId(ticketId, 0, Integer.MAX_VALUE);
		model.addAttribute("comments", comments);
		model.addAttribute("type", ticketForm.getType());

		return WebConstants.Views.TICKET_REVIEW;
	}

	@RequestMapping(value = "/{type}/{id}/review", method = RequestMethod.POST)
	public String postCommentForm(@PathVariable("type") String type, @PathVariable("id") int ticketId, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,
			@Valid @ModelAttribute CommentForm commentForm, BindingResult result, Model model) {

		String path = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		String comment = request.getParameter("comment");
		boolean isImport = comment == null ? true : false;

		if (isImport) {
			if (!commentForm.getCsvFile().isEmpty()) {
				List<AdvisorImportRowBean> rows = new ArrayList<AdvisorImportRowBean>();
				MultipartFile csvFile = commentForm.getCsvFile();
				InputStream inputStream;
				int lineNumber = 1;

				try {
					inputStream = csvFile.getInputStream();
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
					String line;
					SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm");

					while ((line = bufferedReader.readLine()) != null) {
						if (lineNumber > 1 && !StringUtils.isEmpty(line)) {
							String[] arrayVals = line.split(",", 3);
							try {
								Date fromDate = formatter.parse(arrayVals[0]);
								Date toDate = formatter.parse(arrayVals[1]);

								SecureRandom random = new SecureRandom();
								String token = new BigInteger(130, random).toString(32);

								// Create Advisor record
								Advisor advisor = new Advisor();
								advisor.setFromDate(fromDate);
								advisor.setToDate(toDate);
								advisor.setCreatedDate(new Date());
								advisor.setTicketId(ticketId);
								advisor.setToken(token);

								MultipartFile attachedFile = commentForm.getAttachedFile();
								String attachedPath = System.getProperty("java.io.tmpdir");// tomcat
								// ubuntu: "/etc/temp/sharpinu/";
								// window: "C:/temp/"; TODO
								String fileName = null;

								if (!attachedFile.isEmpty()) {
									String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

									try {
										fileName = attachedPath + timestamp + "_" + attachedFile.getOriginalFilename();
										byte[] bytes = attachedFile.getBytes();
										BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(new File(fileName)));
										buffStream.write(bytes);
										buffStream.close();

									} catch (Exception e) {
										return "You failed to upload " + fileName + ": " + e.getMessage();
									}
								}

								advisor.setAttachedFile(fileName);
								advisorRepo.save(advisor);

								// Create Advisor_Post records
								String urls = arrayVals[2].replace('"', ' ');
								String[] urlVals = urls.split(",");

								for (String url : urlVals) {
									String slug = this.getSlugFromUrl(url);
									Post post = postRepo.findOneBySlug(slug);

									if (post != null) {
										AdvisorPost advisorPost = new AdvisorPost();
										advisorPost.setAdvisorId(advisor.getAdvisorId());
										advisorPost.setPostId(post.getPostId());
										advisorPostRepo.save(advisorPost);
									}
								}

								rows.add(new AdvisorImportRowBean(advisor.getAdvisorId(), ticketId, token, fromDate, toDate, urls));
							} catch (ParseException e) {
								model.addAttribute("errorMsg", "Error reading file at line " + String.valueOf(lineNumber));
							}

						}

						lineNumber++;
					}

					// Add new comment to ticket
					User currentUser = SecurityUtil.getCurrentUser();
					if (currentUser != null) {
						commentForm.setOwner(currentUser.getUserId());
					}

					String tempContent = "We have sent you an advisor: <br>";

					for (AdvisorImportRowBean row : rows) {
						tempContent = tempContent + "<p><b>Token</b>: " + row.getToken() + "<br><b>From date</b>: " + row.getFromDate() + "<br><b>To date</b>: " + row.getToDate()
								+ "<br><b>Articles</b>: " + row.getUrls() + "<br>";
						tempContent = tempContent + "<b>Attached file: </b></p><a href='" + path + "/advisor/" + row.getAdvisorId() + "/viewAttached.in'>View</a></p>";
					}

					commentForm.setContent(tempContent);
					commentForm.setCreatedDate(new Date());
					commentForm.setTicketId(ticketId);
					commentForm.setTicket(ticketRepo.findOne(ticketId));

					commentService.addComment(commentForm, path);

				} catch (IOException e) {
					redirectAttributes.addFlashAttribute("errorMsg", "Error reading file at line " + String.valueOf(lineNumber));
				}

			} else {
				redirectAttributes.addFlashAttribute("errorMsg", "Please attach a advisor template file.");
			}

			return "redirect:" + "/admin/ticket/" + ticketId + "/review.in";

		} else {
			boolean hasErrors = result.hasErrors();

			if (!hasErrors) {
				Ticket ticket = ticketRepo.findOne(ticketId);

				if ("p".equals(type)) {
					User currentUser = SecurityUtil.getCurrentUser();
					if (currentUser != null) {
						commentForm.setOwner(currentUser.getUserId());
					}

					commentForm.setCreatedDate(new Date());
					commentForm.setTicketId(ticketId);
					commentForm.setTicket(ticket);

					commentService.addComment(commentForm, path);
					redirectAttributes.addFlashAttribute("successMsg", "New comment has been added.");

				} else {
					User currentUser = SecurityUtil.getCurrentUser();
					if (currentUser != null) {
						commentForm.setOwner(currentUser.getUserId());
					}

					commentForm.setCreatedDate(new Date());
					commentForm.setTicketId(ticketId);
					commentForm.setTicket(ticket);

					commentService.addComment(commentForm, path);

					ticket.setStatus(Ticket.STATUS_CLOSE);
					ticketRepo.save(ticket);

					redirectAttributes.addFlashAttribute("successMsg", "Ticket has been closed.");
				}

				return "redirect:" + "/admin/ticket/" + ticketId + "/review.in";
			}
		}

		return "redirect:" + "/admin/ticket/" + ticketId + "/review.in";
	}

	@RequestMapping(value = "/{type}/{refId}/add", method = RequestMethod.GET)
	public String addTicket(@PathVariable("type") String type, @PathVariable("refId") int refId, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		// Load ref item
		Object item = this.loadRefItem(type, refId);
		model.addAttribute("refTitle", this.getTitleFromItem(item));
		model.addAttribute("userId", this.getUserIdFromItem(item));
		model.addAttribute("type", type);
		model.addAttribute("refId", refId);

		return WebConstants.Views.TICKET_ADD;
	}

	@RequestMapping(value = "/{type}/{refId}/add", method = RequestMethod.POST)
	public String saveNewTicket(@PathVariable("type") String type, @PathVariable("refId") int refId, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,
			@Valid @ModelAttribute TicketForm ticketForm, BindingResult result, Model model) throws IOException {
		boolean hasErrors = result.hasErrors();

		if (!hasErrors) {
			Object item = this.loadRefItem(type, refId);

			if (item != null) {
				User currentUser = SecurityUtil.getCurrentUser();
				if (currentUser != null) {
					ticketForm.setFormUser(currentUser.getUserId());
				}

				ticketForm.setCreatedDate(new Date());
				ticketForm.setStatus(Ticket.STATUS_OPEN);
				ticketForm.setToUser(this.getUserIdFromItem(item));

				String path = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
				ticketService.saveTicketForm(ticketForm, path);
				redirectAttributes.addFlashAttribute("successMsg", "New ticket has been added.");

				return "redirect:" + "/admin/ticket/" + ticketForm.getTicketId() + "/review.in";
			}
		}

		Object item = this.loadRefItem(type, refId);
		model.addAttribute("refTitle", this.getTitleFromItem(item));
		model.addAttribute("userId", this.getUserIdFromItem(item));
		model.addAttribute("type", type);
		model.addAttribute("refId", refId);

		return WebConstants.Views.TICKET_ADD;
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editTicket(@PathVariable("id") int ticketId, HttpServletRequest request, HttpServletResponse response, @ModelAttribute TicketForm ticketForm, BindingResult result, Model model) {
		Ticket ticket = ticketRepo.findOne(ticketId);
		ticketForm = TicketForm.fromTicket(ticket);
		model.addAttribute("ticketForm", ticketForm);

		return WebConstants.Views.TICKET_EDIT;
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String saveEditTicket(@PathVariable("id") int ticketId, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,
			@Valid @ModelAttribute TicketForm ticketForm, BindingResult result, Model model) {
		boolean hasErrors = result.hasErrors();

		if (!hasErrors) {
			Ticket ticket = ticketRepo.findOne(ticketId);
			ticket.setTitle(ticketForm.getTitle());
			ticket.setContent(ticketForm.getContent());
			ticket.setStatus(ticketForm.getStatus());
			ticketRepo.save(ticket);
			redirectAttributes.addFlashAttribute("successMsg", "Ticket has been updated.");

			return "redirect:" + "/admin/ticket/" + String.valueOf(ticketId) + "/review.in";
		} else {
			Ticket ticket = ticketRepo.findOne(ticketId);
			ticketForm = TicketForm.fromTicket(ticket);
			model.addAttribute("ticketForm", ticketForm);
			model.addAttribute("ticketId", ticketId);
			return WebConstants.Views.TICKET_EDIT;
		}
	}

	@RequestMapping("/{userId}/{id}/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("userId") int userId, @PathVariable("id") int id, Model model) throws IOException {
		ticketRepo.delete(id);
		return "redirect:" + "/admin/ticket/1/list.in";
	}

	private Object loadRefItem(String type, int refId) {
		if ("career".equals(type)) {
			return careerRepo.findOne(refId);
		} else if ("company".equals(type)) {
			return companyRepo.findOne(refId);
		} else if ("resume".equals(type)) {
			return resumeRepo.findOne(refId);
		} else {
			return null;
		}
	}

	private String getTitleFromItem(Object item) {
		if (item instanceof Career) {
			return ((Career) item).getName();
		} else if (item instanceof Company) {
			return ((Company) item).getInfo();
		} else if (item instanceof Resume) {
			return ((Resume) item).getName();
		} else {
			return null;
		}
	}

	private Integer getUserIdFromItem(Object item) {
		if (item instanceof Career) {
			return ((Career) item).getUserId();
		} else if (item instanceof Company) {
			return ((Company) item).getUserId();
		} else if (item instanceof Resume) {
			return ((Resume) item).getUserId();
		} else {
			return null;
		}
	}

	private String getSlugFromUrl(String url) {
		String[] urlParts = url.trim().split("\\/");

		if (urlParts.length == 0) {
			return "";
		}

		String lastUrl = urlParts[urlParts.length - 1];

		if ("view.in".equals(lastUrl)) {
			lastUrl = urlParts[urlParts.length - 2];
		}

		String[] lastUrlParts = lastUrl.split("#");

		if (lastUrlParts.length == 0) {
			return "";
		}

		return lastUrlParts[lastUrlParts.length - 1];
	}
}
