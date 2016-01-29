package com.sharpinu.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 *
 */
public class Lib {
	protected static final Logger log = LoggerFactory.getLogger(Lib.class);

	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	public static final String LOG_PART_SEPARATOR = "===================================================================";
	public static final String LOG_LINE_SEPARATOR = "----------------------------------------------------------";

	private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
	private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

	/**
	 * This method is used to get stack info like log4j
	 * 
	 * @param e
	 * @return
	 */
	public static String getStackTrace(Throwable e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}

	public static String getAllMessages(Throwable e) {
		String message = e.getMessage();
		Throwable cause = e.getCause();
		if (cause != null) {
			return message + " -> " + getAllMessages(cause);
		}
		return message;
	}

	public static Map<String, Object> buildParamsMap(String name, Object value) {
		return buildParamsMap(new String[] { name }, new Object[] { value });
	}

	/**
	 * Helper method used in DAO to build params map
	 * 
	 * @param names
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> buildParamsMap(String[] names, Object[] values) {
		if (names == null || names.length == 0 || values == null) {
			return Collections.EMPTY_MAP;
		}
		if (names.length != values.length) {
			throw new RuntimeException("Names and values must have the same size");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < names.length; i++) {
			map.put(names[i], values[i]);
		}
		return map;
	}

	public static String convertToString(Object object) {
		if (object == null) {
			return null;
		}
		String data = null;
		if (object instanceof String) {
			data = (String) object;
		} else {
			try {
				data = ToStringBuilder.reflectionToString(object);
			} catch (Exception e) {
				data = object.toString();
			}
		}
		return data;
	}

	public static final String getHostUrl(HttpServletRequest request) {
		StringBuffer redirectURL = new StringBuffer("");
		redirectURL.append(request.getScheme());
		redirectURL.append("://");
		redirectURL.append(request.getServerName());
		redirectURL.append(request.getServerPort() != 80 ? ":" + request.getServerPort() : "");
		return redirectURL.toString();
	}

	public static final String getSiteUrl(HttpServletRequest request) {
		return Lib.getHostUrl(request) + request.getContextPath();
	}

	public static int getIntValue(Object obj) {
		try {
			if (obj != null) {
				return Integer.parseInt(obj.toString().trim());
			}
		} catch (Exception e) {
			log.warn("Error while formatting the Object to int value.");
		}
		return 0;
	}

	public static String getStringLimitWords(String text, int numOfLimitWords) {
		text = text.trim();
		if (numOfLimitWords == 0) {
			return text;
		}
		text = getStringContentFromHtml(text);
		int numOfWords = text.isEmpty() ? 0 : text.split("\\s+").length;
		if (numOfWords > numOfLimitWords) {
			String[] words = text.split("\\s+");
			text = StringUtils.join(words, " ", 0, numOfLimitWords);
		}
		return text;
	}

	public static String getStringContentFromHtml(String html) {
		return StringEscapeUtils.unescapeHtml(html).replaceAll("\\<.*?>", "");
	}

	public static String toSlug(String input) {
		String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
		String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
		String slug = NONLATIN.matcher(normalized).replaceAll("");
		return slug.toLowerCase(Locale.ENGLISH);
	}
	
	public static boolean isListNotNullAndEmpty(List l) {
		return ((l != null && !l.isEmpty()) ? true : false);
	}
}
