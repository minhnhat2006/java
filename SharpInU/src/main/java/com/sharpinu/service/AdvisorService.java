/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sharpinu.email.IMailSender;
import com.sharpinu.persist.repositories.AdvisorRepo;
import com.sharpinu.persist.repositories.TicketRepo;
import com.sharpinu.persist.repositories.UserRepo;
import com.sharpinu.web.bean.session.UserPreferences;

/**
 *
 * @author nhatnguyen
 */
@Service
public class AdvisorService extends BaseService implements IAdvisorService {

	@Autowired
	private TicketRepo ticketRepo;

	@Autowired
	private AdvisorRepo advisorRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	@Qualifier("mailSender")
	IMailSender sender;

	@Autowired
	private UserPreferences userPreferences;

}
