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
import com.sharpinu.persist.domain.OurPractice;
import com.sharpinu.persist.domain.OurPracticeCategory;
import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.repositories.OurPracticeRepo;
import com.sharpinu.service.admin.IOurPracticeAdminService;
import com.sharpinu.service.admin.IOurPracticeCategoryAdminService;
import com.sharpinu.web.bean.OurPracticeBean;
import com.sharpinu.web.common.util.SecurityUtil;
import com.sharpinu.web.controller.BaseController;
import com.sharpinu.web.form.admin.OurPracticeForm;

@Controller
@RequestMapping(value = "/admin/our_practice")
public class OurPracticeAdminController extends BaseController {

	@Autowired
	IOurPracticeCategoryAdminService ourPracticeCategoryService;

	@Autowired
	IOurPracticeAdminService ourPracticeService;

	@Autowired
	OurPracticeRepo ourPracticeRepo;

	@ModelAttribute
	public OurPracticeForm ourPracticeForm() {
		return new OurPracticeForm(); // populates form for the first time if
										// its null
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String viewOurPracticeAdd(HttpServletRequest request, HttpServletResponse response, @ModelAttribute OurPracticeForm ourPracticeForm, BindingResult result, Model model) {
		this.setModelAttributesForOurPracticeJob(ourPracticeForm, model);
		return WebConstants.Views.OUR_PRACTICE_ADD;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveOurPracticeAdd(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes, @Valid @ModelAttribute OurPracticeForm ourPracticeForm,
			BindingResult result, Model model) {
		boolean hasErrors = result.hasErrors();

		if (!hasErrors) {
			User currentUser = SecurityUtil.getCurrentUser();
			if (currentUser != null) {
				ourPracticeForm.setUserId(currentUser.getUserId());
			}
			OurPracticeCategory ourPracticeCategory = ourPracticeCategoryService.findById(ourPracticeForm.getOurPracticeCategoryId());
			ourPracticeForm.setOurPracticeCategory(ourPracticeCategory);
			ourPracticeService.saveOurPracticeForm(ourPracticeForm);
			redirectAttributes.addFlashAttribute("successMsg", "New ourPractice has been added.");

			return "redirect:" + "/admin/our_practice/" + ourPracticeForm.getOurPracticeId() + "/review.in";
		} else {
			this.setModelAttributesForOurPracticeJob(ourPracticeForm, model);
			return WebConstants.Views.OUR_PRACTICE_ADD;
		}
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String viewOurPracticeEdit(@PathVariable("id") int ourPracticeId, HttpServletRequest request, HttpServletResponse response, @ModelAttribute OurPracticeForm ourPracticeForm,
			BindingResult result, Model model) {
		this.setModelAttributesForOurPracticeJob(ourPracticeForm, model);

		OurPractice ourPractice = ourPracticeRepo.findOne(ourPracticeId);
		ourPracticeForm = OurPracticeForm.fromOurPractice(ourPractice);
		ourPracticeForm.setOurPracticeCategoryId(ourPractice.getOurPracticeCategory().getOurPracticeCategoryId());
		model.addAttribute("ourPracticeForm", ourPracticeForm);
		model.addAttribute("ourPracticeId", ourPracticeId);
		return WebConstants.Views.OUR_PRACTICE_EDIT;
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String saveOurPracticeEdit(@PathVariable("id") int ourPracticeId, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,
			@Valid @ModelAttribute OurPracticeForm ourPracticeForm, BindingResult result, Model model) {
		boolean hasErrors = result.hasErrors();

		if (!hasErrors) {
			OurPractice ourPractice = ourPracticeRepo.findOne(ourPracticeId);
			OurPracticeCategory ourPracticeCategory = ourPracticeCategoryService.findById(ourPracticeForm.getOurPracticeCategoryId());
			ourPracticeForm.setOurPracticeId(ourPracticeId);
			ourPracticeForm.setOurPracticeCategory(ourPracticeCategory);
			ourPracticeForm.setUserId(ourPractice.getUserId());
			ourPracticeForm.setCreatedDate(ourPractice.getCreatedDate());
			ourPracticeService.saveOurPracticeForm(ourPracticeForm);
			redirectAttributes.addFlashAttribute("successMsg", "New ourPractice has been updated.");
			return "redirect:" + "/admin/our_practice/" + String.valueOf(ourPracticeId) + "/review.in";
		} else {
			this.setModelAttributesForOurPracticeJob(ourPracticeForm, model);
			OurPractice ourPractice = ourPracticeRepo.findOne(ourPracticeId);
			ourPracticeForm = OurPracticeForm.fromOurPractice(ourPractice);
			model.addAttribute("ourPracticeForm", ourPracticeForm);
			model.addAttribute("ourPracticeId", ourPracticeId);
			return WebConstants.Views.OUR_PRACTICE_EDIT;
		}
	}

	@RequestMapping(value = "/{id}/review", method = RequestMethod.GET)
	public String reviewOurPracticeForm(@PathVariable("id") int ourPracticeId, HttpServletRequest request, HttpServletResponse response, Model model) {
		OurPractice ourPractice = ourPracticeRepo.findOne(ourPracticeId);
		model.addAttribute("ourPractice", ourPractice);
		return WebConstants.Views.OUR_PRACTICE_REVIEW;
	}

	private void setModelAttributesForOurPracticeJob(OurPracticeForm ourPracticeForm, Model model) {
		List<OurPracticeCategory> categories = ourPracticeCategoryService.findAll();
		OurPracticeCategory selectOurPracticeCategory = new OurPracticeCategory();
		selectOurPracticeCategory.setOurPracticeCategoryId(0);
		selectOurPracticeCategory.setName("Select");
		categories.add(0, selectOurPracticeCategory);
		model.addAttribute("categories", categories);
	}

	@RequestMapping("/{pageIndex}/list")
	public String showOurPractices(HttpServletRequest request, HttpServletResponse response, @PathVariable("pageIndex") int pageIndex, Model model) throws IOException {

		Page<OurPractice> page = ourPracticeService.getOurPracticePageInfo(pageIndex - 1);
		List<OurPracticeBean> ourPractices = ourPracticeService.findOurPracticeViewBean(pageIndex - 1, page);

		model.addAttribute("ourPractices", ourPractices);
		model.addAttribute("page", page);

		int current = page.getNumber() + 1;
		int begin = 1;
		int end = page.getTotalPages();
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);

		return WebConstants.Views.OUR_PRACTICE_LIST;
	}

	@RequestMapping("/{id}/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id, Model model) throws IOException {
		ourPracticeRepo.delete(id);
		return "redirect:" + "/admin/our_practice/1/list.in";
	}
}
