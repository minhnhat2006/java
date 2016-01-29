package sharpinu.repo;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import sharpinu.BaseTester;

import com.sharpinu.persist.repositories.UserRoleRepo;

/**
 *
 * @author Mark
 *
 */
public class UserRoleRepoTester extends BaseTester {

	@Autowired@Qualifier("userRoleRepo")
	UserRoleRepo userRoleRepo;
	
	@Test
	public void test() {
		List<String> roleNames = userRoleRepo.findRoleNameByUserId(19);
		for (String roleName : roleNames) {
			System.out.println(roleName);
		}
	}
	
}