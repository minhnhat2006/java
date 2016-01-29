package com.sharpinu.service.admin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharpinu.persist.domain.Setting;
import com.sharpinu.persist.domain.SettingGroup;
import com.sharpinu.persist.repositories.SettingGroupRepo;
import com.sharpinu.persist.repositories.SettingRepo;
import com.sharpinu.service.BaseService;
import com.sharpinu.util.Lib;
import com.sharpinu.web.bean.admin.SettingGroupBean;

/**
 * 
 * @author Mark-Nguyen
 *
 */
@Service
public class SettingService extends BaseService implements ISettingService {
	@Autowired
	SettingRepo settingRepo;

	@Autowired
	SettingGroupRepo settingGroupRepo;

	@Override
	public List<SettingGroupBean> findGlobalSetting() {
		Map<Integer, SettingGroupBean> settingGroupBeans = new LinkedHashMap<Integer, SettingGroupBean>();
		List<Setting> globalSettings = settingRepo.findBySettingType(Setting.TYPE.GLOBAL);
		if (Lib.isListNotNullAndEmpty(globalSettings)) {
			for (Setting setting : globalSettings) {
				int settingGroupId = setting.getSettingGroup().getSettingGroupId();
				SettingGroupBean settingGroupBean = settingGroupBeans.get(settingGroupId);
				if (settingGroupBean == null) {
					SettingGroup settingGroup = settingGroupRepo.findOne(settingGroupId);
					settingGroupBean = new SettingGroupBean(settingGroup); 
					settingGroupBeans.put(settingGroupId, settingGroupBean);
				}
				settingGroupBean.addASetting(setting);
			}
		}
		return new ArrayList<SettingGroupBean>(settingGroupBeans.values());
	}
}
