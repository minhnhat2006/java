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

import com.sharpinu.persist.domain.Company;
import com.sharpinu.persist.domain.Ticket;
import com.sharpinu.persist.repositories.CategoryRepo;
import com.sharpinu.persist.repositories.CompanyRepo;
import com.sharpinu.persist.repositories.TicketRepo;
import com.sharpinu.persist.repositories.UserRepo;
import com.sharpinu.service.BaseService;
import com.sharpinu.web.bean.CompanyBean;

/**
 * 
 * @author Mark
 *
 */
@Service
public class CompanyService extends BaseService implements ICompanyService {

	final int PAGE_SIZE = 5;

	@Autowired
	CompanyRepo companyRepo;

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	TicketRepo ticketRepo;

	public Page<Company> getCompanyPageInfo(int pageIndex) {
		Sort sort = new Sort(new Order(Direction.DESC, "createdDate"));
		Pageable pageable = new PageRequest(pageIndex, PAGE_SIZE, sort);
		Page<Company> page = companyRepo.findAll(pageable);
		return page;
	}

	public List<CompanyBean> findCompanyViewBean(int pageIndex, Page<Company> companies) {
		List<CompanyBean> companyBeans = new ArrayList<CompanyBean>();

		for (Company company : companies) {
			CompanyBean companyBean = new CompanyBean(company);
			Ticket ticket = this.ticketRepo.findByCompanyId(company.getCompanyId());
			companyBean.setTicket(ticket);
			companyBeans.add(companyBean);
		}

		return companyBeans;
	}
}
