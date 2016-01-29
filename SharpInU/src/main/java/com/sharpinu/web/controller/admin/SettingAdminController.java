package com.sharpinu.web.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sharpinu.constant.WebConstants;
import com.sharpinu.service.admin.ISettingService;
import com.sharpinu.web.bean.admin.SettingGroupBean;
import com.sharpinu.web.controller.BaseController;
/**
 * 
 * @author Mark-Nguyen
 *
 */
@Controller
@RequestMapping(value = "/admin/setting")
public class SettingAdminController extends BaseController {

	@Autowired
	ISettingService settingService;

	@RequestMapping("/global")
	public String showGlobalSetting(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		List<SettingGroupBean> settingGroupBeans = settingService.findGlobalSetting();
		model.addAttribute("settingGroupBeans", settingGroupBeans);
		return WebConstants.Views.GLOBAL_SETTING;
	}
	
	
}
