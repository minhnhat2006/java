package com.sharpinu.web.common.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sharpinu.persist.domain.User;
import com.sharpinu.security.bean.SharpInUUserDetails;

/**
 * Contains utility methods for security
 * @author Mark
 *
 */
public class SecurityUtil {
	public static SharpInUUserDetails getUserDetail() {
		return (SharpInUUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
	}

	public static User getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			SharpInUUserDetails userDetail = (SharpInUUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User siuUser = userDetail.getSharpInUUser();
			return siuUser;
		}
		return null;
	}
	
	public static String generatePasswordHash() {
		String defaultPassword = "Sharpinu@com";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(defaultPassword);
		return hashedPassword;
	}
	
	public static String encryptPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encryptedPassword = passwordEncoder.encode(password);
		return encryptedPassword;
	}
	
	public static String getURLWithContextPath(HttpServletRequest request) {
		int serverPort = request.getServerPort();
		if (serverPort == 80) {
			return request.getScheme() + "://" + request.getServerName() + request.getContextPath();
		} else {
			return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		}
	}

}
