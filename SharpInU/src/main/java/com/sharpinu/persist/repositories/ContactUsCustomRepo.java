package com.sharpinu.persist.repositories;

import java.util.Date;
import java.util.List;

import com.sharpinu.persist.BaseCustomRepo;
import com.sharpinu.persist.domain.ContactUs;

/**
 *
 * @author Mark
 *
 */
public interface ContactUsCustomRepo<ContactUs, Integer>  extends BaseCustomRepo {
	List<ContactUs> findInDateRange(Date from, Date to);
}