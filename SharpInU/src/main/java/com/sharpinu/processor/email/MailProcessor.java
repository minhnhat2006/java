package com.sharpinu.processor.email;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.sharpinu.config.ConfigManager;
import com.sharpinu.constant.ConfigConstants;
import com.sharpinu.constant.MailTemplateConstants;
import com.sharpinu.email.IMailSender;
import com.sharpinu.persist.domain.Career;
import com.sharpinu.persist.domain.Company;
import com.sharpinu.persist.domain.Resume;
import com.sharpinu.persist.domain.Ticket;
import com.sharpinu.persist.domain.User;
import com.sharpinu.service.BaseService;
import com.sharpinu.util.Lib;
import com.sharpinu.web.form.CommentForm;
import com.sharpinu.web.form.ContactUsForm;

public class MailProcessor extends BaseService implements Runnable {
	public static enum MailType {
		CONTACT_US, NEW_RESUME, NEW_CAREER, NEW_COMPANY, NEW_RESUME_USER, NEW_CAREER_USER, NEW_COMPANY_USER, RESET_PASSWORD, PASSWORD_CHANGED, NEW_TICKET_USER, TICKET_REPLY_USER, TICKET_REPLY_ADMIN
	}

	private MailType mailType;
	private Object data;
	private IMailSender sender;
	private String defaultToEmail;
	private String contextPath = "";
	private User user;

	public MailProcessor(MailType mailType, Object datas, IMailSender sender) {
		super();
		this.mailType = mailType;
		this.data = datas;
		this.sender = sender;
		this.defaultToEmail = ConfigManager.getInstance().getProperty(ConfigConstants.SHARPINU_DEFAULT_TO_EMAIL);
	}

	public void run() {
		if (MailType.CONTACT_US.equals(this.mailType)) {
			this.sendMailByContactUs();
		} else if (MailType.NEW_CAREER.equals(this.mailType)) {
			this.sendMailbyCareer();
		} else if (MailType.NEW_COMPANY.equals(this.mailType)) {
			this.sendMailByCompany();
		} else if (MailType.NEW_RESUME.equals(this.mailType)) {
			this.sendMailByResume();
		} else if (MailType.NEW_CAREER_USER.equals(this.mailType)) {
			this.sendMailbyCareerToUser();
		} else if (MailType.NEW_COMPANY_USER.equals(this.mailType)) {
			this.sendMailByCompanyToUser();
		} else if (MailType.NEW_RESUME_USER.equals(this.mailType)) {
			this.sendMailByResumeToUser();
		} else if (MailType.RESET_PASSWORD.equals(this.mailType)) {
			this.sendResetPassword();
		} else if (MailType.PASSWORD_CHANGED.equals(this.mailType)) {
			this.sendPasswordChanged();
		} else if (MailType.NEW_TICKET_USER.equals(this.mailType)) {
			this.sendNewTicketToUser();
		} else if (MailType.TICKET_REPLY_USER.equals(this.mailType)) {
			this.sendNewTicketReplyToUser();
		} else if (MailType.TICKET_REPLY_ADMIN.equals(this.mailType)) {
			this.sendNewTicketReplyToAdmin();
		}
	}

	public void setCurrentUser(User user) {
		this.user = user;
	}

	private void sendMailByResume() {
		Resume resume = (Resume) data;
		try {
			Map<String, Object> templateVars = Lib.buildParamsMap(new String[] { "name", "title", "summary_skills", "objectives", "education", "experience", "address", "phone", "achievement" },
					new String[] { resume.getName(), resume.getCurrentTitle(), resume.getSummarySkill(), resume.getObjectives(), resume.getEducation(), resume.getExperience(), resume.getAddress(),
							resume.getPhone(), resume.getAchievement() });
			List<String> attachmentFiles = new ArrayList<String>();
			String image = resume.getImage();
			String cv = resume.getCv();
			if (StringUtils.hasText(image)) {
				attachmentFiles.add(image);
			}

			if (StringUtils.hasText(cv)) {
				attachmentFiles.add(cv);
			}
			sender.sendUsingFreeMarkerTemplateWithAttachments(this.defaultToEmail, "New Resume from " + resume.getName(), MailTemplateConstants.RESUME, templateVars, attachmentFiles);
		} catch (Exception e) {
			log.warn(String.format("Failed to send email of resume[resumeId= %s]", resume.getResumeId()), e);
		}
	}

