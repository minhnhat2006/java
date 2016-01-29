package com.sharpinu.service;

import java.util.List;
import java.util.Date;

import com.sharpinu.persist.domain.UEmployment;
import com.sharpinu.persist.domain.User;

/**
 * Contain operations related to UEducation
 * 
 * @author tthieucdl@gmail.com
 *
 */
public interface IUEmploymentService extends IBaseService{

	public List<UEmployment> findByUserId(int userId);
	
	public void create(User user, String clientName, String positionHeld,
						Date startDate, Date endDate, String description);
	
	public void update(int uEmploymentId, String clientName, String positionHeld, 
						Date startDate, Date endDate, String description);
	
	public void delete(int uEmploymentId);
}
