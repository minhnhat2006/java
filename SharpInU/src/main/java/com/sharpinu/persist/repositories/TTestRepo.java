package com.sharpinu.persist.repositories;

import org.springframework.data.jpa.repository.Query;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.TTest;

/**
*
* @author vodinh90@gmail.com
*
*/
public interface TTestRepo extends BaseRepo<TTest, Integer>, TTestCustomRepo<TTest, Integer> {
	
	@Query("SELECT t FROM TTest t WHERE t.testId = ?")
	public TTest findById(Integer tTestId);
}