	private void sendMailByContactUs() {
		ContactUsForm contactUsForm = (ContactUsForm) data;
		try {
			Map<String, Object> templateVars = Lib.buildParamsMap(new String[] { "name", "company", "email", "phone", "message" }, new String[] { contactUsForm.getName(), contactUsForm.getCompany(),
					contactUsForm.getEmail(), contactUsForm.getPhone(), contactUsForm.getMessage() });
			sender.sendUsingFreeMarkerTemplateWithAttachments(this.defaultToEmail, this.defaultToEmail, "New Contact from " + contactUsForm.getEmail(), MailTemplateConstants.CONTACT_US, templateVars,
					null);
		} catch (Exception e) {
			log.warn(String.format("Failed to send email of ContactUs[contactUsId= %s]", contactUsForm.getContactUsId()), e);
		}
	}

	private void sendMailByCompany() {
		Company company = (Company) data;
		try {
			Map<String, Object> templateVars = Lib.buildParamsMap(new String[] { "website", "email", "phone", "situation", "expectation" }, new String[] { company.getWebsite(), company.getEmail(),
					company.getPhone(), company.getSituation(), company.getExpectation() });
			List<String> attachmentFiles = new ArrayList<String>();
			String addtionalInfoFilePath = company.getAdditionalInfo();
			if (StringUtils.hasText(addtionalInfoFilePath)) {
				attachmentFiles.add(addtionalInfoFilePath);
			}
			sender.sendUsingFreeMarkerTemplateWithAttachments(this.defaultToEmail, "New Company from " + company.getEmail(), MailTemplateConstants.COMPANY, templateVars, attachmentFiles);
		} catch (Exception e) {
			log.warn(String.format("Failed to send email of Company[companyId= %s]", company.getCompanyId()), e);
		}
	}

	private void sendMailbyCareer() {
		Career career = (Career) data;
		try {
			Map<String, Object> templateVars = Lib.buildParamsMap(new String[] { "email", "phone", "situation", "expectation", "name" },
					new String[] { career.getEmail(), career.getPhone(), career.getSituation(), career.getExpectation(), career.getName() });
			List<String> attachmentFiles = new ArrayList<String>();
			String addtionalInfoFilePath = career.getAdditionalInfo();
			if (StringUtils.hasText(addtionalInfoFilePath)) {
				attachmentFiles.add(addtionalInfoFilePath);
			}
			sender.sendUsingFreeMarkerTemplateWithAttachments(this.defaultToEmail, "New Career from " + career.getEmail(), MailTemplateConstants.CAREER, templateVars, attachmentFiles);
		} catch (Exception e) {
			log.warn(String.format("Failed to send email of Career[careerId= %s]", career.getCareerId()), e);
		}
	}

	private void sendMailbyCareerToUser() {
		Career career = (Career) data;

		try {
			Map<String, Object> templateVars = Lib.buildParamsMap(new String[] { "name" }, new String[] { user.getFirstName() + " " + user.getLastName() });

			sender.sendUsingFreeMarkerTemplateWithAttachments(this.defaultToEmail, user.getUserEmail(), "SharpInU - Ask for Career Development", MailTemplateConstants.CAREER_USER, templateVars, null);
		} catch (Exception e) {
			log.warn(String.format("Failed to send email of Career[careerId= %s]", career.getCareerId()), e);
		}
	}

	private void sendMailByCompanyToUser() {
		Company company = (Company) data;

		try {
			Map<String, Object> templateVars = Lib.buildParamsMap(new String[] { "name" }, new String[] { user.getFirstName() + " " + user.getLastName() });

			sender.sendUsingFreeMarkerTemplateWithAttachments(this.defaultToEmail, user.getUserEmail(), "SharpInU - Ask for a Solution & Approach", MailTemplateConstants.COMPANY_USER, templateVars,
					null);
		} catch (Exception e) {
			log.warn(String.format("Failed to send email of Company[companyId= %s]", company.getCompanyId()), e);
		}
	}

	private void sendMailByResumeToUser() {
		Resume resume = (Resume) data;

		try {
			Map<String, Object> templateVars = Lib.buildParamsMap(new String[] { "name" }, new String[] { user.getFirstName() + " " + user.getLastName() });

			sender.sendUsingFreeMarkerTemplateWithAttachments(this.defaultToEmail, user.getUserEmail(), "SharpInU - Ask for a Better Resume", MailTemplateConstants.RESUME_USER, templateVars, null);
		} catch (Exception e) {
			log.warn(String.format("Failed to send email of resume[resumeId= %s]", resume.getResumeId()), e);
		}
	}

