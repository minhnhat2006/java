package com.sharpinu.service;

import java.util.List;

import com.sharpinu.persist.domain.Skill;
import com.sharpinu.persist.domain.TQuestion;
import com.sharpinu.persist.domain.TTest;
import com.sharpinu.persist.domain.User;
import com.sharpinu.web.bean.QuestionBean;
import com.sharpinu.web.bean.SkillBean;

/**
 * 
 * @author Mark
 *
 */
public interface ISkillService extends IBaseService {
	public List<Skill> findAll();

	public Skill findById(Integer skillId);
	
	public List<Skill> findByIds(List<Integer> skillIds);
	
	public List<SkillBean> findByWorkCategoryId(int workCategoryId);
	
	public List<TQuestion> getTestQuestions(int skillId, User currentUser, int numOfQuestion, Integer practiceQuestionId);
	
	public QuestionBean getRandomPracticeQuestion(int skillId, User currentUser);
	
	public QuestionBean  getQuestionBeanByQuestionId(int questionId);

	public TTest saveTestInforOfUser(int skillId, List<TQuestion> tQuestions, User user, List<QuestionBean> questionBeans);
	
	public List<QuestionBean> buildListQuestionBean(List<TQuestion> tQuestions);
}
