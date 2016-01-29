package com.sharpinu.security.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.sharpinu.web.bean.session.UserPreferences;

@Component
public class SharpInUAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private UserPreferences userPreferences;

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		try {
			super.onAuthenticationSuccess(request, response, authentication);

			for (GrantedAuthority auth : authentication.getAuthorities()) {
				if ("ROLE_ADMIN".equals(auth.getAuthority())) {
					this.userPreferences.setIsAdmin(true);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Unexpected error when logging in user [" + authentication.getName() + "]", e);
		}
	}
}
