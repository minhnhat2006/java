package com.sharpinu.service.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.repositories.UserRepo;
import com.sharpinu.service.BaseService;

/**
 * 
 * @author Mark
 *
 */
@Service
public class UserAdminService extends BaseService implements IUserAdminService {
	final int PAGE_SIZE = 5;
	@Autowired
	UserRepo userRepo;

	public Page<User> getUserPageInfo(int pageIndex) {
		Sort sort = new Sort(new Order(Direction.DESC, "dateCreated"));
		Pageable pageable = new PageRequest(pageIndex, PAGE_SIZE, sort);
		Page<User> page = userRepo.findAll(pageable);
		return page;
	}

	public List<User> findUserViewBean(int pageIndex, Page<User> users) {
		List<User> carrerBeans = new ArrayList<User>();
		for (User user : users) {
			carrerBeans.add(user);
		}
		return carrerBeans;
	}
}
