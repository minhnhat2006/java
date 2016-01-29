package com.sharpinu.web.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sharpinu.constant.WebConstants;
import com.sharpinu.persist.domain.OurPracticeCategory;
import com.sharpinu.persist.repositories.OurPracticeCategoryRepo;
import com.sharpinu.service.admin.IOurPracticeCategoryAdminService;
import com.sharpinu.web.controller.BaseController;
import com.sharpinu.web.form.admin.OurPracticeCategoryForm;

@Controller
@RequestMapping(value = "/admin/our_practice_category")
public class OurPracticeCategoryController extends BaseController {

	@Autowired
	IOurPracticeCategoryAdminService ourPracticeCategoryService;

	@Autowired
	OurPracticeCategoryRepo ourPracticeCategoryRepo;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String viewOurPracticeCategoryAdd(HttpServletRequest request, HttpServletResponse response, @ModelAttribute OurPracticeCategoryForm ourPracticeCategoryForm, BindingResult result,
			Model model) {
		return WebConstants.Views.OUR_PRACTICE_CATAGORY_ADD;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveOurPracticeCategoryAdd(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,
			@Valid @ModelAttribute OurPracticeCategoryForm ourPracticeCategoryForm, BindingResult result, Model model) {
		boolean hasErrors = result.hasErrors();

		if (!hasErrors) {
			ourPracticeCategoryService.saveOurPracticeCategoryForm(ourPracticeCategoryForm);
			redirectAttributes.addFlashAttribute("successMsg", "New category has been added.");
			return "redirect:" + "/admin/our_practice_category/" + ourPracticeCategoryForm.getOurPracticeCategoryId() + "/review.in";
		} else {
			return WebConstants.Views.OUR_PRACTICE_CATAGORY_ADD;
		}
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String viewOurPracticeCategoryEdit(@PathVariable("id") int ourPracticeCategoryId, HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute OurPracticeCategoryForm ourPracticeCategoryForm, BindingResult result, Model model) {
		OurPracticeCategory ourPracticeCategory = ourPracticeCategoryRepo.findOne(ourPracticeCategoryId);
		ourPracticeCategoryForm = OurPracticeCategoryForm.fromOurPracticeCategory(ourPracticeCategory);
		model.addAttribute("ourPracticeCategoryForm", ourPracticeCategoryForm);
		model.addAttribute("ourPracticeCategoryId", ourPracticeCategoryId);
		return WebConstants.Views.OUR_PRACTICE_CATAGORY_EDIT;
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String saveOurPracticeCategoryEdit(@PathVariable("id") int ourPracticeCategoryId, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,
			@Valid @ModelAttribute OurPracticeCategoryForm ourPracticeCategoryForm, BindingResult result, Model model) {
		boolean hasErrors = result.hasErrors();

		if (!hasErrors) {
			OurPracticeCategory ourPracticeCategory = ourPracticeCategoryRepo.findOne(ourPracticeCategoryId);
			ourPracticeCategoryForm.setOurPracticeCategoryId(ourPracticeCategoryId);
			ourPracticeCategoryForm.setCreatedDate(ourPracticeCategory.getCreatedDate());
			ourPracticeCategoryService.saveOurPracticeCategoryForm(ourPracticeCategoryForm);
			redirectAttributes.addFlashAttribute("successMsg", "New category has been updated.");
			return "redirect:" + "/admin/our_practice_category/" + String.valueOf(ourPracticeCategoryId) + "/review.in";
		} else {
			return WebConstants.Views.OUR_PRACTICE_CATAGORY_EDIT;
		}
	}

	@RequestMapping(value = "/{id}/review", method = RequestMethod.GET)
	public String reviewOurPracticeCategoryForm(@PathVariable("id") int ourPracticeCategoryId, HttpServletRequest request, HttpServletResponse response, Model model) {
		OurPracticeCategory ourPracticeCategory = ourPracticeCategoryRepo.findOne(ourPracticeCategoryId);
		model.addAttribute("ourPracticeCategory", ourPracticeCategory);
		return WebConstants.Views.OUR_PRACTICE_CATAGORY_REVIEW;
	}

	@ModelAttribute
	public OurPracticeCategoryForm ourPracticeCategoryForm() {
		return new OurPracticeCategoryForm();
	}

	@RequestMapping("/{pageIndex}/list")
	public String showCategories(HttpServletRequest request, HttpServletResponse response, @PathVariable("pageIndex") int pageIndex, Model model) throws IOException {

		Page<OurPracticeCategory> page = ourPracticeCategoryService.getOurPracticeCategoryPageInfo(pageIndex - 1);
		List<OurPracticeCategory> ourPracticeCategories = ourPracticeCategoryService.findOurPracticeCategoryViewBean(pageIndex - 1, page);

		model.addAttribute("ourPracticeCategories", ourPracticeCategories);
		model.addAttribute("page", page);

		int current = page.getNumber() + 1;
		int begin = 1;
		int end = page.getTotalPages();
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);

		return WebConstants.Views.OUR_PRACTICE_CATEGORY_LIST;
	}

	@RequestMapping("/{id}/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id, Model model) throws IOException {
		ourPracticeCategoryRepo.delete(id);
		return "redirect:" + "/admin/our_practice_category/1/list.in";
	}

}
