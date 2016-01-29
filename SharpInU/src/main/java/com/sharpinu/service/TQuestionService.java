package com.sharpinu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharpinu.persist.domain.TQuestion;
import com.sharpinu.persist.repositories.TQuestionRepo;

/**
 *
 * @author vodinh90@gmail.com
 *
 */
@Service("tQuestionService")
public class TQuestionService extends BaseService implements ITQuestionService {
	
	@Autowired
	TQuestionRepo tQuestionRepo;
	
	//@Cacheable(value = {"TQuestionService.findById"}, key = "#questionId")
	public TQuestion findById(Integer questionId) {
		return tQuestionRepo.findOne(questionId);
	}
}
