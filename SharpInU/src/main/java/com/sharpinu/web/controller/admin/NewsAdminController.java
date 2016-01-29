/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.web.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sharpinu.constant.WebConstants;
import com.sharpinu.persist.domain.News;
import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.repositories.NewsRepo;
import com.sharpinu.service.admin.INewsService;
import com.sharpinu.web.bean.NewsBean;
import com.sharpinu.web.common.util.SecurityUtil;
import com.sharpinu.web.controller.BaseController;
import com.sharpinu.web.form.admin.NewsForm;

/**
 *
 * @author khanh nguyen
 */

@Controller
@RequestMapping(value = "/admin/news")
public class NewsAdminController extends BaseController {

	@Autowired
	INewsService newsService;

	@Autowired
	NewsRepo newsRepo;

	@ModelAttribute
	public NewsForm newForm() {
		return new NewsForm(); // populates form for the first time if its null
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String viewPostAdd(HttpServletRequest request, HttpServletResponse response, @ModelAttribute NewsForm newsForm, BindingResult result, Model model) {

		return WebConstants.Views.NEWS_ADD;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String savePostAdd(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes, @Valid @ModelAttribute NewsForm newsForm, BindingResult result,
			Model model) {
		boolean hasErrors = result.hasErrors();

		if (!hasErrors) {
			User currentUser = SecurityUtil.getCurrentUser();
			if (currentUser != null) {
				newsForm.setUserId(currentUser.getUserId());
			}
			newsService.saveNewsForm(newsForm);
			redirectAttributes.addFlashAttribute("successMsg", "New post has been added.");
			return "redirect:" + "/admin/news/" + newsForm.getNewsId() + "/review.in";
		} else {
			// this.setModelAttributesForPostJob(newsForm, model);
			return WebConstants.Views.NEWS_ADD;
		}
	}

	@RequestMapping(value = "/{id}/review", method = RequestMethod.GET)
	public String reviewPostForm(@PathVariable("id") int newsId, HttpServletRequest request, HttpServletResponse response, Model model) {
		News news = newsRepo.findOne(newsId);
		model.addAttribute("news", news);
		return WebConstants.Views.NEWS_REVIEW;
	}

	@RequestMapping("/{pageIndex}/list")
	public String showNews(HttpServletRequest request, HttpServletResponse response, @PathVariable("pageIndex") int pageIndex, Model model) throws IOException {
		Page<News> page = newsService.getNewsPageInfo(pageIndex - 1);
		List<NewsBean> newsList = newsService.findNewsViewBean(pageIndex - 1, page);
		model.addAttribute("newss", newsList);
		model.addAttribute("page", page);
		int current = page.getNumber() + 1;
		int begin = 1;
		int end = page.getTotalPages();
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		return WebConstants.Views.NEWS_LIST;
	}

	@RequestMapping("/{id}/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id, Model model) throws IOException {
		newsRepo.delete(id);
		return "redirect:" + "/admin/news/1/list.in";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String viewPostEdit(@PathVariable("id") int newsId, HttpServletRequest request, HttpServletResponse response, @ModelAttribute NewsForm newsForm, BindingResult result, Model model) {
		// this.setModelAttributesForPostJob(postForm, model);

		News news = newsRepo.findOne(newsId);
		newsForm = NewsForm.fromPost(news);
		model.addAttribute("newsForm", newsForm);
		model.addAttribute("newsId", newsId);
		return WebConstants.Views.NEWS_EDIT;
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String savePostEdit(@PathVariable("id") int newsId, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,
			@Valid @ModelAttribute NewsForm newsForm, BindingResult result, Model model) {
		boolean hasErrors = result.hasErrors();

		if (!hasErrors) {
			News news = newsRepo.findOne(newsId);
			newsForm.setNewsId(newsId);
			newsForm.setUserId(news.getUserId());
			newsForm.setCreatedDate(news.getCreatedDate());
			newsService.saveNewsForm(newsForm);
			redirectAttributes.addFlashAttribute("successMsg", "New post has been updated.");
			return "redirect:" + "/admin/news/" + String.valueOf(newsId) + "/review.in";
		} else {

			News news = newsRepo.findOne(newsId);
			newsForm = NewsForm.fromPost(news);
			model.addAttribute("newsForm", newsForm);
			model.addAttribute("newsId", newsId);
			return WebConstants.Views.NEWS_EDIT;
		}
	}

}
