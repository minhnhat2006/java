package com.sharpinu.persist.repositories;

import java.util.Date;
import java.util.List;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.ContactUs;

/**
 *
 * @author Mark
 *
 */
public interface ContactUsRepo extends BaseRepo<ContactUs, Integer>, ContactUsCustomRepo<ContactUs, Integer> {

	List<ContactUs> findByCreatedDateBetween(Date from, Date to);

}