package sharpinu.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sharpinu.persist.domain.Skill;
import com.sharpinu.persist.repositories.UserRepo;
import com.sharpinu.service.ISkillService;

import sharpinu.BaseTester;

/**
 *
 * @author Mark
 *
 */

public class SkillServiceTester extends BaseTester {

	@Autowired@Qualifier("skillService")
	ISkillService skillService;
	
	@Test
	public void test() {
		try {
			for (int i = 0; i < 100; i++) {
				List<Skill> skills = skillService.findAll();	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}