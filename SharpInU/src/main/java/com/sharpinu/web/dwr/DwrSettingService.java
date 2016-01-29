package com.sharpinu.web.dwr;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.sharpinu.persist.domain.Setting;
import com.sharpinu.persist.repositories.SettingRepo;

@RemoteProxy
public class DwrSettingService extends BaseDwrService implements IDwrSettingService {

	@Autowired
	private SettingRepo settingRepo;

	@Override
	@RemoteMethod
	public boolean updateGlobalSetting(Integer settingId, String newValue) {
		if (settingId != null) {
			Setting setting = settingRepo.findOne(settingId);
			if (setting != null) {
				String currentValue = setting.getSettingValue();
				boolean hasChanges = StringUtils.hasText(currentValue) && !currentValue.trim().equals(newValue.trim());
				if (hasChanges) {
					setting.setSettingValue(newValue);
					settingRepo.save(setting);
					return true;
				}
			} else {
				log.error(String.format("Not found setting[settingId=%s]", settingId));
			}
		}
		return false;
	}

}
