package com.sharpinu.email;

import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

public interface IMailSender {
	/**
	 * Send email using FreeMarker to build message from a given template
	 * 
	 * @param to
	 * @param subject
	 * @param templateName
	 * @param templateVars
	 */
	void sendUsingFreeMarkerTemplate(String from, String to, String subject, String templateName, Map<String, Object> templateVars);

	/**
	 * Send email using FreeMarker to build message from a given template
	 * 
	 * @param to
	 * @param subject
	 * @param templateName
	 * @param templateVars
	 */
	void sendUsingFreeMarkerTemplate(String from, String[] to, String subject, String templateName, Map<String, Object> templateVars);

	/**
	 * Send email using FreeMarker to build message from a given template
	 * 
	 * @param emailSystemId
	 * @param to
	 * @param subject
	 * @param templateName
	 * @param templateVars
	 */
	void sendUsingFreeMarkerTemplate(int emailSystemId, String from, String[] to, String subject, String templateName, Map<String, Object> templateVars);

	/**
	 * Send simple message to a recipient using the default email system
	 * 
	 * @param to
	 * @param message
	 */
	void send(String from, String to, String subject, String message);

	/**
	 * Send simple message to a recipient using the default email system
	 * 
	 * @param to
	 * @param subject
	 * @param message
	 */
	void send(String from, String[] to, String subject, String message);

	/**
	 * Send message to a recipient using a given email system
	 * 
	 * @param emailSystemId
	 *            EmailSystem used to send this message
	 * @param to
	 * @param subject
	 * @param message
	 */
	void send(int emailSystemId, String from, String[] to, String subject, String message);

	/**
	 * Send MimeMessage using the default email system
	 * 
	 * @param mimeMessage
	 */
	void send(MimeMessage mimeMessage);

	/**
	 * Send MimeMessage using a given email system
	 * 
	 * @param emailSystemId
	 * @param mimeMessage
	 */
	void send(int emailSystemId, MimeMessage mimeMessage);

	void sendUsingFreeMarkerTemplateWithAttachments(String to, String subject, String templateName, Map<String, Object> templateVars, List<String> attachmentFiles);

	void sendUsingFreeMarkerTemplateWithAttachments(String from, String to, String subject, String templateName, Map<String, Object> templateVars, List<String> attachmentFiles);

	void sendAsHtmlUsingFreeMarkerTemplate(String from, String to, String subject, String templateName, Map<String, Object> templateVars);
}
