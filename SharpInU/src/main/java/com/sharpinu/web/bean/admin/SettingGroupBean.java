package com.sharpinu.web.bean.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sharpinu.persist.domain.Setting;
import com.sharpinu.persist.domain.SettingGroup;

/**
 * 
 * @author Mark-Nguyen
 *
 */
public class SettingGroupBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SettingGroup settingGroup;
	private List<Setting> settings = new ArrayList<Setting>();
	
	public SettingGroupBean(SettingGroup settingGroup) {
		super();
		this.settingGroup = settingGroup;
	}

	public SettingGroupBean(SettingGroup settingGroup, List<Setting> settings) {
		super();
		this.settingGroup = settingGroup;
		this.settings = settings;
	}

	public SettingGroup getSettingGroup() {
		return settingGroup;
	}
	public List<Setting> getSettings() {
		return settings;
	}
	public void setSettingGroup(SettingGroup settingGroup) {
		this.settingGroup = settingGroup;
	}
	public void setSettings(List<Setting> settings) {
		this.settings = settings;
	}
	
	public void addASetting(Setting setting) {
		this.settings.add(setting);
	}

}
