/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.web.bean;

import com.sharpinu.persist.domain.Ticket;
import java.util.Date;

/**
 *
 * @author khanh nguyen
 */
public class CommentBean {
    private Integer ticketConvId;

    private Integer ticketId;
        
    private Integer owner;

    private String createdDate;

    private String content;
    
    private Ticket ticket;
    
    private String userName;

    /**
     * @return the ticketConvId
     */
    public Integer getTicketConvId() {
        return ticketConvId;
    }

    /**
     * @param ticketConvId the ticketConvId to set
     */
    public void setTicketConvId(Integer ticketConvId) {
        this.ticketConvId = ticketConvId;
    }

    /**
     * @return the ticketId
     */
    public Integer getTicketId() {
        return ticketId;
    }

    /**
     * @param ticketId the ticketId to set
     */
    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    /**
     * @return the owner
     */
    public Integer getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    /**
     * @return the createdDate
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the ticket
     */
    public Ticket getTicket() {
        return ticket;
    }

    /**
     * @param ticket the ticket to set
     */
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
