package com.sharpinu.persist.repositories;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.SettingGroup;

/**
 *
 * @author Mark
 *
 */
public interface SettingGroupRepo extends BaseRepo<SettingGroup, Integer>, SettingGroupCustomRepo<SettingGroup, Integer> {
}