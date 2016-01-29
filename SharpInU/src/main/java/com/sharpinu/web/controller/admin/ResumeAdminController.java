package com.sharpinu.web.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sharpinu.constant.WebConstants;
import com.sharpinu.persist.domain.Resume;
import com.sharpinu.persist.domain.Ticket;
import com.sharpinu.persist.repositories.ResumeRepo;
import com.sharpinu.persist.repositories.TicketRepo;
import com.sharpinu.service.IAskForAdviceService;
import com.sharpinu.service.admin.IResumeService;
import com.sharpinu.util.CryptoHelper;
import com.sharpinu.util.FileUtil;
import com.sharpinu.util.JsonResult;
import com.sharpinu.util.Lib;
import com.sharpinu.web.bean.ResumeBean;
import com.sharpinu.web.controller.BaseController;
import com.sharpinu.web.form.ResumeForm;

@Controller
@RequestMapping(value = "/admin/resume")
@SessionAttributes("resumeForm")
public class ResumeAdminController extends BaseController {

	@Autowired
	IAskForAdviceService askForAdviceService;

	@Autowired
	ResumeRepo resumeRepo;

	@Autowired
	IResumeService reesumeService;

	@Autowired
	TicketRepo ticketRepo;

	JsonResult ufile;

	@ModelAttribute("resumeForm")
	public ResumeForm postJobForm() {
		return new ResumeForm(); // populates form for the first time if its
		// null
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String previewResume(@PathVariable("id") String idEncrypt, HttpServletRequest request, HttpServletResponse response, Model model) {
		String idDecrypt = CryptoHelper.decrypt(idEncrypt);
		int resumeId = Lib.getIntValue(idDecrypt);
		Resume resume = resumeRepo.findOne(resumeId);
		model.addAttribute("resume", resume);
		model.addAttribute("idEncrypt", idEncrypt);
		return WebConstants.Views.RESUME_EDIT;
	}

	@RequestMapping(value = "/resume_image", method = RequestMethod.GET)
	public void showResumeImage(@RequestParam("id") String idEncrypt, @RequestParam("isEncrypt") Boolean isEncrypt, HttpServletResponse response, HttpServletRequest request) throws ServletException,
			IOException {
		String idDecrypt = null;
		if (isEncrypt == null || isEncrypt == true) {
			idDecrypt = CryptoHelper.decrypt(idEncrypt);
		} else {
			idDecrypt = idEncrypt;
		}
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
	public void viewCVAttachment(@RequestParam("id") String idEncrypt, @RequestParam(value = "isEncrypt", required = false) Boolean isEncrypt, HttpServletRequest request, HttpServletResponse response) {
		String idDecrypt = null;
		if (isEncrypt == null || isEncrypt == true) {
			idDecrypt = CryptoHelper.decrypt(idEncrypt);
		} else {
			idDecrypt = idEncrypt;
		}
		int resumeId = Lib.getIntValue(idDecrypt);
		Resume resume = resumeRepo.findOne(resumeId);
		InputStream is = null;
		if (resume != null && StringUtils.hasText(resume.getCv())) {
			try {
				File cvAttachment = new File(resume.getCv());
				is = new FileInputStream(cvAttachment);
				FileUtil.sendStreamToUser(is, request, response, cvAttachment.getName());
			} catch (IOException ex) {
				log.error(String.format("Error writing cv attachment file of resume[resumeId = %s] to output stream.", resumeId), ex);
			}
		}
	}

	@RequestMapping("/{pageIndex}/list")
	public String showResumes(HttpServletRequest request, HttpServletResponse response, @PathVariable("pageIndex") int pageIndex, Model model) throws IOException {

		Page<Resume> page = reesumeService.getResumePageInfo(pageIndex - 1);
		List<ResumeBean> categories = reesumeService.findResumeViewBean(pageIndex - 1, page);

		model.addAttribute("resumes", categories);
		model.addAttribute("page", page);

		int current = page.getNumber() + 1;
		int begin = 1;
		int end = page.getTotalPages();
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);

		return WebConstants.Views.RESUME_LIST;
	}

	@RequestMapping("/{id}/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id, Model model) throws IOException {
		resumeRepo.delete(id);
		return "redirect:" + "/admin/resume/1/list.in";
	}

	@RequestMapping(value = "/{id}/view", method = RequestMethod.GET)
	public String viewResume(@PathVariable("id") int categoryId, HttpServletRequest request, HttpServletResponse response, Model model) {
		Resume resume = resumeRepo.findOne(categoryId);
		ResumeBean resumeBean = new ResumeBean(resume);
		Ticket ticket = this.ticketRepo.findByCompanyId(resume.getResumeId());
		resumeBean.setTicket(ticket);
		model.addAttribute("resume", resumeBean);

		String idEncrypt = CryptoHelper.encrypt(String.valueOf(resume.getResumeId()));
		model.addAttribute("idEncrypt", idEncrypt);

		return WebConstants.Views.RESUME_VIEW;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody JsonResult upload(MultipartHttpServletRequest request, HttpServletResponse response) {

		JsonResult jsonResult = new JsonResult();
		try {
			Iterator<String> itr = request.getFileNames();
			MultipartFile mpf = request.getFile(itr.next());
			log.info(mpf.getOriginalFilename() + " uploaded!");

			String idEncrypt = request.getParameter("id");
			String idDecrypt = CryptoHelper.decrypt(idEncrypt);
			int resumeId = Lib.getIntValue(idDecrypt);
			log.info("resum id:" + resumeId);
			Resume resume = resumeRepo.findOne(resumeId);
			askForAdviceService.saveUploadCv(mpf, resume);
			jsonResult.type = "sucess";
			jsonResult.msg = "File uploaded!";
			log.info("File uploaded!");
			// Lis
			// JSON obj = new JSONPObject(), resume)
			// String helloString = new String("khanh");
			// 2. send it back to the client as <img> that calls get method
			// we are using getTimeInMillis to avoid server cached image
			return jsonResult;
		} catch (Exception e) {
			jsonResult.type = "fail";
			jsonResult.msg = "Fail to Upload:" + e.getMessage();
		}
		return jsonResult;
	}

}
