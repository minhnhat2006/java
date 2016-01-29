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

import com.sharpinu.persist.domain.Resume;
import com.sharpinu.persist.domain.Ticket;
import com.sharpinu.persist.repositories.ResumeRepo;
import com.sharpinu.persist.repositories.TicketRepo;
import com.sharpinu.service.BaseService;
import com.sharpinu.web.bean.ResumeBean;

/**
 * 
 * @author Mark
 *
 */
@Service
public class ResumeService extends BaseService implements IResumeService {

	final int PAGE_SIZE = 5;

	@Autowired
	ResumeRepo resumeRepo;

	@Autowired
	TicketRepo ticketRepo;

	public Page<Resume> getResumePageInfo(int pageIndex) {
		Sort sort = new Sort(new Order(Direction.DESC, "createdDate"));
		Pageable pageable = new PageRequest(pageIndex, PAGE_SIZE, sort);
		Page<Resume> page = resumeRepo.findAll(pageable);
		return page;
	}

	public List<ResumeBean> findResumeViewBean(int pageIndex, Page<Resume> resumes) {
		List<ResumeBean> resumeBeans = new ArrayList<ResumeBean>();

		for (Resume resume : resumes) {
			ResumeBean resumeBean = new ResumeBean(resume);
			Ticket ticket = this.ticketRepo.findByResumeId(resume.getResumeId());
			resumeBean.setTicket(ticket);
			resumeBeans.add(resumeBean);
		}

		return resumeBeans;
	}
}
