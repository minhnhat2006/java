package com.sharpinu.service;

import java.util.List;

import com.sharpinu.persist.domain.TOption;

/**
 *
 * @author vodinh90@gmail.com
 *
 */
public interface ITOptionService {
	public TOption findById(Integer optionId);
	
	public List<Integer> findKeyOptionOfQuestion(Integer questionId);
}
