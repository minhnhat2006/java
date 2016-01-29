package com.sharpinu.service.admin;

import java.util.List;

import com.sharpinu.service.IBaseService;
import com.sharpinu.web.bean.admin.SettingGroupBean;

/**
 * 
 * @author Mark-Nguyen
 *
 */
public interface ISettingService extends IBaseService {

	/**
	 * 1. Find all global settings and their group from db
	 * 2. Build beans and add them to model
	 */
	List<SettingGroupBean> findGlobalSetting();
}
