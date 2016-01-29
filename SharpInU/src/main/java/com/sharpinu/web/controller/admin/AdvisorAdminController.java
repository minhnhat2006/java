package com.sharpinu.web.controller.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sharpinu.constant.WebConstants;
import com.sharpinu.persist.domain.Advisor;
import com.sharpinu.persist.domain.AdvisorPost;
import com.sharpinu.persist.domain.Post;
import com.sharpinu.persist.repositories.AdvisorPostRepo;
import com.sharpinu.persist.repositories.AdvisorRepo;
import com.sharpinu.persist.repositories.PostRepo;
import com.sharpinu.web.bean.admin.AdvisorImportRowBean;
import com.sharpinu.web.controller.BaseController;
import com.sharpinu.web.form.admin.ImportAdvisorForm;

@Controller
@RequestMapping(value = "/admin/advisor")
public class AdvisorAdminController extends BaseController {

	@Autowired
	AdvisorRepo advisorRepo;

	@Autowired
	AdvisorPostRepo advisorPostRepo;

	@Autowired
	PostRepo postRepo;

	@ModelAttribute
	public ImportAdvisorForm importAdvisorForm() {
		return new ImportAdvisorForm();
	}

	@RequestMapping(value = "/ticket/{ticketId}/import", method = RequestMethod.GET)
	public String viewImportForm(HttpServletRequest request, HttpServletResponse response, @PathVariable("ticketId") int ticketId, Model model) {
		return WebConstants.Views.ADMIN_ADVISOR_IMPORT;
	}

	@RequestMapping(value = "/ticket/{ticketId}/import", method = RequestMethod.POST)
	public String importCsvFile(HttpServletRequest request, HttpServletResponse response, @PathVariable("ticketId") int ticketId, RedirectAttributes redirectAttributes,
			@Valid @ModelAttribute ImportAdvisorForm importAdvisorForm, BindingResult result, Model model) {
		boolean hasErrors = result.hasErrors();

		if (!hasErrors && !importAdvisorForm.getCsvFile().isEmpty()) {
			List<AdvisorImportRowBean> rows = new ArrayList<AdvisorImportRowBean>();
			MultipartFile csvFile = importAdvisorForm.getCsvFile();
			InputStream inputStream;
			int lineNumber = 1;

			try {
				inputStream = csvFile.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				String line;
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm");

				while ((line = bufferedReader.readLine()) != null) {
					if (lineNumber > 1 && !StringUtils.isEmpty(line)) {
						String[] arrayVals = line.split(",", 3);
						try {
							Date fromDate = formatter.parse(arrayVals[0]);
							Date toDate = formatter.parse(arrayVals[1]);

							SecureRandom random = new SecureRandom();
							String token = new BigInteger(130, random).toString(32);

							// Create Advisor record
							Advisor advisor = new Advisor();
							advisor.setFromDate(fromDate);
							advisor.setToDate(toDate);
							advisor.setCreatedDate(new Date());
							advisor.setTicketId(ticketId);
							advisor.setToken(token);
							advisorRepo.save(advisor);

							// Create Advisor_Post records
							String urls = arrayVals[2].replace('"', ' ');
							String[] urlVals = urls.split(",");

							for (String url : urlVals) {
								String slug = this.getSlugFromUrl(url);
								Post post = postRepo.findOneBySlug(slug);

								if (post != null) {
									AdvisorPost advisorPost = new AdvisorPost();
									advisorPost.setAdvisorId(advisor.getAdvisorId());
									advisorPost.setPostId(post.getPostId());
									advisorPostRepo.save(advisorPost);
								}
							}

							rows.add(new AdvisorImportRowBean(advisor.getAdvisorId(), ticketId, token, fromDate, toDate, urls));
						} catch (ParseException e) {
							model.addAttribute("successMsg", "Error reading file at line " + String.valueOf(lineNumber));
						}

					}

					lineNumber++;
				}

				model.addAttribute("advisors", rows);
				model.addAttribute("successMsg", "File has been imported successfully.");

			} catch (IOException e) {
				model.addAttribute("successMsg", "Error reading file at line " + String.valueOf(lineNumber));
			}
		}

		return WebConstants.Views.ADMIN_ADVISOR_IMPORT;
	}

	private String getSlugFromUrl(String url) {
		String[] urlParts = url.trim().split("\\/");

		if (urlParts.length == 0) {
			return "";
		}

		String lastUrl = urlParts[urlParts.length - 1];

		if ("view.in".equals(lastUrl)) {
			lastUrl = urlParts[urlParts.length - 2];
		}

		String[] lastUrlParts = lastUrl.split("#");

		if (lastUrlParts.length == 0) {
			return "";
		}

		return lastUrlParts[lastUrlParts.length - 1];
	}
}
