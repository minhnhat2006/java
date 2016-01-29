package com.sharpinu.web.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sharpinu.constant.WebConstants;
import com.sharpinu.service.admin.IReportService;
import com.sharpinu.util.DateUtil;
import com.sharpinu.web.bean.admin.OverviewStatisticBean;
import com.sharpinu.web.bean.admin.StatisticDetailsBean;
import com.sharpinu.web.controller.BaseController;

@Controller
@RequestMapping("/admin/report")
public class ReportController extends BaseController {

	@Autowired
	@Qualifier("reportService")
	IReportService reportService;

	@RequestMapping("/overview")
	public String showOverviewReport(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<OverviewStatisticBean> statisticBeans = reportService.getOverviewStatisticData();
		model.addAttribute("statisticBeans", statisticBeans);
		return WebConstants.Views.OVERVIEW_REPORT;
	}

	@RequestMapping("/details")
	public String showDetailsReport(@RequestParam(value = "fromDate", required = false) String fromDate,
			@RequestParam(value = "toDate", required = false) String toDate,
			@RequestParam(value = "pageTitle", required = false) String pageTitle,
			@RequestParam(value = "reportType", required = false) Integer reportType, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (fromDate == null) {
			fromDate = DateUtil
					.getDateBeforeDaysWithFormatString(System.currentTimeMillis(), WebConstants.FixValue.NO_DAYS_OF_WEEK, WebConstants.FixValue.DEFAULT_DATE_FORMAT);
		}
		if (toDate == null) {
			toDate = DateUtil
					.getDateBeforeDaysWithFormatString(System.currentTimeMillis(), 0, WebConstants.FixValue.DEFAULT_DATE_FORMAT);
		}
		Date from = null;
		Date to = null;
		if (!fromDate.equalsIgnoreCase(WebConstants.FixValue.ALL_STRING) && !toDate.equalsIgnoreCase(WebConstants.FixValue.ALL_STRING)) {
			from = DateUtil.convertStringToDate(fromDate, WebConstants.FixValue.DEFAULT_DATE_FORMAT);
			from = DateUtil.ChangeTimeOfDate(from, 0, 0, 0);
			to = DateUtil.convertStringToDate(toDate, WebConstants.FixValue.DEFAULT_DATE_FORMAT);
			to = DateUtil.ChangeTimeOfDate(to, 23, 59, 59);
		}
		List<StatisticDetailsBean> statisticBeans = null;
		String title = null;
		switch (reportType) {
		case WebConstants.FixValue.REGISTER_REPORT:
			statisticBeans = reportService.getRegisterStatisticData(from, to);
			title = WebConstants.FixValue.REGISTER_REPORT_TITLE;
			break;
		case WebConstants.FixValue.COMPANY_REPORT:
			statisticBeans = reportService.getCompanyStatisticData(from, to);
			title = WebConstants.FixValue.COMPANY_REPORT_TITLE;
			break;
		case WebConstants.FixValue.CAREER_REPORT:
			statisticBeans = reportService.getCareerStatisticData(from, to);
			title = WebConstants.FixValue.CAREER_REPORT_TITLE;
			break;
		case WebConstants.FixValue.RESUME_REPORT:
			statisticBeans = reportService.getResumeStatisticData(from, to);
			title = WebConstants.FixValue.RESUME_REPORT_TITLE;
			break;
		case WebConstants.FixValue.CONTACT_US_REPORT:
			statisticBeans = reportService.getContactUsStatisticData(from, to);
			title = WebConstants.FixValue.CONTACT_US_REPORT_TITLE;
			break;
		case WebConstants.FixValue.VIEW_REPORT:
			statisticBeans = reportService.getViewsStatisticData(from, to);
			title = WebConstants.FixValue.VIEW_REPORT_TITLE;
			break;
		case WebConstants.FixValue.VIEW_REPORT_DETAILS:
			statisticBeans = reportService.getViewDetailsStatisticData(from, to, pageTitle);
			title = WebConstants.FixValue.VIEW_REPORT_TITLE + ": " + pageTitle;
			model.addAttribute("pageTitle", pageTitle);
			break;
		}
		model.addAttribute("statisticBeans", statisticBeans);
		model.addAttribute("fromDate", fromDate);
		model.addAttribute("toDate", toDate);
		model.addAttribute("reportType", reportType);
		model.addAttribute("title", title);
		return WebConstants.Views.DETAILS_REPORT;
	}
}
