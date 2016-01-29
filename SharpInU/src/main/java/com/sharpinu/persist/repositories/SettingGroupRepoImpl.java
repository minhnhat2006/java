package com.sharpinu.persist.repositories;

import org.springframework.beans.factory.annotation.Autowired;

import com.sharpinu.persist.BaseRepoImpl;
import com.sharpinu.persist.domain.SettingGroup;

/**
 *
 * @author Mark
 *
 */
public class SettingGroupRepoImpl extends BaseRepoImpl implements SettingGroupCustomRepo<SettingGroup, Integer> {

	@Autowired
	private SettingGroupRepo settingGroupRepo;

}