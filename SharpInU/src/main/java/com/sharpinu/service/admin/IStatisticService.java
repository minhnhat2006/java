package com.sharpinu.service.admin;

import com.sharpinu.service.IBaseService;
import com.sharpinu.web.bean.admin.StatisticBean;

/**
 * 
 * @author mark
 *
 */
public interface IStatisticService extends IBaseService {

	StatisticBean getSiteStatistic();

	void increaseCount(String field);
}
