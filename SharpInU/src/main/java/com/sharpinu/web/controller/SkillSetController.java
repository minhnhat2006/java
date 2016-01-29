package com.sharpinu.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sharpinu.constant.WebConstants;
import com.sharpinu.persist.domain.WorkCategory;
import com.sharpinu.service.IWorkCategoryService;

/**
 * Controller for actions relate to skills.
 * 
 * @author Mark
 *
 */
@Controller
public class SkillSetController extends BaseController {
	@Autowired
	private IWorkCategoryService workCategoryService;
	@RequestMapping("/admin/skills/list")
	public String viewSkillSet(HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException {
		List<WorkCategory> workCategories = workCategoryService.findAll();
		model.addAttribute("workCategories",workCategories);
		return WebConstants.Views.SKILL_SET;
	}
	
}
