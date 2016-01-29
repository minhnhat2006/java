package com.sharpinu.web.form.admin;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import com.sharpinu.persist.domain.News;

public class NewsForm {

    @NotBlank(message = "Title is required.")
    @Size(min = 0, max = 1024, message = "Length of Title must be from 1 to 1024 characters.")
    private String title;
    @NotBlank(message = "Summary is required.")
    @Size(min = 0, max = 1024, message = "Length of Summary must be from 1 to 1024 characters.")
    private String summary;

    @NotBlank(message = "Content is required.")
    @Size(min = 0, max = 65536, message = "Length of Content must be from 1 to 65536 characters.")
    private String content;

    private Date createdDate;
    private int userId;

    private int newsId;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public static NewsForm fromPost(News news) {
        NewsForm newsForm = new NewsForm();
        BeanUtils.copyProperties(news, newsForm);
        newsForm.setNewsId(news.getNewsId());
        return newsForm;
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
