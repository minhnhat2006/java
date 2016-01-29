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
import com.sharpinu.persist.domain.Company;
import com.sharpinu.persist.domain.Ticket;
import com.sharpinu.persist.repositories.CompanyRepo;
import com.sharpinu.persist.repositories.TicketRepo;
import com.sharpinu.service.admin.ICompanyService;
import com.sharpinu.util.CryptoHelper;
import com.sharpinu.util.FileUtil;
import com.sharpinu.util.Lib;
import com.sharpinu.web.bean.CompanyBean;
import com.sharpinu.web.controller.BaseController;

@Controller
@RequestMapping(value = "/admin/company")
public class CompanyAdminController extends BaseController {

	@Autowired
	ICompanyService companyService;

	@Autowired
	CompanyRepo companyRepo;

	@Autowired
	TicketRepo ticketRepo;

	@RequestMapping("/{pageIndex}/list")
	public String showCompanies(HttpServletRequest request, HttpServletResponse response, @PathVariable("pageIndex") int pageIndex, Model model) throws IOException {

		Page<Company> page = companyService.getCompanyPageInfo(pageIndex - 1);
		List<CompanyBean> companies = companyService.findCompanyViewBean(pageIndex - 1, page);

		model.addAttribute("companies", companies);
		model.addAttribute("page", page);

		int current = page.getNumber() + 1;
		int begin = 1;
		int end = page.getTotalPages();
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);

		return WebConstants.Views.COMPANY_LIST;
	}

	@RequestMapping("/{id}/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id, Model model) throws IOException {
		companyRepo.delete(id);
		return "redirect:" + "/admin/company/1/list.in";
	}

	@RequestMapping(value = "/{id}/view", method = RequestMethod.GET)
	public String viewCompany(@PathVariable("id") int categoryId, HttpServletRequest request, HttpServletResponse response, Model model) {
		Company company = companyRepo.findOne(categoryId);
		String idEncrypt = CryptoHelper.encrypt(String.valueOf(company.getCompanyId()));
		model.addAttribute("idEncrypt", idEncrypt);

		CompanyBean companyBean = new CompanyBean(company);
		Ticket ticket = this.ticketRepo.findByCompanyId(company.getCompanyId());
		companyBean.setTicket(ticket);
		model.addAttribute("company", companyBean);

		return WebConstants.Views.COMPANY_VIEW;
	}

	@RequestMapping(value = "/company_additionalFile", method = RequestMethod.GET)
	public void viewAdditionalInfoAttachment(@RequestParam("id") String idEncrypt, @RequestParam(value = "isEncrypt", required = false) Boolean isEncrypt, HttpServletRequest request,
			HttpServletResponse response) {
		String idDecrypt = null;
		if (isEncrypt == null || isEncrypt == true) {
			idDecrypt = CryptoHelper.decrypt(idEncrypt);
		} else {
			idDecrypt = idEncrypt;
		}
		int companyId = Lib.getIntValue(idDecrypt);
		Company company = companyRepo.findOne(companyId);
		InputStream is = null;
		if (company != null && StringUtils.hasText(company.getAdditionalInfo())) {
			try {
				File additionalInfoAttachment = new File(company.getAdditionalInfo());
				is = new FileInputStream(additionalInfoAttachment);
				FileUtil.sendStreamToUser(is, request, response, additionalInfoAttachment.getName());
			} catch (IOException ex) {
				log.error(String.format("Error writing additional info attachment file of company[companyId = %s] to output stream.", companyId), ex);
			}
		}
	}
}
