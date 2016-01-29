package com.sharpinu.service.admin;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sharpinu.persist.domain.User;
import com.sharpinu.service.IBaseService;

/**
 * 
 * @author mark
 *
 */
public interface IUserAdminService extends IBaseService {

	/**
	 * Find users with pageIndex criteria
	 * 
	 * @param pageIndex
	 * @return
	 */
	Page<User> getUserPageInfo(int pageIndex);

	/**
	 * Find users with pageIndex criteria
	 * 
	 * @param pageIndex
	 * @param users
	 * @return
	 */
	List<User> findUserViewBean(int pageIndex, Page<User> users);
}
