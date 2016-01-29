package com.sharpinu.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharpinu.persist.domain.TTest;
import com.sharpinu.persist.domain.TUserTest;
import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.repositories.TUserTestRepo;
import com.sharpinu.web.bean.TestTrackingBean;

/**
 *
 * @author vodinh90@gmail.com
 *
 */

@Service("tUserTestService")
public class TUserTestService extends BaseService implements ITUserTestService {
	private static final int SCORE_PER_QUESTION = 100;
	
	@Autowired
	private TUserTestRepo tUserTestRepo;
	
	public TUserTest saveTUserTest(TTest tTest, User user) {
		TUserTest tUserTest = new TUserTest(tTest, user);
		return tUserTestRepo.save(tUserTest);
	}
	
	public TUserTest findByUserIdAndTestId(int userId, int testId) {
		return tUserTestRepo.findByUserIdAndTestId(userId, testId);
	}
	
	public TUserTest updateUserTest(TUserTest tUserTest) {
		return tUserTestRepo.save(tUserTest);
	}
	
	public TUserTest updateScoreAndCorrectCountTest(User currentUser, TestTrackingBean testBean) {
		int correctCount = testBean.getCorrectCount();
		int score = correctCount * SCORE_PER_QUESTION;
		//find TUserTest current
		TUserTest tUserTest = findByUserIdAndTestId(currentUser.getUserId(), testBean.gettTest().getTestId());
		tUserTest.setCorrectCount(correctCount);
		tUserTest.setScore(score);
		tUserTest.setFinishedDated(new Date(System.currentTimeMillis()));
		tUserTest = updateUserTest(tUserTest);
		return tUserTest;
	}
	
}
