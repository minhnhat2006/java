package com.sharpinu.web.dwr;

import java.util.Date;
import java.util.List;

import com.sharpinu.web.bean.TrendBean;

public interface IDwrTrendService {

	public List<TrendBean> searchTrends(String term, Date createdDate);

	public TrendBean getTrendById(int postId);

	public List<TrendBean> loadMoreTrendsRecent(long offset);

}
