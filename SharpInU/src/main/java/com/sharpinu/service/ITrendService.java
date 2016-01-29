package com.sharpinu.service;

import java.util.Date;
import java.util.List;

import com.sharpinu.web.bean.TrendBean;
import com.sharpinu.web.bean.TrendPageBean;

public interface ITrendService extends IBaseService {

	TrendPageBean getTrends(Integer pageSize);

	TrendPageBean searchTrendsSortByCreated(String term);

	List<TrendBean> searchTrendsSortByCreated(String term, Date createdDate);

	List<TrendBean> getTrendByDate(Date offset, int pageSize);

	TrendBean getTrend(int postId);

}
