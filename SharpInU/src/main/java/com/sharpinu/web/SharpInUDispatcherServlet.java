package com.sharpinu.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;
import org.springframework.web.util.WebUtils;

import com.sharpinu.cache.CacheHelper;
import com.sharpinu.constant.StatisticConstants;
import com.sharpinu.constant.WebConstants;
import com.sharpinu.service.admin.IStatisticService;
import com.sharpinu.util.Lib;

/**
 * This class used to clear cache and debug request's details
 * 
 * @author Mark
 *
 */
public class SharpInUDispatcherServlet extends DispatcherServlet {
	public static final String APP_EXCEPTION = "APP_EXCEPTION";

	private static final long serialVersionUID = -5662113797950414637L;
	private UrlPathHelper urlPathHelper = new UrlPathHelper();

	@Override
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		addHttpRequestHeadersToResponse(response);
		/**
		 * 1. first we must check if this is cache-related request, we do: +
		 * perform the specified cache request + ignore the chain and write info
		 * msg
		 */
		if (CacheHelper.isCacheRequest(request)) {
			CacheHelper.performCacheRequest(request, response);
			return;
		}

		try {
			super.doService(request, response);

			// Increase site view count
			String uri = request.getRequestURI();
			String contextPath = request.getContextPath();

			if (!StringUtils.isEmpty(uri) && !uri.startsWith(contextPath + "/admin/") && !uri.startsWith(contextPath + "/sec/")) {
				IStatisticService statisticService = (IStatisticService) this.getWebApplicationContext().getBean(IStatisticService.class);
				statisticService.increaseCount(StatisticConstants.SITE_VIEW_TOTAL);
			}
		} catch (Exception e) {
			/**
			 * JSP may throw errors while rendering view. Instead of letting
			 * WebServer to show ugly text to users, we catch and resolve it
			 */
			HandlerExecutionChain mappedHandler = getHandler(request);
			Object handler = mappedHandler != null ? mappedHandler.getHandler() : null;
			ModelAndView errModelAndView = processHandlerException(request, response, handler, e);
			if (errModelAndView != null) {
				render(errModelAndView, request, response);
			}
		}

	}

	private void addHttpRequestHeadersToResponse(HttpServletResponse response) {
		response.addHeader(WebConstants.HttpRequestHeaders.CACHE_CONTROL, "no-cache");

	}

	@Override
	protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			super.render(mv, request, response);
		} finally {
		}
	}

	@Override
	protected ModelAndView processHandlerException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		/**
		 * 1. un-expected exception, log the error so developers can debug/fix
		 * it
		 */
		logger.error(ex);
		ModelAndView modelAndView = null;
		try {
			modelAndView = super.processHandlerException(request, response, handler, ex);
			/**
			 * Remove the attributes set by super.processHandlerException()
			 * method above to avoid Tomcat container handling the error. So we
			 * can use our own page to handle it
			 */
			request.removeAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE);
			request.removeAttribute(WebUtils.ERROR_EXCEPTION_TYPE_ATTRIBUTE);
			request.removeAttribute(WebUtils.ERROR_MESSAGE_ATTRIBUTE);
			request.removeAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE);
			request.removeAttribute(WebUtils.ERROR_REQUEST_URI_ATTRIBUTE);
			request.removeAttribute(WebUtils.ERROR_SERVLET_NAME_ATTRIBUTE);
		} catch (Exception e) {
			/**
			 * no handler found for this kind of exception
			 */
			logger.error("No handler found for the exception " + ex.getClass().getName());
		}
		/**
		 * add the exception to the request so jsp can get it and show to user
		 */
		request.setAttribute(APP_EXCEPTION, ex);
		String message = Lib.getAllMessages(ex);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("message", message);
		if (modelAndView == null) {
			modelAndView = new ModelAndView(WebConstants.Views.ERRORS);
		}
		modelAndView.addObject("model", model);
		return modelAndView;
	}

	@Override
	protected void noHandlerFound(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.error("No handler found for the request: " + urlPathHelper.getRequestUri(request));
		/**
		 * No handler found, we redirect to PageNotFound
		 */
		response.sendRedirect(request.getContextPath() + "/" + WebConstants.Views.PAGE_NOT_FOUND + WebConstants.PAGE_SUFFIX);
	}
}
