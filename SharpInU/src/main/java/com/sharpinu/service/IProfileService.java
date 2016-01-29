package com.sharpinu.service;

import java.util.List;

import com.sharpinu.persist.domain.Profile;
import com.sharpinu.persist.domain.User;

/**
 * Contain operations related to Profile
 * 
 * @author hieutran83
 *
 */
public interface IProfileService extends IBaseService {
	public List<Profile> findByUserId(int userId);
	
	public Profile findOneByUserId(int userId);
	
	public void create(User user, com.sharpinu.persist.domain.File file,
					String tagline, String overview, int hourlyRate,
					String experience, String serviceDescription);
	
	public void update(int profileId, com.sharpinu.persist.domain.File file, 
					String tagline, String overview, int hourlyRate,
					String experience, String serviceDescription);
	
	public void delete(int profileId);
}
