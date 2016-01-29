package com.sharpinu.security.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.sharpinu.web.bean.session.UserPreferences;

@Component
public class SharpInULogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private UserPreferences userPreferences;

	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		this.userPreferences.setIsAdmin(false);
		this.setDefaultTargetUrl("/sec/sign_in.in");
		super.onLogoutSuccess(request, response, authentication);
	}
}
