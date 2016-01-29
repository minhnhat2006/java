package com.sharpinu.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sharpinu.config.ConfigManager;
import com.sharpinu.constant.ConfigConstants;
import com.sharpinu.web.bean.session.UserPreferences;
import com.sharpinu.web.common.util.SecurityUtil;

public abstract class BaseController {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	protected UserPreferences userPreferences;

	@ModelAttribute("isAdmin")
	public Boolean isAdmin() {
		return this.userPreferences.getIsAdmin();
	}

	@ModelAttribute("isLoggedIn")
	public Boolean isUserLogin() {
		return SecurityUtil.getCurrentUser() != null ? true : false;
	}

	@ModelAttribute("loggedUserFullName")
	public String loggedUserFullName() {
		return SecurityUtil.getCurrentUser() != null ? (SecurityUtil.getCurrentUser().getFirstName() + " " + SecurityUtil.getCurrentUser().getLastName()) : "";
	}

	@ModelAttribute("googleTrackingId")
	public String googleAnalyticsTrackingId() {
		return ConfigManager.getInstance().getProperty(ConfigConstants.GOOGLE_ANALYTICS_TRACKING_ID);
	}
}
