package com.sharpinu.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sharpinu.constant.StatisticConstants;
import com.sharpinu.constant.WebConstants;
import com.sharpinu.service.IUserService;
import com.sharpinu.service.admin.IStatisticService;
import com.sharpinu.web.form.UserSignUpForm;
import com.sharpinu.web.validator.UserSignUpFormValidator;

/**
 * Process sign-up.
 * 
 * Auto login after sign-up successfully.
 * 
 * @author Mark
 *
 */
@Controller
public class SignUpController extends BaseController {
	@Autowired
	@Qualifier("sharpInUAuthenticationManager")
	protected AuthenticationManager authenticationManager;

	@Autowired
	IUserService userService;

	@Autowired
	UserSignUpFormValidator userSignUpFormValidator;

	@Autowired
	@Qualifier("sharpInUUserDetailsService")
	private UserDetailsService userDetailsService;

	@Autowired
	IStatisticService statisticService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(userSignUpFormValidator);
	}

	@RequestMapping(value = "/sec/sign_up", method = RequestMethod.GET)
	public ModelAndView showSignUpPage(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UserSignUpForm userSignUpForm) throws IOException {
		Map<String, Object> model = new HashMap<String, Object>();
		// request.setAttribute("userSignUpForm", new UserSignUpForm());
		return new ModelAndView(WebConstants.Views.SIGN_UP, "model", model);
	}

	@RequestMapping(value = "/sec/sign_up", method = RequestMethod.POST)
	public String doSignUpUserAccount(HttpServletRequest request, HttpServletResponse response, @Valid @ModelAttribute UserSignUpForm userSignUpForm, BindingResult result) throws IOException {
		boolean hasError = result.hasErrors();
		if (!hasError) {
			userService.createUserAccount(userSignUpForm);
			this.authenticateUserAndSetSession(userSignUpForm.getEmail(), userSignUpForm.getPassword(), request);

			// Increase site's register count
			statisticService.increaseCount(StatisticConstants.SITE_USER_REGISTER_TOTAL);

			return WebConstants.Views.SIGN_UP_SUCCESS;
		} else {
			return WebConstants.Views.SIGN_UP;
		}
	}

	private void authenticateUserAndSetSession(String userEmail, String password, HttpServletRequest request) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userEmail, password);

		// generate session if one doesn't exist
		request.getSession();

		token.setDetails(new WebAuthenticationDetails(request));
		Authentication authenticatedUser = authenticationManager.authenticate(token);

		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
	}

}