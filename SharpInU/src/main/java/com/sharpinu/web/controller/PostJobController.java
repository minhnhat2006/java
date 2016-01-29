package com.sharpinu.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.sharpinu.constant.WebConstants;
import com.sharpinu.persist.domain.Country;
import com.sharpinu.persist.domain.Skill;
import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.domain.WorkCategory;
import com.sharpinu.persist.repositories.CountryRepo;
import com.sharpinu.security.bean.SharpInUUserDetails;
import com.sharpinu.service.IJobService;
import com.sharpinu.service.ILocationService;
import com.sharpinu.service.ISkillService;
import com.sharpinu.service.IWorkCategoryService;
import com.sharpinu.web.bean.CityBean;
import com.sharpinu.web.common.util.SecurityUtil;
import com.sharpinu.web.common.util.WebHelper;
import com.sharpinu.web.form.PostJobForm;

/**
 * Controller for posting job. Include these action:
 * + Show Post Job page.
 * + Preview Job.
 * + Cancel posting job.
 * + Submit job.
 * 
 * @author Mark
 *
 */

@Controller
@SessionAttributes("postJobForm")
public class PostJobController extends BaseController {
	@Autowired
	private WebHelper webHelper;
	
	@Autowired
	private IJobService jobService;
	
	@Autowired
	private ILocationService locationService;
	
	@Autowired
	private ISkillService skillService;
	
	@Autowired
	private IWorkCategoryService workCategoryService;
	
	@Autowired
	private CountryRepo countryRepo;
	
	@ModelAttribute("postJobForm")
	public PostJobForm postJobForm() {
		return new PostJobForm(); // populates form for the first time if its null
	}
	 
    @RequestMapping(value = "/admin/post_job", method = RequestMethod.GET)
    public String postJob(HttpServletRequest request, HttpServletResponse response, 
    		@ModelAttribute("postJobForm") PostJobForm postJobForm, BindingResult result, Model model, SessionStatus status) throws IOException {
    	status.setComplete();
    	setModelAttributesForPostJob(postJobForm, model);
        return WebConstants.Views.POST_JOB;
    }
    
    @RequestMapping(value = "/admin/post_job/preview", method = RequestMethod.POST)
    public String postJobPreview(HttpServletRequest request, HttpServletResponse response, 
    		@Valid @ModelAttribute("postJobForm") PostJobForm postJobForm, BindingResult result, Model model) throws IOException {
    	boolean hasError = result.hasErrors();
		if (!hasError) {
			setModelAttributesForPostJobPreview(postJobForm, model);
			return WebConstants.Views.POST_JOB_PREVIEW;
		} else {
			setModelAttributesForPostJob(postJobForm, model);
			
			return WebConstants.Views.POST_JOB;
		}
    }
    
    @RequestMapping(value = "/admin/post_job/done", method = RequestMethod.POST)
    public String postJobDone(HttpServletRequest request, HttpServletResponse response, 
    		@ModelAttribute("postJobForm") PostJobForm postJobForm, BindingResult result, Model model, SessionStatus status) throws IOException {
    	SharpInUUserDetails userDetail = SecurityUtil.getUserDetail();
    	
    	jobService.postJob(postJobForm, userDetail.getSharpInUUser().getUserId());
    	status.setComplete();
    	return WebConstants.Views.POST_JOB_DONE;
    }
    
    @RequestMapping(value = "/admin/post_job/cancel", method = RequestMethod.POST)
    public String postJobCancel(HttpServletRequest request, HttpServletResponse response, 
    		@ModelAttribute("postJobForm") PostJobForm postJobForm, BindingResult result, Model model, SessionStatus status) throws IOException {
    	status.setComplete();
    	return WebConstants.Views.POST_JOB_DONE;
    }
	private void setModelAttributesForPostJobPreview(PostJobForm postJobForm,
			Model model) {
		String location = webHelper.buildocationText(postJobForm.getCountryId(), postJobForm.getCityId());
		model.addAttribute("location", location);
		
		String createdDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
		model.addAttribute("createdDate", createdDate);
		
		String salaryType = webHelper.buildSalaryTypeText(postJobForm.getSalaryType());
		model.addAttribute("salaryType", salaryType);
		
		String salary = webHelper.buildSalaryText(postJobForm.getSalaryFromAmount(), postJobForm.getSalaryToAmount());
		model.addAttribute("salary", salary);
		
		String clientInfo = buildClientInfoText();
		model.addAttribute("clientInfo", clientInfo);
		
		List<Skill> requiredSkills = skillService.findByIds(postJobForm.getRequiredSkillIds());
		model.addAttribute("requiredSkills", requiredSkills);
	}

	private String buildClientInfoText() {
		SharpInUUserDetails userDetail = SecurityUtil.getUserDetail();
		User jvUser = userDetail.getSharpInUUser();
		String clientInfo = "Hidden";
		if (jvUser.getFirstName() != null || jvUser.getLastName() != null) {
			clientInfo = String.format("%s %s", jvUser.getFirstName(), jvUser.getLastName());
		}
		return clientInfo;
	}

	private void setModelAttributesForPostJob(PostJobForm postJobForm, Model model) {
		model.addAttribute("postJobForm", postJobForm);
		
		List<Skill> skills = skillService.findAll();
		model.addAttribute("skills", skills);
		
		List<WorkCategory> workCategories = workCategoryService.findAll();
		workCategories.add(0, new WorkCategory(0, "Select", "Select"));
		model.addAttribute("workCategories", workCategories);
		
		List<Country> countries = countryRepo.findAll();
		countries.add(0, new Country(0, "Select a country"));
		model.addAttribute("countries", countries);
		
		int selectedCountryId = postJobForm.getCountryId();
		if (selectedCountryId != 0) {
			List<CityBean> cityBeans = locationService.findByCountryId(selectedCountryId);
			cityBeans.add(0, new CityBean(0, "Select", 0));
			model.addAttribute("cities", cityBeans);
		}
	}

	
}
