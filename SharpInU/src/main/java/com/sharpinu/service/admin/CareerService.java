package com.sharpinu.service.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.sharpinu.persist.domain.Career;
import com.sharpinu.persist.domain.Ticket;
import com.sharpinu.persist.repositories.CareerRepo;
import com.sharpinu.persist.repositories.TicketRepo;
import com.sharpinu.persist.repositories.UserRepo;
import com.sharpinu.service.BaseService;
import com.sharpinu.web.bean.CareerBean;

/**
 * 
 * @author Mark
 *
 */
@Service
public class CareerService extends BaseService implements ICareerService {

	final int PAGE_SIZE = 5;

	@Autowired
	CareerRepo careerRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	TicketRepo ticketRepo;

	public Page<Career> getCareerPageInfo(int pageIndex) {
		Sort sort = new Sort(new Order(Direction.DESC, "createdDate"));
		Pageable pageable = new PageRequest(pageIndex, PAGE_SIZE, sort);
		Page<Career> page = careerRepo.findAll(pageable);
		return page;
	}

	public List<CareerBean> findCareerViewBean(int pageIndex, Page<Career> careers) {
		List<CareerBean> carrerBeans = new ArrayList<CareerBean>();

		for (Career career : careers) {
			CareerBean careerBean = new CareerBean(career);
			Ticket ticket = this.ticketRepo.findByCareerId(career.getCareerId());
			careerBean.setTicket(ticket);
			carrerBeans.add(careerBean);
		}

		return carrerBeans;
	}
}
