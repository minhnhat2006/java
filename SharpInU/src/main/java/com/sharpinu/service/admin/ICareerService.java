package com.sharpinu.service.admin;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sharpinu.persist.domain.Career;
import com.sharpinu.service.IBaseService;
import com.sharpinu.web.bean.CareerBean;

/**
 * 
 * @author mark
 *
 */
public interface ICareerService extends IBaseService {

	/**
	 * Find careers with pageIndex criteria
	 * 
	 * @param pageIndex
	 * @return
	 */
	Page<Career> getCareerPageInfo(int pageIndex);

	/**
	 * Find careers with pageIndex criteria
	 * 
	 * @param pageIndex
	 * @param careers
	 * @return
	 */
	List<CareerBean> findCareerViewBean(int pageIndex, Page<Career> careers);
}
