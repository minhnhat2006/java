package com.sharpinu.persist.repositories;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.Role;

/**
 *
 * @author Mark
 *
 */
public interface RoleRepo extends BaseRepo<Role, Integer>, RoleCustomRepo<Role, Integer> {

	Role findByRoleName(String roleUser);

}