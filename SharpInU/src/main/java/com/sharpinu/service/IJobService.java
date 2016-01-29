package com.sharpinu.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sharpinu.persist.domain.Job;
import com.sharpinu.web.bean.JobViewBean;
import com.sharpinu.web.form.PostJobForm;

/**
 * 
 * @author Mark
 *
 */
public interface IJobService extends IBaseService {
	/**
	 * Add new job. Insert data into these tables:
	 * + job
	 * + job_skill
	 * @param postJobForm
	 * @param createdUserId
	 * @return
	 */
	Job postJob(PostJobForm postJobForm, int createdUserId);

	/**
	 * Find jobs with pageIndex criteria
	 *  
	 * @param pageIndex
	 * @return
	 */
	List<JobViewBean> findJobs(int pageIndex);
	
	/**
	 * Find jobs with pageIndex criteria
	 *  
	 * @param pageIndex
	 * @return
	 */
	Page<Job> getJobPageInfo(int pageIndex);
	
	/**
	 * Find jobs with pageIndex criteria
	 *  
	 * @param pageIndex
	 * @param jobs
	 * @return
	 */
	List<JobViewBean> findJobsViewBean(int pageIndex, Page<Job> jobs);
}
