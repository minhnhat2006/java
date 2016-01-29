package com.sharpinu.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sharpinu.constant.WebConstants;
import com.sharpinu.persist.repositories.UserRepo;
import com.sharpinu.web.form.UserSignInForm;

/**
 * Process sign-in
 * 
 * @author Mark
 *
 */
@Controller
public class SignInController extends BaseController {

	@Autowired
	UserRepo userRepo;

	@RequestMapping("/sec/sign_in")
	public String showLoginPage(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute UserSignInForm userSignInForm,
			@RequestParam(value = "error", required = false) String error,
			BindingResult result, Model model) throws IOException {
		if (error != null) {
			result.reject("login.failed",
					"Email and password do not match. Please try again.");
		} else {
			userSignInForm.setRememberMe(true);
		}

		model.addAttribute("userSignInForm", userSignInForm);
		return WebConstants.Views.SIGN_IN;
	}

}
