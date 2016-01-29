package com.sharpinu.persist.repositories;

import java.util.Date;
import java.util.List;

import com.sharpinu.persist.domain.User;


public interface UserCustomRepo {

	int countInDateRange(Date from, Date to);

	public List<User> findInDateRange(Date from, Date to);
}
