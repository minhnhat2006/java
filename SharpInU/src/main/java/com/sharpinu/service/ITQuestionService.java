package com.sharpinu.service;

import com.sharpinu.persist.domain.TQuestion;

/**
 *
 * @author vodinh90@gmail.com
 *
 */
public interface ITQuestionService extends IBaseService {
	public TQuestion findById(Integer questionId);
}
