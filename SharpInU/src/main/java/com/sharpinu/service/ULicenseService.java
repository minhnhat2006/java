package com.sharpinu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharpinu.persist.domain.ULicense;
import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.repositories.ULicenseRepo;

/**
 * Contain operations related to ULicense
 * 
 * @author tthieucdl@gmail.com
 *
 */
@Service("uLicenseService")
public class ULicenseService extends BaseService implements IULicenseService {

	@Autowired
	private ULicenseRepo uLicenseRepo;
	
	public List<ULicense> findByUserId(int userId) {
		return uLicenseRepo.findByUser(new User(userId));
	}

	public void create(User user, String conferringOrganization, String professionalLicense,
			Date dateIssued, String licenseNumber, String description) {
		ULicense uLicense = new ULicense();
		uLicense.setUser(user);
		uLicense.setConferringOrganization(conferringOrganization);
		uLicense.setProfessionalLicense(professionalLicense);
		uLicense.setDateIssued(dateIssued);
		uLicense.setLicenseNumber(licenseNumber);
		uLicense.setDescription(description);
		
		this.uLicenseRepo.save(uLicense);
	}
	
	public void update(int uLicenseId, String conferringOrganization, String professionalLicense,
			Date dateIssued, String licenseNumber, String description) {
		ULicense uLicense = this.uLicenseRepo.findOne(uLicenseId);
	 	uLicense.setConferringOrganization(conferringOrganization);
	 	uLicense.setProfessionalLicense(professionalLicense);
	 	uLicense.setDateIssued(dateIssued);
	 	uLicense.setLicenseNumber(licenseNumber);
	 	uLicense.setDescription(description);
	 	
	 	this.uLicenseRepo.save(uLicense);
	}
	
	public void delete(int uLicenseId) {
		this.uLicenseRepo.delete(uLicenseId);
	}
}
