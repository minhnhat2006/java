package com.sharpinu.service.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.sharpinu.persist.domain.Trend;
import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.repositories.TrendRepo;
import com.sharpinu.persist.repositories.UserRepo;
import com.sharpinu.service.BaseService;
import com.sharpinu.service.IRepositoryService;
import com.sharpinu.web.bean.TrendBean;
import com.sharpinu.web.form.admin.TrendForm;

/**
 * 
 * @author Mark
 *
 */
@Service
public class TrendAdminService extends BaseService implements com.sharpinu.service.admin.ITrendAdminService {
	final int PAGE_SIZE = 5;
	@Autowired
	TrendRepo trendRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	IRepositoryService repositoryService;

	public String saveTrendForm(TrendForm trendForm) {
		try {
			Trend trend = new Trend(trendForm);
			trendRepo.save(trend);
			trendForm.setTrendId(trend.getTrendId());
		} catch (Exception e) {
			throw new RuntimeException("Failed to save Trend Form", e);
		}

		return null;
	}

	public Page<Trend> getTrendPageInfo(int pageIndex) {
		Sort sort = new Sort(new Order(Direction.ASC, "summary"));
		Pageable pageable = new PageRequest(pageIndex, PAGE_SIZE, sort);
		Page<Trend> page = trendRepo.findAll(pageable);
		return page;
	}

	public List<TrendBean> findTrendViewBean(int pageIndex, Page<Trend> trends) {
		try {
			List<TrendBean> trendBeans = new ArrayList<TrendBean>();

			for (Trend trend : trends) {
				User user = userRepo.findOne(trend.getUserId());
				TrendBean trendBean = new TrendBean(trend.getTrendId(), "", trend.getContent(), trend.getSummary());

				if (user != null) {
					trendBean.setUserId(user.getUserId());
					trendBean.setUserEmail(user.getUserEmail());
				}

				trendBeans.add(trendBean);
			}

			return trendBeans;
		} catch (Exception e) {
			log.error("Failed to findTrendViewBean", e);
			throw new RuntimeException("Failed to findTrendViewBean", e);
		}
	}
}
