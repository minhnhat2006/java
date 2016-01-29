package com.sharpinu.service;

import com.sharpinu.persist.domain.TQuestion;
import com.sharpinu.persist.domain.TTest;
import com.sharpinu.persist.domain.TTestQuestion;

/**
 *
 * @author vodinh90@gmail.com
 *
 */
public interface ITTestQuestionService extends IBaseService {
	public TTestQuestion saveTTestQuestion(TQuestion tQuestion, TTest tTest, int sequence);
}
