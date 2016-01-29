package com.sharpinu.service;

import com.sharpinu.persist.domain.TTest;
import com.sharpinu.persist.domain.TUserTest;
import com.sharpinu.persist.domain.User;
import com.sharpinu.web.bean.TestTrackingBean;

/**
 *
 * @author vodinh90@gmail.com
 *
 */
public interface ITUserTestService extends IBaseService {
	public TUserTest saveTUserTest(TTest tTest, User user);
	
	public TUserTest findByUserIdAndTestId(int userId, int testId);
	
	public TUserTest updateUserTest(TUserTest tUserTest);
	
	public TUserTest updateScoreAndCorrectCountTest(User currentUser, TestTrackingBean testBean);
}
