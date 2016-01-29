package com.sharpinu.persist.repositories;

import java.util.List;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.UEmployment;
import com.sharpinu.persist.domain.User;

public interface UEmploymentRepo extends BaseRepo<UEmployment, Integer>, UEducationCustomRepo<UEmployment, Integer> {

	public List<UEmployment> findByUser(User user);
}
