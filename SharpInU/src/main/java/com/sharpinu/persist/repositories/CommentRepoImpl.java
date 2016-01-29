/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.persist.repositories;

import com.sharpinu.persist.BaseRepoImpl;
import com.sharpinu.persist.domain.Comment;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author khanh nguyen
 */
public class CommentRepoImpl extends BaseRepoImpl implements CommentCustomRepo<Comment, Integer> {

    @Autowired
    private CommentRepo commentRepo;

    public List<Comment> getCommentByTicketId(int ticketId, int offset, int pageSize) {
        try {
            String queryString = "SELECT c FROM Comment c WHERE c.ticket.ticketId = :ticketId  ORDER BY c.createdDate ASC";
            Query query = this.em.createQuery(queryString).setParameter("ticketId", ticketId);
            if (offset < 0) {
                offset = 0;
            }
            if (pageSize > 0) {
                query.setMaxResults(pageSize);
            }
            query.setFirstResult(offset);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(String.format("Failed to find tickets by current user", offset), e);
        }

    }

    public int getCountCommentsByTicketId(int ticketId) {
        try {
            String queryString = "SELECT COUNT(c) FROM Comment c WHERE c.ticket.ticketId = :ticketId";
            Query query = this.em.createQuery(queryString).setParameter("ticketId", ticketId);
            return ((Long) query.getSingleResult()).intValue();
        } catch (Exception e) {
            throw new RuntimeException(String.format("Failed to count comments", ticketId), e);
        }
    }

}
