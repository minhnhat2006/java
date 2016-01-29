package sharpinu.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import sharpinu.BaseTester;

import com.sharpinu.persist.domain.User;
import com.sharpinu.service.IUserService;
import com.sharpinu.web.form.UserSignUpForm;

/**
 *
 * @author Mark
 *
 */

public class UserServiceTester extends BaseTester {

	@Autowired@Qualifier("userService")
	IUserService userService;
	
	@Test
	public void test() {
		UserSignUpForm userSignUpForm = new UserSignUpForm();
		userSignUpForm.setEmail("testuser@gmail.com");
		userSignUpForm.setPassword("123123");
		userSignUpForm.setFirstName("Test");
		userSignUpForm.setLastName("User");
		User user = userService.createUserAccount(userSignUpForm);
		System.out.println(user.getUserRoles().size());
	}
	
}