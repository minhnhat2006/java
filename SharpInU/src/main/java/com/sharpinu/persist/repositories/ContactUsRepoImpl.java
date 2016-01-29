package com.sharpinu.persist.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sharpinu.persist.BaseRepoImpl;
import com.sharpinu.persist.domain.ContactUs;

/**
 *
 * @author Mark
 *
 */
public class ContactUsRepoImpl extends BaseRepoImpl implements ContactUsCustomRepo<ContactUs, Integer> {
	@Autowired
	private ContactUsRepo contactUsRepo;

	public List<ContactUs> findInDateRange(Date from, Date to) {
		try {
			List<ContactUs> results = new ArrayList<ContactUs>();
			if (from == null && to == null) {
				results = contactUsRepo.findAll();
			} else {
				results = contactUsRepo.findByCreatedDateBetween(from, to);
			}
			return results;
		} catch (Exception e) {
			throw new RuntimeException("Failed to find ContactUs", e);
		}
	}

}