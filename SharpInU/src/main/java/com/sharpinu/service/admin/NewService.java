/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.service.admin;

import com.sharpinu.persist.domain.News;

import com.sharpinu.persist.domain.User;

import com.sharpinu.persist.repositories.NewsRepo;

import com.sharpinu.persist.repositories.UserRepo;
import com.sharpinu.service.BaseService;
import com.sharpinu.service.IRepositoryService;
import com.sharpinu.web.bean.NewsBean;
import com.sharpinu.web.form.admin.NewsForm;

import java.util.ArrayList;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author khanh nguyen
 */
@Service
public class NewService extends BaseService implements INewsService {

    final int PAGE_SIZE = 5;
    @Autowired
    NewsRepo newsRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    IRepositoryService repositoryService;

    public String saveNewsForm(NewsForm newsForm) {
        try {
            News news = new News(newsForm);
            newsRepo.save(news);
            newsForm.setNewsId(news.getNewsId());
        } catch (Exception e) {
            throw new RuntimeException("Failed to save News Form", e);
        }

        return null;
    }

    public Page<News> getNewsPageInfo(int pageIndex) {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "createdDate"));
        Pageable pageable = new PageRequest(pageIndex, PAGE_SIZE, sort);
        Page<News> page = newsRepo.findAll(pageable);
        return page;
    }

    public List<NewsBean> findNewsViewBean(int pageIndex, Page<News> newsList) {
        try {
            List<NewsBean> newsBeans = new ArrayList<NewsBean>();
            for (News news : newsList) {

                User user = userRepo.findOne(news.getUserId());
                NewsBean newsBean = new NewsBean(news.getNewsId(), news.getTitle(), news.getContent(), news.getSummary());

                if (user != null) {
                    newsBean.setUserId(user.getUserId());
                    newsBean.setUserEmail(user.getUserEmail());
                }

                newsBeans.add(newsBean);
            }
            return newsBeans;
        } catch (Exception e) {
            log.error("Failed to findPostViewBean", e);
            throw new RuntimeException("Failed to findPostViewBean", e);
        }
    }
}
