package com.sharpinu.web.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sharpinu.constant.WebConstants;
import com.sharpinu.persist.domain.Career;
import com.sharpinu.persist.domain.Ticket;
import com.sharpinu.persist.repositories.CareerRepo;
import com.sharpinu.persist.repositories.TicketRepo;
import com.sharpinu.service.admin.ICareerService;
import com.sharpinu.util.CryptoHelper;
import com.sharpinu.util.FileUtil;
import com.sharpinu.util.Lib;
import com.sharpinu.web.bean.CareerBean;
import com.sharpinu.web.controller.BaseController;

@Controller
@RequestMapping(value = "/admin/career")
public class CareerAdminController extends BaseController {

	@Autowired
	ICareerService careerService;

	@Autowired
	CareerRepo careerRepo;

	@Autowired
	TicketRepo ticketRepo;

	@RequestMapping("/{pageIndex}/list")
	public String showCareers(HttpServletRequest request, HttpServletResponse response, @PathVariable("pageIndex") int pageIndex, Model model) throws IOException {

		Page<Career> page = careerService.getCareerPageInfo(pageIndex - 1);
		List<CareerBean> careers = careerService.findCareerViewBean(pageIndex - 1, page);

		model.addAttribute("careers", careers);
		model.addAttribute("page", page);

		int current = page.getNumber() + 1;
		int begin = 1;
		int end = page.getTotalPages();
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);

		return WebConstants.Views.CAREER_LIST;
	}

	@RequestMapping("/{id}/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id, Model model) throws IOException {
		careerRepo.delete(id);
		return "redirect:" + "/admin/career/1/list.in";
	}

	@RequestMapping(value = "/{id}/view", method = RequestMethod.GET)
	public String viewCareer(@PathVariable("id") int careerId, HttpServletRequest request, HttpServletResponse response, Model model) {
		Career career = careerRepo.findOne(careerId);
		String idEncrypt = CryptoHelper.encrypt(String.valueOf(career.getCareerId()));
		model.addAttribute("idEncrypt", idEncrypt);

		CareerBean careerBean = new CareerBean(career);
		Ticket ticket = this.ticketRepo.findByCareerId(career.getCareerId());
		careerBean.setTicket(ticket);
		model.addAttribute("career", careerBean);

		return WebConstants.Views.CAREER_VIEW;
	}

	@RequestMapping(value = "/career_additionalFile", method = RequestMethod.GET)
	public void viewAdditionalInfoAttachment(@RequestParam("id") String idEncrypt, @RequestParam(value = "isEncrypt", required = false) Boolean isEncrypt, HttpServletRequest request,
			HttpServletResponse response) {
		String idDecrypt = null;
		if (isEncrypt == null || isEncrypt == true) {
			idDecrypt = CryptoHelper.decrypt(idEncrypt);
		} else {
			idDecrypt = idEncrypt;
		}
		int careerId = Lib.getIntValue(idDecrypt);
		Career career = careerRepo.findOne(careerId);
		InputStream is = null;
		if (career != null && StringUtils.hasText(career.getAdditionalInfo())) {
			try {
				File additionalInfoAttachment = new File(career.getAdditionalInfo());
				is = new FileInputStream(additionalInfoAttachment);
				FileUtil.sendStreamToUser(is, request, response, additionalInfoAttachment.getName());
			} catch (IOException ex) {
				log.error(String.format("Error writing additional info attachment file of career[careerId = %s] to output stream.", careerId), ex);
			}
		}
	}
}
