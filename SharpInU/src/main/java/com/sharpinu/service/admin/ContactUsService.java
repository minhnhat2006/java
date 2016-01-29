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

import com.sharpinu.persist.domain.ContactUs;
import com.sharpinu.persist.repositories.ContactUsRepo;
import com.sharpinu.service.BaseService;

/**
 * 
 * @author Mark
 *
 */
@Service
public class ContactUsService extends BaseService implements IContactUsService {
	final int PAGE_SIZE = 5;
	@Autowired
	ContactUsRepo contactUsRepo;

	public Page<ContactUs> getContactUsPageInfo(int pageIndex) {
		Sort sort = new Sort(new Order(Direction.DESC, "createdDate"));
		Pageable pageable = new PageRequest(pageIndex, PAGE_SIZE, sort);
		Page<ContactUs> page = contactUsRepo.findAll(pageable);
		return page;
	}

	public List<ContactUs> findContactUsViewBean(int pageIndex, Page<ContactUs> contactUses) {
		List<ContactUs> contactUsBeans = new ArrayList<ContactUs>();
		for (ContactUs contactUs : contactUses) {
			
			contactUsBeans.add(contactUs);
		}
		return contactUsBeans;
	}
}
