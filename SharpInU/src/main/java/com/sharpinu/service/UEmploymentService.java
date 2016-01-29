package com.sharpinu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharpinu.persist.domain.UEmployment;
import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.repositories.UEmploymentRepo;

/**
 * Contain operations related to UEmployment
 * 
 * @author tthieucdl@gmail.com
 *
 */
@Service("uEmploymentService")
public class UEmploymentService extends BaseService implements IUEmploymentService {

	@Autowired
	private UEmploymentRepo uEmploymentRepo;
	
	public List<UEmployment> findByUserId(int userId) {
		return uEmploymentRepo.findByUser(new User(userId));
	}
	
	public void create(User user, String clientName, String positionHeld,
			Date startDate, Date endDate, String description) {
		UEmployment uEmployment = new UEmployment();
		uEmployment.setUser(user);
		uEmployment.setClientName(clientName);
		uEmployment.setPositionHeld(positionHeld);
		uEmployment.setStartDate(startDate);
		uEmployment.setEndDate(endDate);
		uEmployment.setDescription(description);
		
		this.uEmploymentRepo.save(uEmployment);
	}
	
	public void update(int uEmploymentId, String clientName, String positionHeld, 
			Date startDate, Date endDate, String description) {
		UEmployment uEmployment = this.uEmploymentRepo.findOne(uEmploymentId);
		// Must check null here or throw item not found? Later
		uEmployment.setClientName(clientName);
		uEmployment.setPositionHeld(positionHeld);
		uEmployment.setStartDate(startDate);
		uEmployment.setEndDate(endDate);
		uEmployment.setDescription(description);
		
		this.uEmploymentRepo.save(uEmployment);
	}
	
	public void delete(int uEmploymentId) {
		this.uEmploymentRepo.delete(uEmploymentId);
	}
}
