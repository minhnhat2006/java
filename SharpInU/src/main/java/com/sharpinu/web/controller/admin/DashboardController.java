package com.sharpinu.web.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sharpinu.constant.WebConstants;
import com.sharpinu.service.admin.IStatisticService;
import com.sharpinu.web.bean.admin.StatisticBean;
import com.sharpinu.web.controller.BaseController;
import com.sharpinu.web.form.admin.DashboardForm;

@Controller
public class DashboardController extends BaseController {

	@Autowired
	IStatisticService statisticService;

	@RequestMapping(value = "/admin/dashboard", method = RequestMethod.GET)
	public String getDashboardForm(HttpServletRequest request, HttpServletResponse response, Model model) {
		StatisticBean statistic = statisticService.getSiteStatistic();
		model.addAttribute("statistic", statistic);

		return WebConstants.Views.ADMIN_DASHBOARD_VIEW;
	}

	@ModelAttribute
	public DashboardForm dashboardForm() {
		return new DashboardForm();
	}

}
