package com.sharpinu.service.admin;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sharpinu.persist.domain.Resume;
import com.sharpinu.service.IBaseService;
import com.sharpinu.web.bean.ResumeBean;

/**
 * 
 * @author nhatnguyen
 *
 */
public interface IResumeService extends IBaseService {

	/**
	 * Find resumes with pageIndex criteria
	 * 
	 * @param pageIndex
	 * @return
	 */
	Page<Resume> getResumePageInfo(int pageIndex);

	/**
	 * Find resumes with pageIndex criteria
	 * 
	 * @param pageIndex
	 * @param postBeans
	 * @return
	 */
	List<ResumeBean> findResumeViewBean(int pageIndex, Page<Resume> resumes);
}
