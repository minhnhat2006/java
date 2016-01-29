package com.sharpinu.service.admin;

import java.util.Date;
import java.util.List;

import com.sharpinu.web.bean.admin.OverviewStatisticBean;
import com.sharpinu.web.bean.admin.StatisticDetailsBean;

public interface IReportService {

	List<OverviewStatisticBean> getOverviewStatisticData();

	List<StatisticDetailsBean> getRegisterStatisticData(Date from, Date to);

	List<StatisticDetailsBean> getCompanyStatisticData(Date from, Date to);

	List<StatisticDetailsBean> getCareerStatisticData(Date from, Date to);

	List<StatisticDetailsBean> getResumeStatisticData(Date from, Date to);

	List<StatisticDetailsBean> getContactUsStatisticData(Date from, Date to);

	List<StatisticDetailsBean> getViewsStatisticData(Date from, Date to);

	List<StatisticDetailsBean> getViewDetailsStatisticData(Date from, Date to, String pageTitle);
}
