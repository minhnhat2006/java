/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.persist.repositories;

import java.util.List;

import com.sharpinu.persist.BaseCustomRepo;

/**
 *
 * @author administrator
 */
public interface TicketCustomRepo<Ticket, Integer> extends BaseCustomRepo {

	List<Ticket> findAllTicketByUserId(int userId, int offset, int pageSize);

	Ticket findByCareerId(int careerId);

	Ticket findByCompanyId(int companyId);

	Ticket findByResumeId(int resumeId);
}
