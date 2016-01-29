package com.sharpinu.web.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sharpinu.constant.WebConstants;
import com.sharpinu.persist.domain.ContactUs;
import com.sharpinu.persist.repositories.ContactUsRepo;
import com.sharpinu.service.admin.IContactUsService;
import com.sharpinu.web.controller.BaseController;

@Controller
@RequestMapping(value = "/admin/contactus")
public class ContactUsAdminController extends BaseController {

	@Autowired
	IContactUsService contactUsService;

	@Autowired
	ContactUsRepo contactUsRepo;
	
	@RequestMapping("/{pageIndex}/list")
	public String showContactUss(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("pageIndex") int pageIndex, Model model) throws IOException {

		Page<ContactUs> page = contactUsService.getContactUsPageInfo(pageIndex - 1);
		List<ContactUs> contactuses = contactUsService.findContactUsViewBean(pageIndex - 1, page);

		model.addAttribute("contactuses", contactuses);
		model.addAttribute("page", page);

		int current = page.getNumber() + 1;
		int begin = 1;
		int end = page.getTotalPages();
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);

		return WebConstants.Views.CONTACT_US_LIST;
	}
	
	@RequestMapping("/{id}/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") int id, Model model) throws IOException {
		contactUsRepo.delete(id);
		return  "redirect:" + "/admin/contactus/1/list.in";
	}
	
	@RequestMapping(value = "/{id}/view", method = RequestMethod.GET)
	public String viewContactUs(@PathVariable("id") int categoryId, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ContactUs contactus = contactUsRepo.findOne(categoryId);
		model.addAttribute("contactus", contactus);
		return WebConstants.Views.CONTACT_US_VIEW;
	}

}
