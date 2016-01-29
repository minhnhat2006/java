package sharpinu.repo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.repositories.UserRepo;

import sharpinu.BaseTester;

/**
 *
 * @author Mark
 *
 */
public class UserRepoTester extends BaseTester {

	@Autowired@Qualifier("userRepo")
	UserRepo userRepo;
	
	@Test
	public void test() {
		userRepo.findAll();
	}
	
}