package com.sharpinu.web.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.platinum.pcv.PasswordComplexityException;
import com.sharpinu.constant.WebConstants;
import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.repositories.UserRepo;
import com.sharpinu.service.IUserService;
import com.sharpinu.util.DateUtil;
import com.sharpinu.util.Lib;
import com.sharpinu.util.PasswordEnforcementUtil;
import com.sharpinu.web.common.util.SecurityUtil;
import com.sharpinu.web.form.UserSignInForm;

@Controller
public class PasswordController extends BaseController {

	@Autowired
	UserRepo userRepo;

	@Autowired
	IUserService userService;

	@RequestMapping("/sec/forgot_password")
	public ModelAndView showForgotPasswordPage(@ModelAttribute UserSignInForm userSignInForm, HttpServletRequest request, HttpServletResponse response, Model model, SessionStatus status)
			throws IOException {
		status.setComplete();
		setFormToModel(userSignInForm, model);
		return new ModelAndView(WebConstants.Views.FORGOT_PASSWORD, "model", model);
	}

	@RequestMapping(value = "/sec/forgot_password", method = RequestMethod.POST)
	public ModelAndView postForgotPassword(@ModelAttribute UserSignInForm userSignInForm, HttpServletRequest request, HttpServletResponse response, Model model, BindingResult result)
			throws IOException {
		User user = userRepo.findByUserEmail(userSignInForm.getEmail());
		if (user == null) {
			result.reject("email", "Email isn't existing.");
			return new ModelAndView(WebConstants.Views.FORGOT_PASSWORD, "model", model);
		}

		userService.generatePasswordHash(user, SecurityUtil.getURLWithContextPath(request));
		return new ModelAndView(WebConstants.Views.PASSWORD_IS_SENT, "model", model);
	}

	@RequestMapping(value = "/sec/reset_password", method = RequestMethod.GET)
	public ModelAndView showResetPasswordForm(@ModelAttribute UserSignInForm userSignInForm, @RequestParam(value = "hash", required = false) String passwordHash, HttpServletRequest request,
			HttpServletResponse response, BindingResult result, Model model) throws IOException {

		User user = userRepo.findByPasswordHash(passwordHash);
		if (user == null) {
			result.reject("password.hash", "Invalid change password request.");
			return new ModelAndView(WebConstants.Views.SIGN_IN, "model", model);
		}

		if (isExpiredPasswordHash(user)) {
			result.reject("password.hash", "Changing password request was expired.");
			return new ModelAndView(WebConstants.Views.SIGN_IN, "model", model);
		}
		if (userSignInForm == null) {
			userSignInForm = new UserSignInForm();
		}

		userSignInForm.setPasswordHash(passwordHash);
		setFormToModel(userSignInForm, model);
		return new ModelAndView(WebConstants.Views.NEW_PASSWORD, "model", model);
	}

	private void setFormToModel(UserSignInForm userSignInForm, Model model) {
		model.addAttribute("userSignInForm", userSignInForm);
	}

	private boolean isExpiredPasswordHash(User user) {
		Timestamp passwordHashDate = user.getPasswordHashDate();
		Date expiredPasswordHashDate = DateUtil.addHoursForTimestamp(passwordHashDate, WebConstants.FixValue.PASSWORD_HASH_EXPIRED_IN_HOURS);
		Date currentDate = new Date();
		if (expiredPasswordHashDate.before(currentDate)) {
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/sec/new_password", method = RequestMethod.POST)
	public ModelAndView postNewPassword(@ModelAttribute UserSignInForm userSignInForm, HttpServletRequest request, HttpServletResponse response, Model model, BindingResult result) throws IOException {
		String passwordHash = userSignInForm.getPasswordHash();
		String newPassword = userSignInForm.getPassword();
		User user = userRepo.findByPasswordHash(passwordHash);

		if (user == null) {
			result.reject("password.hash", "Invalid change password request.");
			return new ModelAndView(WebConstants.Views.SIGN_IN, "model", model);
		}

		try {
			PasswordEnforcementUtil.validatePassword(newPassword);
		} catch (PasswordComplexityException e) {
			result.reject("password", Lib.getAllMessages(e));
			setFormToModel(userSignInForm, model);
			return new ModelAndView(WebConstants.Views.NEW_PASSWORD, "model", model);
		}

		if (isExpiredPasswordHash(user)) {
			result.reject("password.hash", "Changing password request was expired.");
			setFormToModel(userSignInForm, model);
			return new ModelAndView(WebConstants.Views.SIGN_IN, "model", model);
		}

		userService.changePassword(user, newPassword, SecurityUtil.getURLWithContextPath(request));
		model.addAttribute("showChangedPW", true);
		return new ModelAndView(WebConstants.Views.HOME, "model", model);
	}

	@ModelAttribute("userSignInForm")
	public UserSignInForm postJobForm() {
		return new UserSignInForm(); // populates form for the first time if its
										// null
	}

}