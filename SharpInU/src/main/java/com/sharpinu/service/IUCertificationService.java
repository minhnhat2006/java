package com.sharpinu.service;

import java.util.Date;
import java.util.List;

import com.sharpinu.persist.domain.UCertification;
import com.sharpinu.persist.domain.User;

/**
 * Contain operations related to UCertification
 * 
 * @author tthieucdl@gmail.com
 *
 */
public interface IUCertificationService extends IBaseService{

	public List<UCertification> findByUserId(int userId);
	
	public void create(User user, String conferringOrganization, String professionalCertificate,
			Date dateAwarded, String certificateNumber, String description);
	
	public void update(int uCertificationId, String conferringOrganization, String professionalCertificate,
			Date dateAwarded, String certificateNumber, String description);
	
	public void delete(int uCertificationId);
}
