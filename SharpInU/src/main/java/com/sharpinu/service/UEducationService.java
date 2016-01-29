package com.sharpinu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sharpinu.persist.domain.UEducation;
import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.repositories.UEducationRepo;

/**
 * Contain operations related to UEducation
 * 
 * @author tthieucdl@gmail.com
 *
 */
@Service("uEducationService")
public class UEducationService extends BaseService implements IUEducationService {

	@Autowired
	private UEducationRepo uEducationRepo;

	public List<UEducation> findByUserId(int userId) {
		return this.uEducationRepo.findByUser(new User(userId));
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void create(User user, String institutionName, String degreeType, Date graduationStartDate, Date graduationEndDate, String description) {
		UEducation uEducation = new UEducation();
		uEducation.setUser(user);
		uEducation.setInstitutionName(institutionName);
		uEducation.setDegreeType(degreeType);
		uEducation.setGraduationStartDate(graduationStartDate);
		uEducation.setGraduationEndDate(graduationEndDate);
		uEducation.setDescription(description);

		this.uEducationRepo.save(uEducation);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(int uEducationId, String institutionName, String degreeType, Date graduationStartDate, Date graduationEndDate, String description) {
		UEducation uEducation = this.uEducationRepo.findOne(uEducationId);
		// Must check null here or throw item not found? Later
		uEducation.setInstitutionName(institutionName);
		uEducation.setDegreeType(degreeType);
		uEducation.setGraduationStartDate(graduationStartDate);
		uEducation.setGraduationEndDate(graduationEndDate);
		uEducation.setDescription(description);

		this.uEducationRepo.save(uEducation);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(int uEducationId) {
		this.uEducationRepo.delete(uEducationId);
	}
}
