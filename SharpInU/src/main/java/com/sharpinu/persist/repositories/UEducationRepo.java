package com.sharpinu.persist.repositories;

import java.util.List;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.UEducation;
import com.sharpinu.persist.domain.User;

public interface UEducationRepo extends BaseRepo<UEducation, Integer>, UEducationCustomRepo<UEducation, Integer> {

	public List<UEducation> findByUser(User user);
}
