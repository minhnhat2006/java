package com.sharpinu.persist.repositories;

import java.util.List;

/**
 *
 * @author Mark
 *
 */
public interface UserRoleCustomRepo<UserRole, Integer> {
	public List<String> findRoleNameByUserId(Integer userId);

	public List<UserRole> findRolesByUserId(Integer userId);
}