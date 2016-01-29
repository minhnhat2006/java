/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.web.bean;

import org.directwebremoting.annotations.DataTransferObject;

/**
 *
 * @author khanh nguyen
 */
@DataTransferObject
public class NewsBean {

    Integer newsId;
    private String title;
    String content;
    String summary;
    String createdDate;
    String updatedDate;

    String userEmail;
    int userId;

    public NewsBean(Integer newsId, String title, String content, String summary) {
        super();
        this.newsId = newsId;
        this.title = title;
        this.content = content;
        this.summary = summary;
    }

    public NewsBean() {
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
