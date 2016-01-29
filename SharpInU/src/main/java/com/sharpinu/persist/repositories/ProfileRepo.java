package com.sharpinu.persist.repositories;

import java.util.List;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.Profile;
import com.sharpinu.persist.domain.User;


public interface ProfileRepo extends BaseRepo<Profile, Integer>, ProfileCustomRepo<Profile, Integer> {

	public List<Profile> findByUser(User user);

}
