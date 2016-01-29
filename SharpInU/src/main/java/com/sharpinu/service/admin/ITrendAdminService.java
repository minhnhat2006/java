package com.sharpinu.service.admin;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sharpinu.persist.domain.Trend;
import com.sharpinu.service.IBaseService;
import com.sharpinu.web.bean.TrendBean;
import com.sharpinu.web.form.admin.TrendForm;

/**
 * 
 * @author nhatnguyen
 *
 */
public interface ITrendAdminService extends IBaseService {

	String saveTrendForm(TrendForm trendForm);

	/**
	 * Find trends with pageIndex criteria
	 * 
	 * @param pageIndex
	 * @return
	 */
	Page<Trend> getTrendPageInfo(int pageIndex);

	/**
	 * Find trends with pageIndex criteria
	 * 
	 * @param pageIndex
	 * @param trendBeans
	 * @return
	 */
	List<TrendBean> findTrendViewBean(int pageIndex, Page<Trend> trends);
}
