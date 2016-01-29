package com.sharpinu.web;

import java.io.File;

import javax.servlet.ServletContextEvent;

import org.springframework.web.util.Log4jConfigListener;

public class SharpInULog4jConfigListener extends Log4jConfigListener{
	@Override
	public void contextInitialized(ServletContextEvent event) {
		File webPath = new File(event.getServletContext().getRealPath("/"));
		File rootPath = webPath.getParentFile();
		System.out.println("rootPath:" + rootPath.getAbsolutePath());
		System.setProperty("rootPath", rootPath.getAbsolutePath());
		super.contextInitialized(event);
	}

}
