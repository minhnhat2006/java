package com.sharpinu.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sharpinu.constant.WebConstants;
import com.sharpinu.persist.domain.ContactUs;
import com.sharpinu.persist.repositories.ContactUsRepo;
import com.sharpinu.service.IAskForAdviceService;
import com.sharpinu.util.CryptoHelper;
import com.sharpinu.util.Lib;
import com.sharpinu.web.form.ContactUsForm;

/**
 * @author Mark
 *
 */

@Controller
@SessionAttributes("contactUsForm")
public class ContactUsController extends BaseController {
	@Autowired
	IAskForAdviceService askForAdviceService;
	
	@Autowired
	ContactUsRepo contactUsRepo;
	
	@ModelAttribute("contactUsForm")
	public ContactUsForm createContactUsForm() {
		return new ContactUsForm(); // populates form for the first time if its null
	}
	 
    @RequestMapping(value = "/contact_us", method = RequestMethod.GET)
    public String viewContactUsForm(HttpServletRequest request, HttpServletResponse response, 
    		@ModelAttribute("contacUsForm") ContactUsForm contactUsForm, BindingResult result, Model model, SessionStatus status) throws IOException {
    	status.setComplete();
    	setModelAttributesForContactUsForm(contactUsForm, model);
        return WebConstants.Views.CONTACT_US;
    }
    
    @RequestMapping(value = "/contact_us/post", method = RequestMethod.POST)
    public String postContactInfo(HttpServletRequest request, HttpServletResponse response, 
    		@Valid@ModelAttribute("contactUsForm") ContactUsForm contactUsForm, BindingResult result, Model model, RedirectAttributes redirectAttributes) throws IOException {
    	boolean hasError = result.hasErrors();
		if (!hasError) {
			askForAdviceService.saveContactUsForm(contactUsForm);
			redirectAttributes.addFlashAttribute("successMsg", "Your information is submited. Thank you, we'll respond soon.");
			String idEncrypt = CryptoHelper.encrypt(String.valueOf(contactUsForm.getContactUsId()));
			return "redirect:" + "/contact/" + idEncrypt + "/review.in";
		} else {
			setModelAttributesForContactUsForm(contactUsForm, model);
			return WebConstants.Views.CONTACT_US;
		}
    }
    
	@RequestMapping(value = "/contact/{id}/review", method = RequestMethod.GET)
	public String reviewContactUsForm(@PathVariable("id") String idEncrypt, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		String idDecrypt = CryptoHelper.decrypt(idEncrypt);
		int contactUsId = Lib.getIntValue(idDecrypt);
		ContactUs contactUs = contactUsRepo.findOne(contactUsId);
		model.addAttribute("contactUs", contactUs);
		return WebConstants.Views.CONTACT_US_REVIEW;
	}


	private void setModelAttributesForContactUsForm(ContactUsForm contactUsForm, Model model) {
		model.addAttribute("contactUsForm", contactUsForm);
	}
}
