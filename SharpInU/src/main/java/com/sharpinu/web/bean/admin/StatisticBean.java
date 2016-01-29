package com.sharpinu.web.bean.admin;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class StatisticBean {
	Integer siteViewTotal;
	Integer siteUserRegisterTotal;

	public Integer getSiteViewTotal() {
		return siteViewTotal;
	}

	public void setSiteViewTotal(Integer siteViewTotal) {
		this.siteViewTotal = siteViewTotal;
	}

	public Integer getSiteUserRegisterTotal() {
		return siteUserRegisterTotal;
	}

	public void setSiteUserRegisterTotal(Integer siteUserRegisterTotal) {
		this.siteUserRegisterTotal = siteUserRegisterTotal;
	}

}
