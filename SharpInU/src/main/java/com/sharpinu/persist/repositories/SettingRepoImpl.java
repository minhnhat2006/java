package com.sharpinu.persist.repositories;

import org.springframework.beans.factory.annotation.Autowired;

import com.sharpinu.persist.BaseRepoImpl;
import com.sharpinu.persist.domain.Setting;

/**
 *
 * @author Mark
 *
 */
public class SettingRepoImpl extends BaseRepoImpl implements SettingCustomRepo<Setting, Integer> {

	@Autowired
	private SettingRepo settingRepo;

}