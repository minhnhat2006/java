package com.sharpinu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharpinu.persist.domain.TOption;
import com.sharpinu.persist.repositories.TOptionRepo;

/**
 *
 * @author vodinh90@gmail.com
 *
 */
@Service("tOptionService")
public class TOptionService extends BaseService implements ITOptionService {
	@Autowired
	TOptionRepo tOptionRepo;
	
	//@Cacheable(value = {"TOptionService.findById"}, key = "#optionId")
	public TOption findById(Integer optionId) {
		return tOptionRepo.findOne(optionId);
	}
	
	public List<Integer> findKeyOptionOfQuestion(Integer questionId) {
		return tOptionRepo.findKeyOptionByQuestionId(questionId);
	}
}
