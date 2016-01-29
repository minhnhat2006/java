package com.sharpinu.persist.repositories;

import java.util.Date;
import java.util.List;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.User;

/**
 * Do operations on [user] table
 * 
 * @author Mark
 *
 */
public interface UserRepo extends BaseRepo<User, Integer>, UserCustomRepo {
	public User findByUserEmail(String userEmail);
	
	public User findByPasswordHash(String passwordHash);

	public List<User> findByDateCreatedBetween(Date from, Date to);

}
