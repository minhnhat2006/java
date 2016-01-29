package com.sharpinu.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sharpinu.constant.WebConstants;
import com.sharpinu.persist.domain.Advisor;
import com.sharpinu.persist.repositories.AdvisorRepo;
import com.sharpinu.service.IAdvisorService;
import com.sharpinu.util.FileUtil;
import com.sharpinu.web.form.AdvisorForm;

@Controller
@RequestMapping("/advisor")
public class AdvisorController extends BaseController {

	@Autowired
	@Qualifier("advisorRepo")
	AdvisorRepo advisorRepo;

	@Autowired
	@Qualifier("advisorService")
	IAdvisorService advisorService;

	@ModelAttribute
	public AdvisorForm advisorForm() {
		return new AdvisorForm();
	}

	@RequestMapping(value = "/stay_on_the_edge", method = RequestMethod.GET)
	public String getStayOnTheEdge(HttpServletRequest request, HttpServletResponse response, Model model) {
		return WebConstants.Views.ADVISOR_STAY_ON_THE_EDGE;
	}

	@RequestMapping(value = "/stay_on_the_edge", method = RequestMethod.POST)
	public String postStayOnTheEdge(HttpServletRequest request, HttpServletResponse response, @Valid @ModelAttribute AdvisorForm advisorForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		boolean hasErrors = result.hasErrors();

		if (!hasErrors) {
			this.userPreferences.setAdvisorToken(advisorForm.getToken());

			return "redirect:/stay_on_the_edge/" + this.userPreferences.getPostSlugBeingView() + "/view.in";
		}

		return WebConstants.Views.ADVISOR_STAY_ON_THE_EDGE;
	}

	@RequestMapping(value = "/{id}/viewAttached", method = RequestMethod.GET)
	public void viewAttachment(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) {
		Advisor advisor = advisorRepo.findOne(id);
		InputStream is = null;

		if (advisor != null && StringUtils.hasText(advisor.getAttachedFile())) {
			try {
				File attachedFile = new File(advisor.getAttachedFile());
				is = new FileInputStream(attachedFile);
				FileUtil.sendStreamToUser(is, request, response, attachedFile.getName());

			} catch (IOException ex) {
				log.error(String.format("Error reading attached file of advisor[advisorId = %s] to output stream.", id), ex);
			}
		}
	}
}
