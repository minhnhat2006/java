package com.sharpinu.web.config.security;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * Handler processing for Authentication Failure
 * 
 * @author Mark
 *
 */
public class SharpInUAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	private static final String FAILURE_URL = "/sec/sign_in.in?error=1";
	public SharpInUAuthenticationFailureHandler() {
		/**
		 * for keeping ModelAttribute in target model;
		 */
//		this.setUseForward(true);
		this.setDefaultFailureUrl(FAILURE_URL);
	}
}
