/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.service;

import java.util.List;

import com.sharpinu.web.bean.CommentBean;
import com.sharpinu.web.form.CommentForm;

/**
 *
 * @author khanh nguyen
 */
public interface ICommentService extends IBaseService {

	CommentBean addComment(CommentForm commentForm, String contextPath);

	List<CommentBean> getCommentsByTicketId(int ticketId, int offset, int pageSize);

	int getNumOfCommentsByTicketId(int ticketId);

}
