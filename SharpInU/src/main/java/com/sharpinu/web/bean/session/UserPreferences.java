package com.sharpinu.web.bean.session;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserPreferences implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean isAdmin = false;

	private String advisorToken = "";

	private String postSlugBeingView = "";

	public boolean getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getAdvisorToken() {
		return this.advisorToken;
	}

	public void setAdvisorToken(String token) {
		this.advisorToken = token;
	}

	public String getPostSlugBeingView() {
		return this.postSlugBeingView;
	}

	public void setPostSlugBeingView(String postSlugBeingView) {
		this.postSlugBeingView = postSlugBeingView;
	}
}
