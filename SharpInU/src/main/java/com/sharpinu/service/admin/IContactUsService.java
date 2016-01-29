package com.sharpinu.service.admin;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sharpinu.persist.domain.ContactUs;
import com.sharpinu.service.IBaseService;

/**
 * 
 * @author Mark
 *
 */
public interface IContactUsService extends IBaseService {

	Page<ContactUs> getContactUsPageInfo(int pageIndex);

	List<ContactUs> findContactUsViewBean(int pageIndex,
			Page<ContactUs> contactUses);
}
