/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.service.admin;

import java.util.List;

import com.sharpinu.service.IBaseService;
import com.sharpinu.web.bean.admin.TicketBean;
import com.sharpinu.web.form.admin.TicketForm;

/**
 *
 * @author administrator
 */
public interface ITicketService extends IBaseService {

	String saveTicketForm(TicketForm ticketForm, String contextPath);

	List<TicketBean> getTicketsByUser(int userId, int offset, int pageIndex);

	long getTotalTickets();
}
