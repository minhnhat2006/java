/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.persist.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import com.sharpinu.persist.domain.News;

/**
 *
 * @author khanh nguyen
 */
public interface NewsCustomRepo<News, Integer> {

    public News findLatestNews();

    public List<News> findByContentContainingOrderCreatedDesc(String term, Date createdDate, int limit);

    public int countContentContainingOrderCreatedDesc(String term, Date createdDate);
}
