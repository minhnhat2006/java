package com.sharpinu.web.dwr;

import java.util.Date;
import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;

import com.sharpinu.constant.TrendConst;
import com.sharpinu.service.ITrendService;
import com.sharpinu.web.bean.TrendBean;

@RemoteProxy
public class DwrTrendService implements IDwrTrendService {

	@Autowired
	ITrendService trendService;

	@RemoteMethod
	public List<TrendBean> searchTrends(String term, Date createdDate) {
		return trendService.searchTrendsSortByCreated(term, createdDate);
	}

	@RemoteMethod
	public TrendBean getTrendById(int postId) {
		return trendService.getTrend(postId);
	}

	@RemoteMethod
	public List<TrendBean> loadMoreTrendsRecent(long offset) {
		Date date = new Date(offset);
		return trendService.getTrendByDate(date, TrendConst.DEFAULT_POST_PAGE_SIZE);
	}
}
