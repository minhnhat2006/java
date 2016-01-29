package com.sharpinu.service;

import com.sharpinu.persist.domain.User;
import com.sharpinu.web.form.UserSignUpForm;

public interface IUserService extends IBaseService {
	/**
	 * Create user with role USER
	 * @return
	 */
	public User createUserAccount(UserSignUpForm userSignUpForm);
	
	/**
	 * Check if email has been registered before.
	 * 
	 * @param email
	 * @return
	 */
	public boolean emailIsRegisteredBefore(String email);

	void generatePasswordHash(User user, String contextPath);

	void changePassword(User user, String newPassword, String contextPath);
}
