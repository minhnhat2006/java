package com.sharpinu.email;

import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.StringUtils;

import com.sharpinu.cache.CacheConstants;
import com.sharpinu.cache.CacheHelper;
import com.sharpinu.config.ConfigManager;
import com.sharpinu.constant.ConfigConstants;
import com.sharpinu.persist.domain.EmailSystem;
import com.sharpinu.persist.repositories.EmailSystemRepo;

/**
 * Mail sender used to send emails using our mail systems defined in
 * email_system table
 *
 */
public class MailSender implements IMailSender {
	protected final Log log = LogFactory.getLog(getClass());

	private CacheHelper cacheHelper = CacheHelper.getInstance();

	@Autowired
	@Qualifier("emailSystemRepo")
	private EmailSystemRepo emailSystemRepo;

	@Autowired
	@Qualifier("mailTemplateBuilder")
	private IMailTemplateBuilder mailTemplateBuilder;

	private void initAllEmailSystems() {
		MailSenderHelper.initAllEmailSystems(cacheHelper, emailSystemRepo);
	}

	public void send(String from, String to, String subject, String message) {
		this.send(from, new String[] { to }, subject, message);
	}

	public void send(String from, String[] to, String subject, String message) {
		JavaMailSender sender = getDefaultMailSender();
		MailSenderHelper.sendSimpleMessage(sender, from, to, subject, message);
	}

	public void sendWithAttachments(String from, String to, String subject, String message, List<String> attachments) {
		JavaMailSender sender = null;

		if (StringUtils.isEmpty(from)) {
			sender = this.getDefaultMailSender();
		} else {
			sender = this.getConsultancyMailSender();
		}

		MailSenderHelper.sendMessageWithAttachments(sender, from, new String[] { to }, subject, message, attachments);
	}

	public void sendAsHtmlWithAttachments(String from, String to, String subject, String message, List<String> attachments) {
		JavaMailSender sender = null;

		if (StringUtils.isEmpty(from)) {
			sender = this.getDefaultMailSender();
		} else {
			sender = this.getConsultancyMailSender();
		}

		MailSenderHelper.sendHtmlMessageWithAttachments(sender, from, new String[] { to }, subject, message, attachments);
	}

	public void sendAsHtml(String from, String to, String subject, String message) {
		JavaMailSender sender = null;

		if (StringUtils.isEmpty(from)) {
			sender = this.getDefaultMailSender();
		} else {
			sender = this.getConsultancyMailSender();
		}

		MailSenderHelper.sendHtmlMessageWithAttachments(sender, from, new String[] { to }, subject, message, null);
	}

	public void send(int emailSystemId, String from, String[] to, String subject, String message) {
		JavaMailSender sender = getMailSender(emailSystemId);
		MailSenderHelper.sendSimpleMessage(sender, from, to, subject, message);
	}

	public void send(MimeMessage mimeMessage) {
		JavaMailSender sender = getDefaultMailSender();
		sender.send(mimeMessage);
	}

	public void send(int emailSystemId, MimeMessage mimeMessage) {
		JavaMailSender sender = getMailSender(emailSystemId);
		sender.send(mimeMessage);
	}

	private JavaMailSender getMailSender(int emailSystemId) {
		String valueKey = emailSystemId + "";
		String cacheName = CacheConstants.MailUtil.GET_MAIL_SENDER_CACHE;
		SharpInUJavaMailSender sender = null;
		if (cacheHelper.containsKey(cacheName, valueKey)) {
			sender = (SharpInUJavaMailSender) cacheHelper.getValueFromCache(cacheName, valueKey);
		} else {
			EmailSystem emailSystem = emailSystemRepo.findOne(emailSystemId);
			if (emailSystem != null) {
				sender = MailSenderHelper.createNewSender(emailSystem);
				cacheHelper.putToCache(cacheName, valueKey, sender);
			} else {
				throw new RuntimeException("Could not find any EmailSystem with id [" + emailSystemId + "]");
			}
		}
		return sender;
	}

