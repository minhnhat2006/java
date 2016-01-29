package com.sharpinu.web.bean;

import java.util.List;

public class TrendPageBean {
	TrendBean trendBean;
	List<TrendBean> trendBeans;
	Boolean hasMoreTrend;

	// for pagination
	Integer currentIndex;
	Integer totalPages;
	Integer beginIndex;
	Integer endIndex;

	public TrendPageBean(TrendBean latestTrend) {
		super();
		this.trendBean = latestTrend;
	}

	public TrendPageBean() {
	}

	public TrendBean getTrendBean() {
		return trendBean;
	}

	public void setTrendBean(TrendBean trendBean) {
		this.trendBean = trendBean;
	}

	public List<TrendBean> getTrendBeans() {
		return trendBeans;
	}

	public void setTrendBeans(List<TrendBean> trendBeans) {
		this.trendBeans = trendBeans;
	}

	public Boolean getHasMoreTrend() {
		return this.hasMoreTrend;
	}

	public void setHasMoreTrend(Boolean hasMoreTrend) {
		this.hasMoreTrend = hasMoreTrend;
	}

	public Integer getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(Integer currentIndex) {
		this.currentIndex = currentIndex;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(Integer beginIndex) {
		this.beginIndex = beginIndex;
	}

	public Integer getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}

}
