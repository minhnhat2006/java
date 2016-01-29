package com.sharpinu.persist.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.TUserTest;

/**
 *
 * @author lvhuy08t2@gmail.com
 *
 */
public interface TUserTestRepo  extends BaseRepo<TUserTest, Integer> , TUserTestCustomRepo {
	
	@Query("SELECT ut FROM TUserTest ut JOIN ut.tTest t WHERE ut.user.userId = :userId AND t.testId = :testId")
	public TUserTest findByUserIdAndTestId(@Param("userId") int userId, @Param("testId") int testId);
}
