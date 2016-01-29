/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.web.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharpinu.constant.WebConstants;
import com.sharpinu.persist.domain.News;
import com.sharpinu.persist.repositories.NewsRepo;
import com.sharpinu.service.admin.INewsService;
import com.sharpinu.util.DateUtil;
import com.sharpinu.util.Lib;
import com.sharpinu.web.bean.NewsBean;

/**
 *
 * @author khanh nguyen
 */
@Controller
@RequestMapping(value = "/news")
public class NewsController extends BaseController {
	@Autowired
	INewsService newsService;
	@Autowired
	NewsRepo newsRepo;

	@RequestMapping("/{pageIndex}/list")
	public String news(HttpServletRequest request, HttpServletResponse response, @PathVariable("pageIndex") int pageIndex, Model model) throws IOException {
		Page<News> page = newsService.getNewsPageInfo(pageIndex - 1);

		if (page.getTotalElements() > 0) {
			List<NewsBean> newsList = newsService.findNewsViewBean(pageIndex - 1, page);
			model.addAttribute("newsBean", newsList.get(0));
			model.addAttribute("newss", newsList);
			model.addAttribute("page", page);
			int current = page.getNumber() + 1;
			int begin = 1;
			int end = page.getTotalPages();
			model.addAttribute("beginIndex", begin);
			model.addAttribute("endIndex", end);
			model.addAttribute("currentIndex", current);
			return WebConstants.Views.NEWS;
		} else {
			return WebConstants.Views.PAGE_UNDER_CONSTRUCTION;
		}
	}

	@RequestMapping(value = "/get-news", method = RequestMethod.POST)
	public @ResponseBody NewsBean getNewsById(HttpServletRequest request, HttpServletResponse response) {
		int id = Lib.getIntValue(request.getParameter("id"));

		News news = newsRepo.findOne(id);
		NewsBean bean = buildNewsBean(news);
		return bean;
	}

	private NewsBean buildNewsBean(News news) {
		NewsBean newsBean = new NewsBean();
		newsBean.setNewsId(news.getNewsId());
		newsBean.setTitle(news.getTitle());
		newsBean.setSummary(news.getSummary());
		newsBean.setCreatedDate(DateUtil.convertDateTimeToString(news.getCreatedDate().getTime()));
		newsBean.setUpdatedDate(DateUtil.convertDateTimeToString(news.getUpdatedDate().getTime()));
		newsBean.setContent(news.getContent());

		return newsBean;
	}
}
