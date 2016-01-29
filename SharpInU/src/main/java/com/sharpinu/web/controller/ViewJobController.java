package com.sharpinu.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sharpinu.constant.WebConstants;
import com.sharpinu.persist.domain.Job;
import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.repositories.JobRepo;
import com.sharpinu.security.bean.SharpInUUserDetails;
import com.sharpinu.service.IJobService;
import com.sharpinu.service.ISkillService;
import com.sharpinu.web.bean.JobViewBean;
import com.sharpinu.web.common.util.SecurityUtil;
import com.sharpinu.web.common.util.WebHelper;

/**
 * 
 * @author Mark
 * 
 */
@Controller
@SessionAttributes("jobs")
public class ViewJobController extends BaseController {
	@Autowired
	private WebHelper webHelper;
	
	@Autowired
	private ISkillService skillService;
	
	@Autowired
	private IJobService jobService;
	
	@Autowired
	private JobRepo jobRepo;
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param pageIndex this value is started from ONE. 
	 * 		  While [jobService.findJobs] is ZERO BASE, it is minus 1 before pass to that method.
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/admin/jobs/{pageIndex}/list")
	public String jobsList(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("pageIndex") int pageIndex, Model model) throws IOException {
		
		Page<Job> page = jobService.getJobPageInfo(pageIndex - 1);
		List<JobViewBean> jobs = jobService.findJobsViewBean(pageIndex - 1, page);
		
		model.addAttribute("jobs", jobs);
		model.addAttribute("page", page);
		
		int current = page.getNumber() + 1;
	    int begin = 1;
	    int end = page.getTotalPages();
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		
		return WebConstants.Views.JOBS_LIST;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/admin/jobs/{jobId}/view")
	public String viewJob(HttpServletRequest request,
			HttpServletResponse response, @PathVariable("jobId") int jobId, ModelMap model) throws IOException {
		List<JobViewBean> jobs = (List<JobViewBean>) model.get("jobs");
		for(JobViewBean job : jobs) {
			if(job.getJobId() == jobId){
				setModelAttributesForJobDetail(job, model);
				break;
			}
		}
		return WebConstants.Views.JOB_DETAIL;
	}
	
	private void setModelAttributesForJobDetail(JobViewBean jobViewBean,
			ModelMap model) {
		
		model.addAttribute("title", jobViewBean.getTitle());
		
		model.addAttribute("location", jobViewBean.getLocation());
		
		model.addAttribute("createdDate", jobViewBean.getCreatedDate());
		
		String salaryType = webHelper.buildSalaryTypeText(jobViewBean.getSalaryType());
		model.addAttribute("salaryType", salaryType);
		
		String salary = webHelper.buildSalaryText(jobViewBean.getSalaryFromAmount(), jobViewBean.getSalaryToAmount());
		model.addAttribute("salary", salary);
		
		String company = buildComapnyText();
		model.addAttribute("company", company);
		
		model.addAttribute("jobType", jobViewBean.getJobType());
		
		model.addAttribute("appliedCount", jobViewBean.getAppliedCount());
		
		model.addAttribute("description", jobViewBean.getDescription());
	
		model.addAttribute("requiredSkills", jobViewBean.getRequiredSkills());
		
		model.addAttribute("customRequiredSkill", jobViewBean.getCustomRequiredSkill());
	}
	
	private String buildComapnyText() {
		SharpInUUserDetails userDetail = SecurityUtil.getUserDetail();
		User jvUser = userDetail.getSharpInUUser();
		String clientInfo = "Hidden";
		if (jvUser.getFirstName() != null || jvUser.getLastName() != null) {
			clientInfo = String.format("%s %s", jvUser.getFirstName(), jvUser.getLastName());
		}
		return clientInfo;
	}
}
