package com.sharpinu.service.admin;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharpinu.constant.WebConstants;
import com.sharpinu.ga.GoogleAnalyticsManager;
import com.sharpinu.persist.domain.Career;
import com.sharpinu.persist.domain.Company;
import com.sharpinu.persist.domain.ContactUs;
import com.sharpinu.persist.domain.Resume;
import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.repositories.CareerRepo;
import com.sharpinu.persist.repositories.CompanyRepo;
import com.sharpinu.persist.repositories.ContactUsRepo;
import com.sharpinu.persist.repositories.ResumeRepo;
import com.sharpinu.persist.repositories.UserRepo;
import com.sharpinu.util.DateUtil;
import com.sharpinu.util.Lib;
import com.sharpinu.web.bean.admin.OverviewStatisticBean;
import com.sharpinu.web.bean.admin.StatisticDetailsBean;

@Service
public class ReportService implements IReportService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ResumeRepo resumeRepo;

	@Autowired
	private CareerRepo careerRepo;

	@Autowired
	private CompanyRepo companyRepo;

	@Autowired
	private ContactUsRepo contactUsRepo;

	public List<OverviewStatisticBean> getOverviewStatisticData() {
		try {
			List<OverviewStatisticBean> statisticBeans = new ArrayList<OverviewStatisticBean>();
			Date toDate = new Date();
			Date back7Date = DateUtil.getDateBeforeDays(7);
			Date back30Date = DateUtil.getDateBeforeDays(30);

			buildRegisterStatisticBean(statisticBeans, toDate, back7Date, back30Date);
			buildResumeStatisticBean(statisticBeans, toDate, back7Date, back30Date);
			buildCareerStatisticBean(statisticBeans, toDate, back7Date, back30Date);
			buildCompanyStatisticBean(statisticBeans, toDate, back7Date, back30Date);
			buildContactUsStatisticBean(statisticBeans, toDate, back7Date, back30Date);
			buildViewStatisticBean(statisticBeans, toDate, back7Date, back30Date);

			return statisticBeans;
		} catch (Exception e) {
			throw new RuntimeException("Failed to get overview statistic data", e);
		}
	}

	public List<StatisticDetailsBean> getRegisterStatisticData(Date from, Date to) {
		List<StatisticDetailsBean> results = new ArrayList<StatisticDetailsBean>();
		List<User> users = userRepo.findInDateRange(from, to);
		StatisticDetailsBean registerDetailsBean = null;
		for (User u : users) {
			registerDetailsBean = new StatisticDetailsBean(u.getUserId(), u.getUserName(), u.getUserEmail(), u.getDateCreated());
			results.add(registerDetailsBean);
		}
		return results;
	}

	public List<StatisticDetailsBean> getCompanyStatisticData(Date from, Date to) {
		List<StatisticDetailsBean> results = new ArrayList<StatisticDetailsBean>();
		List<Company> companies = companyRepo.findInDateRange(from, to);
		StatisticDetailsBean companyDetailsBean = null;
		for (Company c : companies) {
			companyDetailsBean = new StatisticDetailsBean(c.getCompanyId(), c.getInfo(), c.getEmail(), c.getCreatedDate());
			results.add(companyDetailsBean);
		}
		return results;
	}

	public List<StatisticDetailsBean> getCareerStatisticData(Date from, Date to) {
		List<StatisticDetailsBean> results = new ArrayList<StatisticDetailsBean>();
		List<Career> careers = careerRepo.findInDateRange(from, to);
		StatisticDetailsBean careerDetailsBean = null;
		for (Career c : careers) {
			careerDetailsBean = new StatisticDetailsBean(c.getCareerId(), c.getName(), c.getEmail(), c.getCreatedDate());
			results.add(careerDetailsBean);
		}
		return results;
	}

	public List<StatisticDetailsBean> getResumeStatisticData(Date from, Date to) {
		List<StatisticDetailsBean> results = new ArrayList<StatisticDetailsBean>();
		List<Resume> resumes = resumeRepo.findInDateRange(from, to);
		StatisticDetailsBean resumeDetailsBean = null;
		for (Resume r : resumes) {
			User user = userRepo.findOne(r.getUserId());
			resumeDetailsBean = new StatisticDetailsBean(r.getResumeId(), r.getName(), user.getUserEmail(), r.getCreatedDate());
			results.add(resumeDetailsBean);
		}
		return results;
	}

	public List<StatisticDetailsBean> getContactUsStatisticData(Date from, Date to) {
		List<StatisticDetailsBean> results = new ArrayList<StatisticDetailsBean>();
		List<ContactUs> contacts = contactUsRepo.findInDateRange(from, to);
		StatisticDetailsBean contactDetailsBean = null;
		for (ContactUs c : contacts) {
			contactDetailsBean = new StatisticDetailsBean(c.getContactId(), c.getName(), c.getEmail(), c.getCreatedDate());
			results.add(contactDetailsBean);
		}
		return results;
	}

	public List<StatisticDetailsBean> getViewsStatisticData(Date from, Date to) {
		try {
			List<List<String>> gaData = null;
			if (from == null && to == null) {
				gaData = GoogleAnalyticsManager.getInstance().getAllViewAnalyticsData();
			} else {
				String startDate = DateUtil.convertDateToStringFormat(from, DateUtil.GOOGLE_DATE_FORMAT);
				String endDate = DateUtil.convertDateToStringFormat(to, DateUtil.GOOGLE_DATE_FORMAT);
				gaData = GoogleAnalyticsManager.getInstance().getViewAnalyticsData(startDate, endDate);
			}
			List<StatisticDetailsBean> results = buildStatisicBeanFromGaData(gaData);
			return results;
		} catch (Exception e) {
			throw new RuntimeException("Failed to get Views statistic data", e);
		}
	}

	private List<StatisticDetailsBean> buildStatisicBeanFromGaData(List<List<String>> gaData) {
		StatisticDetailsBean viewDetailsBean;
		Map<String, StatisticDetailsBean> pageTitleStatisticBeanMap = new HashMap<String, StatisticDetailsBean>();
		for (List<String> row : gaData) {
			String pageTitle = row.get(0);
			if (StringUtils.isBlank(pageTitle)) {
				continue;
			}
			viewDetailsBean = pageTitleStatisticBeanMap.get(pageTitle);
			if (viewDetailsBean == null) {
				viewDetailsBean = initStatisticBeanFromGaData(row);
				pageTitleStatisticBeanMap.put(pageTitle, viewDetailsBean);
			}
			int pageViews = Lib.getIntValue(row.get(5));
			viewDetailsBean.increasePageViews(pageViews);
		}
		return new ArrayList<StatisticDetailsBean>(pageTitleStatisticBeanMap.values());
	}

	private StatisticDetailsBean initStatisticBeanFromGaData(List<String> row) {
		String info = row.get(0);
		String date = row.get(1);
		String hour = row.get(2);
		String minute = row.get(3);
		Date viewDate = DateUtil.convertStringToDate(date + hour + minute, "yyyyMMddhhmm");
		StatisticDetailsBean viewDetailsBean = new StatisticDetailsBean();
		viewDetailsBean.setInfo(info);
		viewDetailsBean.setSubmitDate(new Timestamp(viewDate.getTime()));
		return viewDetailsBean;
	}

	private void buildContactUsStatisticBean(List<OverviewStatisticBean> statisticBeans, Date toDate, Date fromLast7Days, Date fromLast30Days) {
		int total = ((Long) contactUsRepo.count()).intValue();
		int last7Days = contactUsRepo.countInDateRange(fromLast7Days, toDate, ContactUs.class.getName());
		int last30Days = contactUsRepo.countInDateRange(fromLast30Days, toDate, ContactUs.class.getName());
		OverviewStatisticBean statisticBean = new OverviewStatisticBean("Contact Us Request", total, last7Days, last30Days, WebConstants.FixValue.CONTACT_US_REPORT);
		statisticBeans.add(statisticBean);
	}

	private void buildCompanyStatisticBean(List<OverviewStatisticBean> statisticBeans, Date toDate, Date fromLast7Days, Date fromLast30Days) {
		int total = ((Long) companyRepo.count()).intValue();
		int last7Days = companyRepo.countInDateRange(fromLast7Days, toDate, Company.class.getName());
		int last30Days = companyRepo.countInDateRange(fromLast30Days, toDate, Company.class.getName());
		OverviewStatisticBean statisticBean = new OverviewStatisticBean("Ask For Company Advice", total, last7Days, last30Days, WebConstants.FixValue.COMPANY_REPORT);
		statisticBeans.add(statisticBean);
	}

	private void buildCareerStatisticBean(List<OverviewStatisticBean> statisticBeans, Date toDate, Date fromLast7Days, Date fromLast30Days) {
		int total = ((Long) careerRepo.count()).intValue();
		int last7Days = careerRepo.countInDateRange(fromLast7Days, toDate, Career.class.getName());
		int last30Days = careerRepo.countInDateRange(fromLast30Days, toDate, Career.class.getName());
		OverviewStatisticBean statisticBean = new OverviewStatisticBean("Ask For Career Advice", total, last7Days, last30Days, WebConstants.FixValue.CAREER_REPORT);
		statisticBeans.add(statisticBean);
	}

	private void buildResumeStatisticBean(List<OverviewStatisticBean> statisticBeans, Date toDate, Date fromLast7Days, Date fromLast30Days) {
		int total = ((Long) resumeRepo.count()).intValue();
		int last7Days = resumeRepo.countInDateRange(fromLast7Days, toDate, Resume.class.getName());
		int last30Days = resumeRepo.countInDateRange(fromLast30Days, toDate, Resume.class.getName());
		OverviewStatisticBean statisticBean = new OverviewStatisticBean("Ask For Resume", total, last7Days, last30Days, WebConstants.FixValue.RESUME_REPORT);
		statisticBeans.add(statisticBean);
	}

	private void buildRegisterStatisticBean(List<OverviewStatisticBean> statisticBeans, Date toDate, Date fromLast7Days, Date fromLast30Days) {
		int total = ((Long) userRepo.count()).intValue();
		int last7Days = userRepo.countInDateRange(fromLast7Days, toDate);
		int last30Days = userRepo.countInDateRange(fromLast30Days, toDate);
		OverviewStatisticBean statisticBean = new OverviewStatisticBean("Register", total, last7Days, last30Days, WebConstants.FixValue.REGISTER_REPORT);
		statisticBeans.add(statisticBean);
	}

	private void buildViewStatisticBean(List<OverviewStatisticBean> statisticBeans, Date toDate,
			Date fromLast7Days, Date fromLast30Days) throws IOException {
		int total = GoogleAnalyticsManager.getInstance().getTotalPageViews();
		String endDate = DateUtil.convertDateToStringFormat(toDate, DateUtil.GOOGLE_DATE_FORMAT);
		String startDate = DateUtil.convertDateToStringFormat(fromLast7Days, DateUtil.GOOGLE_DATE_FORMAT);
		int last7Days = GoogleAnalyticsManager.getInstance().getPageviews(startDate, endDate);
		startDate = DateUtil.convertDateToStringFormat(fromLast30Days, DateUtil.GOOGLE_DATE_FORMAT);
		int last30Days = GoogleAnalyticsManager.getInstance().getPageviews(startDate, endDate);
		OverviewStatisticBean statisticBean = new OverviewStatisticBean("View", total, last7Days, last30Days,
				WebConstants.FixValue.VIEW_REPORT);
		statisticBeans.add(statisticBean);
	}

	public List<StatisticDetailsBean> getViewDetailsStatisticData(Date from, Date to, String pageTitle) {
		try {
			List<StatisticDetailsBean> results = new ArrayList<StatisticDetailsBean>();
			if (StringUtils.isEmpty(pageTitle)) {
				return results;
			}
			List<List<String>> gaData = null;
			if (from == null && to == null) {
				gaData = GoogleAnalyticsManager.getInstance().getAllViewDetailsAnalyticsData(pageTitle);
			} else {
				String startDate = DateUtil.convertDateToStringFormat(from, DateUtil.GOOGLE_DATE_FORMAT);
				String endDate = DateUtil.convertDateToStringFormat(to, DateUtil.GOOGLE_DATE_FORMAT);
				gaData = GoogleAnalyticsManager.getInstance().getViewDetailsAnalyticsData(startDate, endDate, pageTitle);
			}
			for (List<String> row : gaData) {
				StatisticDetailsBean viewDetailBean = initStatisticBeanFromGaData(row);
				results.add(viewDetailBean);
			}
			return results;
		} catch (Exception e) {
			throw new RuntimeException("Failed to get view details statistic data [pageTitle = " + pageTitle + "]", e);
		}
	}
}
