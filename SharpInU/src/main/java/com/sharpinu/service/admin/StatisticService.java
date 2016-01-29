package com.sharpinu.service.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharpinu.constant.StatisticConstants;
import com.sharpinu.persist.domain.Statistic;
import com.sharpinu.persist.repositories.StatisticRepo;
import com.sharpinu.service.BaseService;
import com.sharpinu.web.bean.admin.StatisticBean;

/**
 * 
 * @author Mark
 *
 */
@Service
public class StatisticService extends BaseService implements IStatisticService {

	@Autowired
	StatisticRepo statisticRepo;

	public StatisticBean getSiteStatistic() {
		List<Statistic> statistics = statisticRepo.findAll();
		Map<String, Integer> statisticArr = new HashMap<String, Integer>();

		for (Statistic statistic : statistics) {
			statisticArr.put(statistic.getField(), statistic.getCount());
		}

		StatisticBean statisticBean = new StatisticBean();
		statisticBean.setSiteViewTotal(statisticArr.get(StatisticConstants.SITE_VIEW_TOTAL));
		statisticBean.setSiteUserRegisterTotal(statisticArr.get(StatisticConstants.SITE_USER_REGISTER_TOTAL));

		return statisticBean;
	}

	public void increaseCount(String field) {
		Statistic statistic = statisticRepo.findOne(field);
		statistic.setCount(statistic.getCount() + 1);
		statisticRepo.save(statistic);
	}
}
