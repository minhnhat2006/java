package com.sharpinu.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sharpinu.constant.WebConstants;
import com.sharpinu.persist.domain.Resume;
import com.sharpinu.persist.repositories.ResumeRepo;
import com.sharpinu.service.IAskForAdviceService;
import com.sharpinu.util.CryptoHelper;
import com.sharpinu.util.FileUtil;
import com.sharpinu.util.Lib;
import com.sharpinu.web.common.util.SecurityUtil;
import com.sharpinu.web.form.ResumeForm;

/**
 * Controller for posting resume
 * 
 * @author Mark
 *
 */

@Controller
@SessionAttributes("resumeForm")
public class ResumeController extends BaseController {
	@Autowired
	IAskForAdviceService askForAdviceService;
	
	@Autowired
	ResumeRepo resumeRepo;
	
	@ModelAttribute("resumeForm")
	public ResumeForm postJobForm() {
		return new ResumeForm(); // populates form for the first time if its null
	}
	 
    @RequestMapping(value = "/resume_form", method = RequestMethod.GET)
    public String showResumeForm(HttpServletRequest request, HttpServletResponse response, 
    		@ModelAttribute("resumeForm") ResumeForm resumeForm, BindingResult result, Model model, SessionStatus status) throws IOException {
    	status.setComplete();
    	setModelAttributesForResumeForm(resumeForm, model);
        return WebConstants.Views.RESUME_FORM;
    }
    
    @RequestMapping(value = "/resume_form/post", method = RequestMethod.POST)
    public String postResume(HttpServletRequest request, HttpServletResponse response, 
    		@Valid@ModelAttribute("resumeForm") ResumeForm resumeForm, BindingResult result, Model model, RedirectAttributes redirectAttributes) throws IOException {
    	boolean hasError = result.hasErrors();
		if (!hasError) {
			resumeForm.setUserId(SecurityUtil.getCurrentUser().getUserId());
			askForAdviceService.saveResumeForm(resumeForm);
			redirectAttributes.addFlashAttribute("successMsg", "Your resume information is submitted successul. Thank you, we'll respond soon.");
			String idEncrypt = CryptoHelper.encrypt(String.valueOf(resumeForm.getResumeId()));
			return "redirect:" + "/resume/" + idEncrypt+ "/review.in";
		} else {
			setModelAttributesForResumeForm(resumeForm, model);
			return WebConstants.Views.RESUME_FORM;
		}
    }
    
    @RequestMapping(value = "/resume/{id}/review", method = RequestMethod.GET)
    public String previewResume(@PathVariable("id") String idEncrypt, HttpServletRequest request, HttpServletResponse response, Model model) {
    	String idDecrypt = CryptoHelper.decrypt(idEncrypt);
    	int resumeId = Lib.getIntValue(idDecrypt);
    	Resume resume = resumeRepo.findOne(resumeId);
    	model.addAttribute("resume", resume);
    	model.addAttribute("idEncrypt", idEncrypt);
    	return WebConstants.Views.RESUME_FORM_REVIEW;
    }
    
	@RequestMapping(value = "/resume_image", method = RequestMethod.GET)
	public void showResumeImage(@RequestParam("id") String idEncrypt,
			HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException {
		String idDecrypt = CryptoHelper.decrypt(idEncrypt);
		int resumeId = Lib.getIntValue(idDecrypt);

		Resume resume = resumeRepo.findOne(resumeId);
		if (resume != null && StringUtils.hasText(resume.getImage())) {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			File image = new File(resume.getImage());
			if (image.exists()) {
				byte[] fileBytes = FileUtils.readFileToByteArray(image);
				response.getOutputStream().write(fileBytes);
				response.getOutputStream().close();
			}
		}
	}

	@RequestMapping(value = "/resume_cv", method = RequestMethod.GET)
    public void viewCVAttachment(@RequestParam("id")  String idEncrypt, HttpServletRequest request, HttpServletResponse response) {
		String idDecrypt = CryptoHelper.decrypt(idEncrypt);
		int resumeId = Lib.getIntValue(idDecrypt);
		Resume resume = resumeRepo.findOne(resumeId);
		InputStream is = null;
		if (resume!= null && StringUtils.hasText(resume.getCv())) {
			try {
				File cvAttachment = new File(resume.getCv());
				is = new FileInputStream(cvAttachment);
				FileUtil.sendStreamToUser(is, request, response, cvAttachment.getName());
			} catch (IOException ex) {
				log.error(String.format("Error writing cv attachment file of resume[resumeId = %s] to output stream.", resumeId), ex);
			}
		}
	}

	private void setModelAttributesForResumeForm(ResumeForm resumeForm, Model model) {
		model.addAttribute("resumeForm", resumeForm);
	}
}
