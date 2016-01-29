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
import com.sharpinu.persist.domain.Career;
import com.sharpinu.persist.repositories.CareerRepo;
import com.sharpinu.service.IAskForAdviceService;
import com.sharpinu.util.CryptoHelper;
import com.sharpinu.util.FileUtil;
import com.sharpinu.util.Lib;
import com.sharpinu.web.common.util.SecurityUtil;
import com.sharpinu.web.form.CareerForm;

@Controller
public class CareerController extends BaseController {

	@Autowired
	IAskForAdviceService askForAdviceService;

	@Autowired
	CareerRepo careerRepo;

	@RequestMapping(value = "/career_form", method = RequestMethod.GET)
	public String viewCareerForm(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute CareerForm careerForm, BindingResult result, Model model, SessionStatus status) {
		status.setComplete();
		return WebConstants.Views.CAREER_FORM;
	}

	@RequestMapping(value = "/career_form", method = RequestMethod.POST)
	public String saveCareerForm(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,
			@Valid@ModelAttribute CareerForm careerForm, BindingResult result, Model model) {
		boolean hasErrors = result.hasErrors();
		if (!hasErrors) {
			careerForm.setUserId(SecurityUtil.getCurrentUser().getUserId());
			askForAdviceService.saveCareerForm(careerForm);
			redirectAttributes.addFlashAttribute("successMsg", "Submission of Career Asking is successful. Thank you, we'll respond soon.");
			String idEncrypt = CryptoHelper.encrypt(String.valueOf(careerForm.getCareerId()));
			
			return "redirect:" + "/career/" + idEncrypt + "/review.in";
		}
		return WebConstants.Views.CAREER_FORM;
	}

	@RequestMapping(value = "/career/{id}/review", method = RequestMethod.GET)
	public String reviewCareerForm(@PathVariable("id") String idEncrypt, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		String idDecrypt = CryptoHelper.decrypt(idEncrypt);
		int careerId = Lib.getIntValue(idDecrypt);
		
		Career career = careerRepo.findOne(careerId);
		model.addAttribute("career", career);
		model.addAttribute("idEncrypt", idEncrypt);
		return WebConstants.Views.CAREER_FORM_REVIEW;
	}
	
	@RequestMapping(value = "/career/additionalFile", method = RequestMethod.GET)
    public void viewCVAttachment(@RequestParam("id")  String idEncrypt, HttpServletRequest request, HttpServletResponse response) {
		String idDecrypt = CryptoHelper.decrypt(idEncrypt);
		int careerId = Lib.getIntValue(idDecrypt);
		Career career = careerRepo.findOne(careerId);
		InputStream is = null;
		if (career!= null && StringUtils.hasText(career.getAdditionalInfo())) {
			try {
				File additionalInfoFile = new File(career.getAdditionalInfo());
				is = new FileInputStream(additionalInfoFile);
				FileUtil.sendStreamToUser(is, request, response, additionalInfoFile.getName());
			} catch (IOException ex) {
				log.error(String.format("Error writing additional info file of career[careerId = %s] to output stream.", careerId), ex);
			}
		}
	}

	@ModelAttribute
	public CareerForm careerForm() {
		return new CareerForm();
	}
}
