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
import com.sharpinu.persist.domain.Trend;
import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.repositories.TrendRepo;
import com.sharpinu.service.admin.ITrendAdminService;
import com.sharpinu.web.bean.TrendBean;
import com.sharpinu.web.common.util.SecurityUtil;
import com.sharpinu.web.controller.BaseController;
import com.sharpinu.web.form.admin.TrendForm;

@Controller
@RequestMapping(value = "/admin/trend")
public class TrendAdminController extends BaseController {

	@Autowired
	ITrendAdminService trendService;

	@Autowired
	TrendRepo trendRepo;

	@ModelAttribute
	public TrendForm trendForm() {
		return new TrendForm(); // populates form for the first time if
								// its null
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String viewTrendAdd(HttpServletRequest request, HttpServletResponse response, @ModelAttribute TrendForm trendForm, BindingResult result, Model model) {
		return WebConstants.Views.TREND_ADD;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveTrendAdd(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes, @Valid @ModelAttribute TrendForm trendForm, BindingResult result,
			Model model) {
		boolean hasErrors = result.hasErrors();

		if (!hasErrors) {
			User currentUser = SecurityUtil.getCurrentUser();
			if (currentUser != null) {
				trendForm.setUserId(currentUser.getUserId());
			}

			trendService.saveTrendForm(trendForm);
			redirectAttributes.addFlashAttribute("successMsg", "New trend has been added.");

			return "redirect:" + "/admin/trend/" + trendForm.getTrendId() + "/review.in";
		} else {
			return WebConstants.Views.TREND_ADD;
		}
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String viewTrendEdit(@PathVariable("id") int trendId, HttpServletRequest request, HttpServletResponse response, @ModelAttribute TrendForm trendForm, BindingResult result, Model model) {
		Trend trend = trendRepo.findOne(trendId);
		trendForm = TrendForm.fromTrend(trend);
		model.addAttribute("trendForm", trendForm);
		model.addAttribute("trendId", trendId);
		return WebConstants.Views.TREND_EDIT;
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String saveTrendEdit(@PathVariable("id") int trendId, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,
			@Valid @ModelAttribute TrendForm trendForm, BindingResult result, Model model) {
		boolean hasErrors = result.hasErrors();

		if (!hasErrors) {
			Trend trend = trendRepo.findOne(trendId);
			trendForm.setTrendId(trendId);
			trendForm.setUserId(trend.getUserId());
			trendForm.setCreatedDate(trend.getCreatedDate());
			trendService.saveTrendForm(trendForm);
			redirectAttributes.addFlashAttribute("successMsg", "New trend has been updated.");
			return "redirect:" + "/admin/trend/" + String.valueOf(trendId) + "/review.in";
		} else {
			Trend trend = trendRepo.findOne(trendId);
			trendForm = TrendForm.fromTrend(trend);
			model.addAttribute("trendForm", trendForm);
			model.addAttribute("trendId", trendId);
			return WebConstants.Views.TREND_EDIT;
		}
	}

	@RequestMapping(value = "/{id}/review", method = RequestMethod.GET)
	public String reviewTrendForm(@PathVariable("id") int trendId, HttpServletRequest request, HttpServletResponse response, Model model) {
		Trend trend = trendRepo.findOne(trendId);
		model.addAttribute("trend", trend);
		return WebConstants.Views.TREND_REVIEW;
	}

	@RequestMapping("/{pageIndex}/list")
	public String showTrends(HttpServletRequest request, HttpServletResponse response, @PathVariable("pageIndex") int pageIndex, Model model) throws IOException {

		Page<Trend> page = trendService.getTrendPageInfo(pageIndex - 1);
		List<TrendBean> trends = trendService.findTrendViewBean(pageIndex - 1, page);

		model.addAttribute("trends", trends);
		model.addAttribute("page", page);

		int current = page.getNumber() + 1;
		int begin = 1;
		int end = page.getTotalPages();
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);

		return WebConstants.Views.TREND_LIST;
	}

	@RequestMapping("/{id}/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id, Model model) throws IOException {
		trendRepo.delete(id);
		return "redirect:" + "/admin/trend/1/list.in";
	}
}
