package com.sharpinu.service;

import com.sharpinu.persist.domain.Skill;
import com.sharpinu.persist.domain.TTest;

/**
 *
 * @author vodinh90@gmail.com
 *
 */
public interface ITTestService extends IBaseService {
	public TTest saveTTest(int totalQuestion, int totalTime, Skill skill);
}
