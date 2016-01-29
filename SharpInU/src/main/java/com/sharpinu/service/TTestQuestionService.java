package com.sharpinu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharpinu.persist.domain.TQuestion;
import com.sharpinu.persist.domain.TTest;
import com.sharpinu.persist.domain.TTestQuestion;
import com.sharpinu.persist.repositories.TTestQuestionRepo;

/**
 *
 * @author vodinh90@gmail.com
 *
 */

@Service("tTestQuestionService")
public class TTestQuestionService extends BaseService implements ITTestQuestionService {

	@Autowired
	private TTestQuestionRepo tTestQuestionRepo;
	
	public TTestQuestion saveTTestQuestion(TQuestion tQuestion, TTest tTest, int sequence) {
		TTestQuestion tTestQuestion = new TTestQuestion(tQuestion, tTest, sequence);
		return tTestQuestionRepo.save(tTestQuestion); 
	}
	
}
