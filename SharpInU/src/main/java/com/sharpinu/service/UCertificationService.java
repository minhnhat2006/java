package com.sharpinu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sharpinu.persist.domain.UCertification;
import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.repositories.UCertificationRepo;

/**
 * Contain operations related to UCertification
 * 
 * @author tthieucdl@gmail.com
 *
 */
@Service("uCertificationService")
public class UCertificationService extends BaseService implements IUCertificationService {

	@Autowired
	private UCertificationRepo uCertificationRepo;
	
	public List<UCertification> findByUserId(int userId) {
		return this.uCertificationRepo.findByUser(new User(userId));
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(User user, String conferringOrganization, String professionalCertificate,
			Date dateAwarded, String certificateNumber, String description) {
		
		UCertification uCertification = new UCertification();
		uCertification.setUser(user);
		uCertification.setConferringOrganization(conferringOrganization);
		uCertification.setProfessionalCertificate(professionalCertificate);
		uCertification.setDateAwarded(dateAwarded);
		uCertification.setCertificateNumber(certificateNumber);
		uCertification.setDescription(description);
		
		this.uCertificationRepo.save(uCertification);	
	}
		
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(int uCertificationId, String conferringOrganization, String professionalCertificate,
			Date dateAwarded, String certificateNumber, String description) {
		UCertification uCertification = uCertificationRepo.findOne(uCertificationId);
		// Must check null here or throw item not found? Later
		uCertification.setConferringOrganization(conferringOrganization);
		uCertification.setProfessionalCertificate(professionalCertificate);
		uCertification.setDateAwarded(dateAwarded);
		uCertification.setCertificateNumber(certificateNumber);
		uCertification.setDescription(description);
		
		this.uCertificationRepo.save(uCertification);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(int uCertificationId) {
		uCertificationRepo.delete(uCertificationId);
	}
}
