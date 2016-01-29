/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.persist.repositories;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.Category;
import com.sharpinu.persist.domain.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author khanh nguyen
 */
public interface NewsRepo extends BaseRepo<News, Integer>, NewsCustomRepo<News, Integer> {

    

    public Page<News> findByContentContaining(String term, Pageable pageable);
}
