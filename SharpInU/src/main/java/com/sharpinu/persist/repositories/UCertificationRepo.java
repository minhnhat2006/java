package com.sharpinu.persist.repositories;

import java.util.List;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.UCertification;
import com.sharpinu.persist.domain.User;

public interface UCertificationRepo extends BaseRepo<UCertification, Integer>, UCertificationCustomRepo<UCertification, Integer> {

	public List<UCertification> findByUser(User user);
	
}
