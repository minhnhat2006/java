/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.persist.repositories;

import com.sharpinu.persist.BaseCustomRepo;
import com.sharpinu.persist.domain.Comment;
import java.util.List;

/**
 *
 * @author khanh nguyen
 */
public interface CommentCustomRepo<Comment, Integer> extends BaseCustomRepo{
    public static final String TABLE = "ticket_conv";
    List<Comment> getCommentByTicketId(int ticketId, int offset, int pageSize);
    
    int getCountCommentsByTicketId(int ticketId);
}
