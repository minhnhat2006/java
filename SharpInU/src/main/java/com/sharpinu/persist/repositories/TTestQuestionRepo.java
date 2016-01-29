package com.sharpinu.persist.repositories;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.TTestQuestion;

/**
 *
 * @author vodinh90@gmail.com
 *
 */
public interface TTestQuestionRepo extends BaseRepo<TTestQuestion, Integer>, TTestQuestionCustomRepo<TTestQuestion, Integer> {

}
