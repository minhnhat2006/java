package com.sharpinu.web.dwr;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseDwrService {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	public HttpServletRequest getRequest() {
		WebContext ctx = WebContextFactory.get();
		return ctx.getHttpServletRequest();		
	}
}
