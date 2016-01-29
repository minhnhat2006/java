package com.sharpinu.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sharpinu.constant.WebConstants;

/**
 * This controller is for displaying skills of user
 * @author Mark
 *
 */
@Controller
public class UserSkillController extends BaseController {
	@RequestMapping("/admin/skills")
	public String viewUserSkills(HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException {
		return WebConstants.Views.USER_SKILLS;
	}
}