package com.sharpinu.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;

public class SharpInUContextLoaderListener extends org.springframework.web.context.ContextLoaderListener {
	protected final Log log = LogFactory.getLog(getClass());

	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext context) {
		return super.initWebApplicationContext(context);
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			super.contextInitialized(event);
		} catch (Exception e) {
			throw new RuntimeException("Error while start context", e);
		}

	}

}
