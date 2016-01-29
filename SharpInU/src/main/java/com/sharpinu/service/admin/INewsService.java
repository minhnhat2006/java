/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.service.admin;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sharpinu.persist.domain.News;
import com.sharpinu.service.IBaseService;
import com.sharpinu.web.bean.NewsBean;

import com.sharpinu.web.form.admin.NewsForm;

/**
 *
 * @author khanh nguyen
 */
public interface INewsService extends IBaseService {

    String saveNewsForm(NewsForm newsForm);

    /**
     * Find posts with pageIndex criteria
     *
     * @param pageIndex
     * @return
     */
    Page<News> getNewsPageInfo(int pageIndex);

    /**
     * Find posts with pageIndex criteria
     *
     * @param pageIndex
     * @param newsBean
     * @return
     */
    List<NewsBean> findNewsViewBean(int pageIndex, Page<News> news);
}
