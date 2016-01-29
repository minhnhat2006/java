package com.sharpinu.persist.repositories;

import java.util.List;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.Setting;

/**
 *
 * @author Mark
 *
 */
public interface SettingRepo extends BaseRepo<Setting, Integer>, SettingCustomRepo<Setting, Integer> {
	
	List<Setting> findBySettingType(int settingType);
}