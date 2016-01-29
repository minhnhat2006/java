package com.sharpinu.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sharpinu.email.IMailSender;
import com.sharpinu.persist.domain.Role;
import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.domain.UserRole;
import com.sharpinu.persist.repositories.RoleRepo;
import com.sharpinu.persist.repositories.UserRepo;
import com.sharpinu.persist.repositories.UserRoleRepo;
import com.sharpinu.processor.email.MailProcessor;
import com.sharpinu.web.common.util.SecurityUtil;
import com.sharpinu.web.form.UserSignUpForm;

/**
 * Contain operations relate to User
 * 
 * @author Mark
 *
 */
@Service
public class UserService extends BaseService implements IUserService {
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private UserRoleRepo userRoleRepo;

	@Autowired
	@Qualifier("passwordEncoder")
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    @Qualifier("mailSender")
    IMailSender sender;

	/**
	 * Create user with minimum fields and set default properties: +
	 * accountLocked: false + failedLoginAttempts: 0 + passwordExpired: false +
	 * userActive: true
	 * 
	 * @param email
	 * @param password
	 * @param purpose
	 * @return
	 */
	private User createUser(UserSignUpForm userSignUpForm) {
		User user = new User();
		user.setFirstName(userSignUpForm.getFirstName());
		user.setLastName(userSignUpForm.getLastName());
		user.setUserEmail(userSignUpForm.getEmail());
		String enPassword = passwordEncoder.encode(userSignUpForm.getPassword());
		user.setUserPassword(enPassword);
		setDefaultUserProperties(user);
		return userRepo.save(user);
	}

	/**
	 * Initial default properties for user: 
	 * + accountLocked: false 
	 * + failedLoginAttempts: 0 
	 * + passwordExpired: false 
	 * + userActive: true
	 * + purpose: work
	 * 
	 * @param user
	 */
	private void setDefaultUserProperties(User user) {
		user.setAccountLocked(false);
		user.setFailedLoginAttempts(0);
		user.setPasswordExpired(false);
		user.setActive(true);
		user.setPurpose(User.PURPOSE.WORK);
	}

	public boolean emailIsRegisteredBefore(String email) {
		User userByEmail = userRepo.findByUserEmail(email);
		return userByEmail != null;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public User createUserAccount(UserSignUpForm userSignUpForm) {
		try {
			Role role = roleRepo.findByRoleName(Role.ROLE_USER);
			User user = this.createUser(userSignUpForm);
			UserRole userRole = userRoleRepo.save(new UserRole(user, role));
			user.addUserRole(userRole);
			return user;
		} catch (Exception e) {
			throw new RuntimeException(String.format("Failed to create new user[userEmail=%s, role=%s]", userSignUpForm
					.getEmail(), Role.ROLE_USER, e));
		}
	}
	
	public void generatePasswordHash(User user, String contextPath) {
		String passwordHash = SecurityUtil.generatePasswordHash();
		user.setPasswordHash(passwordHash);
		user.setPasswordHashDate(new Timestamp(System.currentTimeMillis()));
		userRepo.save(user);
		sendForgotPasswordEMail(user, contextPath);
	}
	
	 private void sendForgotPasswordEMail(User user, String contextPath) {
		try {
			MailProcessor mailProcessor = new MailProcessor(
					MailProcessor.MailType.RESET_PASSWORD, user, sender);
			mailProcessor.setContextPath(contextPath);
			Thread t = new Thread(mailProcessor);
			t.start();
		} catch (Exception e) {
			log.warn(String.format(
					"Failed to send email forgot password[userEmail= %s]",
					user.getUserEmail()), e);
		}
	}
	 
	public void changePassword(User user, String newPassword, String contextPath) {
		String encryptedPassword = SecurityUtil.encryptPassword(newPassword);
		user.setUserPassword(encryptedPassword);
		user.setPasswordLastChangedDate(new Date());
		user.setPasswordHash(null);
		user.setPasswordHashDate(null);
		
		userRepo.save(user);
		
		this.sendPasswordChangedEMail(user);
	}
	
	private void sendPasswordChangedEMail(User user) {
		try {
			MailProcessor mailProcessor = new MailProcessor(
					MailProcessor.MailType.PASSWORD_CHANGED, user, sender);
			Thread t = new Thread(mailProcessor);
			t.start();
		} catch (Exception e) {
			log.warn(String.format(
					"Failed to send email password chanegd[userEmail= %s]",
					user.getUserEmail()), e);
		}
	}
	
}
