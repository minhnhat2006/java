package com.sharpinu.persist.repositories;

import java.util.List;

import javax.persistence.Query;

import com.sharpinu.persist.BaseRepoImpl;
import com.sharpinu.persist.domain.UserRole;

/**
 *
 * @author Mark
 *
 */
public class UserRoleRepoImpl extends BaseRepoImpl implements UserRoleCustomRepo<UserRole, Integer> {

	@SuppressWarnings("unchecked")
	public List<String> findRoleNameByUserId(Integer userId) {
		try {
			String queryString = "SELECT r.roleName FROM Role r JOIN r.userRoles ur WHERE ur.user.userId = :userId";
			Query query = this.em.createQuery(queryString).setParameter("userId", userId);
			return query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException(String.format("Failed to find Role by [userId=%s]", userId), e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<UserRole> findRolesByUserId(Integer userId) {
		try {
			String queryString = "SELECT ur FROM UserRole ur WHERE ur.user.userId = :userId";
			Query query = this.em.createQuery(queryString).setParameter("userId", userId);
			return query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException(String.format("Failed to find Role by [userId=%s]", userId), e);
		}
	}
}