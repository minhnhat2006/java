package com.sharpinu.service.admin;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sharpinu.persist.domain.Company;
import com.sharpinu.service.IBaseService;
import com.sharpinu.web.bean.CompanyBean;

/**
 * 
 * @author Mark
 *
 */
public interface ICompanyService extends IBaseService {

	/**
	 * Find companies with pageIndex criteria
	 * 
	 * @param pageIndex
	 * @return
	 */
	Page<Company> getCompanyPageInfo(int pageIndex);

	/**
	 * Find companies with pageIndex criteria
	 * 
	 * @param pageIndex
	 * @param postBeans
	 * @return
	 */
	List<CompanyBean> findCompanyViewBean(int pageIndex, Page<Company> companies);
}
