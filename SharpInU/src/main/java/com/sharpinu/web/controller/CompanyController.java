package com.sharpinu.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sharpinu.constant.WebConstants;
import com.sharpinu.persist.domain.Company;
import com.sharpinu.persist.repositories.CompanyRepo;
import com.sharpinu.service.IAskForAdviceService;
import com.sharpinu.util.CryptoHelper;
import com.sharpinu.util.FileUtil;
import com.sharpinu.util.Lib;
import com.sharpinu.web.common.util.SecurityUtil;
import com.sharpinu.web.form.CompanyForm;

@Controller
public class CompanyController extends BaseController {

	@Autowired
	IAskForAdviceService askForAdviceService;

	@Autowired
	CompanyRepo companyRepo;

	@RequestMapping(value = "/company_form", method = RequestMethod.GET)
	public String viewCompanyForm(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute CompanyForm companyForm, BindingResult result, Model model, SessionStatus status) {
		status.setComplete();
		setModelAttributesForCompanyForm(companyForm, model);
		return WebConstants.Views.COMPANY_FORM;
	}

	@RequestMapping(value = "/company_form", method = RequestMethod.POST)
	public String saveCompanyForm(HttpServletRequest request, HttpServletResponse response, 
			@Valid@ModelAttribute CompanyForm companyForm, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		boolean hasErrors = result.hasErrors();
		if (!hasErrors) {
			companyForm.setUserId(SecurityUtil.getCurrentUser().getUserId());
			askForAdviceService.saveCompanyForm(companyForm);
			redirectAttributes.addFlashAttribute("successMsg", "Submission of Company Information is successful. Thank you, we'll respond soon.");
			String idEncrypt = CryptoHelper.encrypt(String.valueOf(companyForm.getCompanyId()));
			return "redirect:" + "/company/" + idEncrypt + "/review.in";
		}
		return WebConstants.Views.COMPANY_FORM;
	}

	@RequestMapping(value = "/company/{id}/review", method = RequestMethod.GET)
	public String reviewCompanyForm(@PathVariable("id") String idEncrypt, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		String idDecrypt = CryptoHelper.decrypt(idEncrypt);
		int companyId = Lib.getIntValue(idDecrypt);
		
		Company company = companyRepo.findOne(companyId);
		model.addAttribute("company", company);
		model.addAttribute("idEncrypt", idEncrypt);
		return WebConstants.Views.COMPANY_FORM_REVIEW;
	}
	
	@RequestMapping(value = "/company/additionalFile", method = RequestMethod.GET)
    public void viewCVAttachment(@RequestParam("id")  String idEncrypt, HttpServletRequest request, HttpServletResponse response) {
		String idDecrypt = CryptoHelper.decrypt(idEncrypt);
		int companyId = Lib.getIntValue(idDecrypt);
		Company company = companyRepo.findOne(companyId);
		InputStream is = null;
		if (company!= null && StringUtils.hasText(company.getAdditionalInfo())) {
			try {
				File additionalInfoFile = new File(company.getAdditionalInfo());
				is = new FileInputStream(additionalInfoFile);
				FileUtil.sendStreamToUser(is, request, response, additionalInfoFile.getName());
			} catch (IOException ex) {
				log.error(String.format("Error writing additional info file of company[companyId = %s] to output stream.", companyId), ex);
			}
		}
	}

	@ModelAttribute
	public CompanyForm companyForm() {
		return new CompanyForm();
	}
	
	private void setModelAttributesForCompanyForm(CompanyForm companyForm, Model model) {
		model.addAttribute("companyForm", companyForm);
	}
}