	private JavaMailSender getDefaultMailSender() {
		String valueKey = MailSenderHelper.DEFAULT_EMAIL_SYSTEM;
		String cacheName = CacheConstants.MailUtil.GET_MAIL_SENDER_CACHE;
		SharpInUJavaMailSender sender = null;

		if (cacheHelper.containsKey(cacheName, valueKey)) {
			sender = (SharpInUJavaMailSender) cacheHelper.getValueFromCache(cacheName, valueKey);
		} else {
			EmailSystem defaultEmailSystem = emailSystemRepo.findByIsDefaultTrue();
			if (defaultEmailSystem != null) {
				sender = MailSenderHelper.createNewSender(defaultEmailSystem);
				cacheHelper.putToCache(cacheName, valueKey, sender);
			} else {
				throw new RuntimeException("Could not find any default EmailSystem. Please create a new row in email_system table with IS_DEFAULT = 1");
			}
		}

		return sender;
	}

	private JavaMailSender getConsultancyMailSender() {
		String valueKey = MailSenderHelper.CONSULTANCY_EMAIL_SYSTEM;
		String cacheName = CacheConstants.MailUtil.GET_MAIL_SENDER_CACHE;
		SharpInUJavaMailSender sender = null;

		if (cacheHelper.containsKey(cacheName, valueKey)) {
			sender = (SharpInUJavaMailSender) cacheHelper.getValueFromCache(cacheName, valueKey);
		} else {
			EmailSystem defaultEmailSystem = emailSystemRepo.findOne(2);
			if (defaultEmailSystem != null) {
				sender = MailSenderHelper.createNewSender(defaultEmailSystem);
				cacheHelper.putToCache(cacheName, valueKey, sender);
			} else {
				throw new RuntimeException("Could not find any default EmailSystem. Please create a new row in email_system table with IS_DEFAULT = 1");
			}
		}

		return sender;
	}

	/**
	 * Send email using FreeMarker to build message from a given template
	 * 
	 * Note: not work on amazon ec2
	 * 
	 * @param to
	 * @param subject
	 * @param templateName
	 * @param templateVars
	 */
	public void sendUsingFreeMarkerTemplate(String from, String to, String subject, String templateName, Map<String, Object> templateVars) {
		this.sendUsingFreeMarkerTemplate(from, new String[] { to }, subject, templateName, templateVars);
	}

	/**
	 * Send email using FreeMarker to build message from a given template
	 * 
	 * @param to
	 * @param subject
	 * @param templateName
	 * @param templateVars
	 */
	public void sendUsingFreeMarkerTemplateWithAttachments(String to, String subject, String templateName, Map<String, Object> templateVars, List<String> attachmentFiles) {
		String message = mailTemplateBuilder.buildMessageFromTemplate(templateName, templateVars);
		this.sendWithAttachments(ConfigManager.getInstance().getProperty(ConfigConstants.SHARPINU_DEFAULT_TO_EMAIL), to, subject, message, attachmentFiles);
	}

	/**
	 * Send email using FreeMarker to build message from a given template
	 */
	public void sendUsingFreeMarkerTemplateWithAttachments(String from, String to, String subject, String templateName, Map<String, Object> templateVars, List<String> attachmentFiles) {
		String message = mailTemplateBuilder.buildMessageFromTemplate(templateName, templateVars);
		this.sendAsHtmlWithAttachments(from, to, subject, message, attachmentFiles);
	}

	/**
	 * Send email using FreeMarker to build message from a given template
	 * 
	 * Note: not work on amazon ec2
	 * 
	 * @param to
	 * @param subject
	 * @param templateName
	 * @param templateVars
	 */
	public void sendUsingFreeMarkerTemplate(String from, String[] to, String subject, String templateName, Map<String, Object> templateVars) {
		String message = mailTemplateBuilder.buildMessageFromTemplate(templateName, templateVars);
		this.send(from, to, subject, message);
	}

	/**
	 * Send email using FreeMarker to build message from a given template
	 * 
	 * @param to
	 * @param subject
	 * @param templateName
	 * @param templateVars
	 */
	public void sendAsHtmlUsingFreeMarkerTemplate(String from, String to, String subject, String templateName, Map<String, Object> templateVars) {
		String message = mailTemplateBuilder.buildMessageFromTemplate(templateName, templateVars);
		this.sendAsHtml(from, to, subject, message);
	}

	/**
	 * Send email using a given email-system and FreeMarker to build message
	 * from a given template
	 * 
	 * Note: not work on amazon ec2
	 * 
	 * @param to
	 * @param subject
	 * @param templateName
	 * @param templateVars
	 */
	public void sendUsingFreeMarkerTemplate(int emailSystemId, String from, String[] to, String subject, String templateName, Map<String, Object> templateVars) {
		String message = mailTemplateBuilder.buildMessageFromTemplate(templateName, templateVars);
		this.send(emailSystemId, from, to, subject, message);
	}

}
