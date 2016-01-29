package com.sharpinu.service;

import java.util.List;
import java.util.Date;

import com.sharpinu.persist.domain.ULicense;
import com.sharpinu.persist.domain.User;

/**
 * Contain operations related to ULicense
 * 
 * @author tthieucdl@gmail.com
 *
 */
public interface IULicenseService extends IBaseService {

	public List<ULicense> findByUserId(int userId);
	
	public void create(User user, String conferringOrganization, String professionalLicense,
						Date dateIssued, String licenseNumber, String description);
	
	public void update(int uLicenseId, String conferringOrganization, String professionalLicense,
						Date dateIssued, String licenseNumber, String description);
	
	public void delete(int uLicenseId);
}
