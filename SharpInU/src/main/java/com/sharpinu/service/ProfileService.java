package com.sharpinu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharpinu.persist.domain.Profile;
import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.repositories.ProfileRepo;

/**
 * Contain operations related to Profile
 * 
 * @author hieutran83
 *
 */
@Service("profileService")
public class ProfileService extends BaseService implements IProfileService {
	
	@Autowired
	ProfileRepo profileRepo;

	public List<Profile> findByUserId(int userId) {
		return this.profileRepo.findByUser(new User(userId));
	}
	
	public Profile findOneByUserId(int userId) {
		return this.profileRepo.findOneByUserId(userId);
	}
	
	public void create(User user, com.sharpinu.persist.domain.File file,
						String tagline, String overview, int hourlyRate,
						String experience, String serviceDescription) {
		Profile profile = new Profile();
		profile.setUser(user);
		profile.setFile(file);
		profile.setTagline(tagline);
		profile.setOverview(overview);
		profile.setHourlyRate(hourlyRate);
		profile.setExperience(experience);
		profile.setServiceDescription(serviceDescription);
		
		this.profileRepo.save(profile);
	}
	
	public void update(int profileId, com.sharpinu.persist.domain.File file,
						String tagline, String overview, int hourlyRate,
						String experience, String serviceDescription) {
		Profile profile = this.profileRepo.findOne(profileId);
		profile.setFile(file);
		profile.setTagline(tagline);
		profile.setOverview(overview);
		profile.setHourlyRate(hourlyRate);
		profile.setExperience(experience);
		profile.setServiceDescription(serviceDescription);
		
		this.profileRepo.save(profile);
	}
	
	public void delete(int profileId) {
		this.profileRepo.delete(profileId);
	}
}
