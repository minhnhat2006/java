package com.sharpinu.email;

import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;

import com.sharpinu.cache.CacheConstants;
import com.sharpinu.cache.CacheHelper;
import com.sharpinu.persist.domain.EmailSystem;
import com.sharpinu.persist.repositories.EmailSystemRepo;
import com.sharpinu.util.Lib;

public class MailSenderHelper {
	static final String DEFAULT_EMAIL_SYSTEM = "DEFAULT_EMAIL_SYSTEM";
	static final String CONSULTANCY_EMAIL_SYSTEM = "CONSULTANCY_EMAIL_SYSTEM";
	static final int SMTP_NON_SSL_PORT = 25;

	static void sendSimpleMessage(JavaMailSender sender, String from, String[] to, String subject, String message) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(from);
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		sender.send(mailMessage);
	}

	static void sendMessageWithAttachments(JavaMailSender sender, String from, String[] to, String subject, String message, List<String> attachments) {
		MimeMessage mineMessage = sender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mineMessage, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(message);

			if (attachments != null) {
				for (String filePath : attachments) {
					FileSystemResource file = new FileSystemResource(filePath);
					helper.addAttachment(file.getFilename(), file);
				}
			}
		} catch (MessagingException e) {
			throw new MailParseException(e);
		}

		sender.send(mineMessage);
	}

	static void sendHtmlMessageWithAttachments(JavaMailSender sender, String from, String[] to, String subject, String message, List<String> attachments) {
		MimeMessage mineMessage = sender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mineMessage, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(message, true);

			if (attachments != null) {
				for (String filePath : attachments) {
					FileSystemResource file = new FileSystemResource(filePath);
					helper.addAttachment(file.getFilename(), file);
				}
			}
		} catch (MessagingException e) {
			throw new MailParseException(e);
		}

		sender.send(mineMessage);
	}

	static void initAllEmailSystems(CacheHelper cacheHelper, EmailSystemRepo emailSystemRepo) {
		List<EmailSystem> list = emailSystemRepo.findAll();
		String cacheName = CacheConstants.MailUtil.GET_MAIL_SENDER_CACHE;
		SharpInUJavaMailSender defaultSender = null;
		for (EmailSystem emailSystem : list) {
			SharpInUJavaMailSender sender = createNewSender(emailSystem);
			if (defaultSender == null && emailSystem.isDefault()) {
				/**
				 * Take the first default EmailSystem and put it into cache
				 */
				defaultSender = sender;
			}
			cacheHelper.putToCache(cacheName, emailSystem.getEmailSystemId() + "", sender);
		}
		if (defaultSender == null && list != null && !list.isEmpty()) {
			/**
			 * If no default sender found, we take the very first one as default
			 */
			defaultSender = (SharpInUJavaMailSender) cacheHelper.getValueFromCache(cacheName, list.get(0).getEmailSystemId() + "");
		}
		if (defaultSender != null) {
			cacheHelper.putToCache(cacheName, DEFAULT_EMAIL_SYSTEM, defaultSender);
		}
	}

	static SharpInUJavaMailSender createNewSender(EmailSystem emailSystem) {
		try {
			SharpInUJavaMailSender sender = new SharpInUJavaMailSender();
			sender.setHost(emailSystem.getHost());
			sender.setPort(emailSystem.getPort());
			sender.setUsername(emailSystem.getUserName());
			sender.setPassword(emailSystem.getPassword());
			Properties javaMailProperties = new Properties();
			javaMailProperties.put("mail.debug", "true");
			javaMailProperties.setProperty("mail.transport.protocol", emailSystem.getProtocol());
			javaMailProperties.setProperty("mail.smtp.auth", "true");

			if (StringUtils.hasText(emailSystem.getSenderEmail())) {
				javaMailProperties.setProperty("mail.smtp.from", emailSystem.getSenderEmail());
			}
			/*
			 * Fix org.springframework.mail.MailSendException: Mail server
			 * connection failed; nested exception is
			 * javax.mail.MessagingException: Can't send command to SMTP host;
			 */
			if (emailSystem.getPort() == SMTP_NON_SSL_PORT) {
				javaMailProperties.setProperty("mail.smtp.ssl.enable", "false");
				javaMailProperties.setProperty("mail.smtp.starttls.enable", "false");
			} else {
				javaMailProperties.setProperty("mail.smtp.starttls.enable", "true");
			}
			sender.setJavaMailProperties(javaMailProperties);
			return sender;
		} catch (Exception e) {
			throw new RuntimeException("Failed to create sender to [" + Lib.convertToString(emailSystem) + "]", e);
		}
	}
}
