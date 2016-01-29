package com.sharpinu.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sharpinu.constant.WebConstants;
import com.sharpinu.persist.domain.OurPractice;
import com.sharpinu.persist.repositories.OurPracticeRepo;
import com.sharpinu.service.IOurPracticeService;
import com.sharpinu.util.Lib;
import com.sharpinu.web.bean.OurPracticeBean;
import com.sharpinu.web.bean.OurPracticePageBean;

@Controller
@RequestMapping("/our_practice")
public class OurPracticeController extends BaseController {

	@Autowired
	@Qualifier("ourPracticeRepo")
	OurPracticeRepo ourPracticeRepo;

	@Autowired
	@Qualifier("ourPracticeService")
	IOurPracticeService ourPracticeService;

	@RequestMapping(method = RequestMethod.GET)
	public String showDefault(HttpServletRequest request, HttpServletResponse response, Model model) {

		OurPracticePageBean ourPracticePageBean = ourPracticeService.loadDefault();

		if (ourPracticePageBean.getOurPracticeBeans().size() > 0) {
			model.addAttribute("ourPracticePageBean", ourPracticePageBean);

			// TODO: remove this
			for (OurPracticeBean ourPracticeBean : ourPracticePageBean.getOurPracticeBeans()) {
				if (StringUtils.isEmpty(ourPracticeBean.getSlug())) {
					ourPracticeBean.setSlug(Lib.toSlug(ourPracticeBean.getSummary()));

					OurPractice ourPractice = ourPracticeRepo.findOne(ourPracticeBean.getOurPracticeId());
					ourPractice.setSlug(ourPracticeBean.getSlug());
					ourPracticeRepo.save(ourPractice);
				}
			}
			// End TODO

			if (!ourPracticePageBean.getOurPracticeBeans().isEmpty()) {
				model.addAttribute("offset", ourPracticePageBean.getOurPracticeBeans().get(ourPracticePageBean.getOurPracticeBeans().size() - 1).getCreatedDate().getTime());
			}

			return WebConstants.Views.OUR_PRACTICE;

		} else {

			return WebConstants.Views.PAGE_UNDER_CONSTRUCTION;
		}
	}

	@RequestMapping("/our_practice/{term}/search")
	public String searchOurPractices(@PathVariable(value = "term") String term, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (term != null && !term.isEmpty()) {
			OurPracticePageBean ourPracticePageBean = ourPracticeService.searchOurPracticesSortByCreated(term);
			model.addAttribute("ourPracticePageBean", ourPracticePageBean);
			model.addAttribute("term", term);
			return WebConstants.Views.OUR_PRACTICE_SEARCH;
		} else {
			return "redirect:/our_practice.in";
		}
	}

	@RequestMapping("/{slug}/view")
	public String showOurPractice(@PathVariable(value = "slug") String slug, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		OurPracticeBean ourPractice = ourPracticeService.getOurPracticeBySlug(slug);

		if (ourPractice != null) {
			model.addAttribute("ourPractice", ourPractice);

			return WebConstants.Views.OUR_PRACTICE_VIEW;
		}

		return "redirect:/our_practice.in";
	}
}
