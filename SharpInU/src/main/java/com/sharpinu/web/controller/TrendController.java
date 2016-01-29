package com.sharpinu.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sharpinu.constant.TrendConst;
import com.sharpinu.constant.WebConstants;
import com.sharpinu.persist.repositories.TrendRepo;
import com.sharpinu.service.ITrendService;
import com.sharpinu.web.bean.TrendPageBean;

@Controller
public class TrendController extends BaseController {

	@Autowired
	@Qualifier("trendRepo")
	TrendRepo trendRepo;

	@Autowired
	@Qualifier("trendService")
	ITrendService trendService;

	@RequestMapping("/trend")
	public String showMostRecentTrend(HttpServletRequest request, HttpServletResponse response, Model model) {
		TrendPageBean trendPageBean = trendService.getTrends(TrendConst.DEFAULT_POST_PAGE_SIZE);

		if (trendPageBean.getTrendBeans().size() > 0) {
			model.addAttribute("trendPageBean", trendPageBean);

			return WebConstants.Views.TREND;
		} else {
			return WebConstants.Views.PAGE_UNDER_CONSTRUCTION;
		}
	}

	@RequestMapping("/trend/{term}/search")
	public String searchTrends(@PathVariable(value = "term") String term, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (term != null && !term.isEmpty()) {
			TrendPageBean trendPageBean = trendService.searchTrendsSortByCreated(term);
			model.addAttribute("trendPageBean", trendPageBean);
			model.addAttribute("term", term);
			return WebConstants.Views.TREND_SEARCH;
		} else {
			return "redirect:/trend.in";
		}
	}

}
