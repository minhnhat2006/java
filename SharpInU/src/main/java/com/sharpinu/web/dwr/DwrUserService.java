package com.sharpinu.web.dwr;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.repositories.UserRepo;
import com.sharpinu.service.IUserService;
import com.sharpinu.web.common.util.SecurityUtil;

@RemoteProxy
public class DwrUserService extends BaseDwrService implements IDwrUserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private IUserService userService;

	@RemoteMethod
	public boolean isEmailExisting(String email) {
		if (StringUtils.hasText(email)) {
			User user = userRepo.findByUserEmail(email);
			return user != null;
		}
		return false;
	}

	@RemoteMethod
	public boolean sendEmailForResetPassword(String email) {
		if (StringUtils.hasText(email)) {
			User user = userRepo.findByUserEmail(email);
			if (user != null) {
				String contextPath = SecurityUtil.getURLWithContextPath(getRequest());
				userService.generatePasswordHash(user, contextPath);
			}
		}
		return false;
	}

}