	private void sendResetPassword() {
		User user = (User) data;
		try {
			String passwordHash = user.getPasswordHash();
			if (!StringUtils.hasText(passwordHash)) {
				return;
			}
			String userName = user.getFirstName() + " " + user.getLastName();
			String passwordResetUrl = this.getContextPath() + "/sec/reset_password.in?hash=" + user.getPasswordHash();

			Map<String, Object> templateVars = Lib.buildParamsMap(new String[] { "userName", "passwordResetUrl" }, new String[] { userName, passwordResetUrl });
			sender.sendUsingFreeMarkerTemplateWithAttachments(this.defaultToEmail, user.getUserEmail(), "Forgot password", MailTemplateConstants.EMAIL_FORGOT_PASSWORD, templateVars, null);
		} catch (Exception e) {
			log.warn(String.format("Failed to send email forgot password[userEmail= %s]", user.getUserEmail()), e);
		}
	}

	private void sendPasswordChanged() {
		User user = (User) data;
		try {
			String userName = user.getFirstName() + " " + user.getLastName();
			Map<String, Object> templateVars = Lib.buildParamsMap(new String[] { "userName" }, new String[] { userName });
			sender.sendUsingFreeMarkerTemplateWithAttachments(this.defaultToEmail, user.getUserEmail(), "Password Changed", MailTemplateConstants.PASSWORD_CHANGED, templateVars, null);
		} catch (Exception e) {
			log.warn(String.format("Failed to send email password changed[userEmail= %s]", user.getUserEmail()), e);
		}
	}

	private void sendNewTicketToUser() {
		Ticket ticket = (Ticket) data;

		try {
			Map<String, Object> templateVars = Lib.buildParamsMap(new String[] { "name", "title", "content", "link" }, new String[] { user.getFirstName() + " " + user.getLastName(),
					ticket.getTitle(), ticket.getContent(), this.getContextPath() + "/ticket/" + ticket.getTicketId() + "/single_ticket.in" });

			sender.sendUsingFreeMarkerTemplateWithAttachments(this.defaultToEmail, user.getUserEmail(), "SharpInU - New request", MailTemplateConstants.TICKET_USER, templateVars, null);
		} catch (Exception e) {
			log.warn(String.format("Failed to send email of Ticket[companyId= %s]", ticket.getTicketId()), e);
		}
	}

	private void sendNewTicketReplyToUser() {
		CommentForm commentForm = (CommentForm) data;

		try {
			Map<String, Object> templateVars = Lib.buildParamsMap(new String[] { "name", "title", "content", "link" }, new String[] { user.getFirstName() + " " + user.getLastName(),
					commentForm.getTicket().getTitle(), commentForm.getContent(), this.getContextPath() + "/ticket/" + commentForm.getTicketId() + "/single_ticket.in" });

			sender.sendUsingFreeMarkerTemplateWithAttachments(this.defaultToEmail, user.getUserEmail(), "SharpInU - New comment for request", MailTemplateConstants.TICKET_REPLY_USER, templateVars,
					null);
			log.info(String.format("Send reply email of Ticket[ticketId= %s] to user %s", commentForm.getTicketId(), user.getUserEmail()));
		} catch (Exception e) {
			log.warn(String.format("Failed to send reply email of Ticket[ticketId= %s]", commentForm.getTicketId()), e);
		}
	}

	private void sendNewTicketReplyToAdmin() {
		CommentForm commentForm = (CommentForm) data;

		try {
			Map<String, Object> templateVars = Lib.buildParamsMap(new String[] { "name", "title", "content", "link" }, new String[] { user.getFirstName() + " " + user.getLastName(),
					commentForm.getTicket().getTitle(), commentForm.getContent(), this.getContextPath() + "/ticket/" + commentForm.getTicketId() + "/single_ticket.in" });

			sender.sendAsHtmlUsingFreeMarkerTemplate(this.defaultToEmail, this.defaultToEmail, "SharpInU - New comment for request", MailTemplateConstants.TICKET_REPLY_ADMIN, templateVars);
		} catch (Exception e) {
			log.warn(String.format("Failed to send reply email of Ticket[companyId= %s]", commentForm.getTicketId()), e);
		}
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
}
