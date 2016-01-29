package com.sharpinu.persist.repositories;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.UserRole;

/**
 *
 * @author Mark
 *
 */
public interface UserRoleRepo extends BaseRepo<UserRole, Integer>, UserRoleCustomRepo<UserRole, Integer> {

}