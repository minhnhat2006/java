package com.sharpinu.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sharpinu.constant.SettingConstants;
import com.sharpinu.constant.WebConstants;
import com.sharpinu.persist.domain.Setting;
import com.sharpinu.service.admin.ISettingService;
import com.sharpinu.web.bean.admin.SettingGroupBean;

/**
 * This controller is for displaying skills of user
 * 
 * @author Mark
 *
 */
@Controller
public class HomeController extends BaseController {

	@Autowired
	ISettingService settingService;
	
	@RequestMapping("/")
	public String root(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		return home(request, response, model);
	}

	@RequestMapping("/home")
	public String home(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		List<SettingGroupBean> globalSettings = settingService.findGlobalSetting();
		model.addAttribute("globalSettings", globalSettings);
		
		String weeklyTopicUrl = findSettingValueByGroupNameAndSettingName(
				globalSettings, SettingConstants.SettingGroupName.FOOTER_MENU, SettingConstants.SettingName.WEEKLY_HIGHLIGHT_TOPIC_URL);
		String weeklyTopicTitle = findSettingValueByGroupNameAndSettingName(
				globalSettings, SettingConstants.SettingGroupName.FOOTER_MENU, SettingConstants.SettingName.WEEKLY_HIGHLIGHT_TOPIC_TITLE);
		String weeklyTopicImageUrl = findSettingValueByGroupNameAndSettingName(
				globalSettings, SettingConstants.SettingGroupName.FOOTER_MENU, SettingConstants.SettingName.WEEKLY_HIGHLIGHT_TOPIC_IMAGE_URL);
		String testTrendTopicUrl = findSettingValueByGroupNameAndSettingName(
				globalSettings, SettingConstants.SettingGroupName.FOOTER_MENU, SettingConstants.SettingName.TESTING_TREND_TOPIC_URL);
		String testTrendTopicTitle = findSettingValueByGroupNameAndSettingName(
				globalSettings, SettingConstants.SettingGroupName.FOOTER_MENU, SettingConstants.SettingName.TESTING_TREND_TOPIC_TITLE);
		String testTrendTopicImageUrl = findSettingValueByGroupNameAndSettingName(
				globalSettings, SettingConstants.SettingGroupName.FOOTER_MENU, SettingConstants.SettingName.TESTING_TREND_TOPIC_IMAGE_URL);
		
		model.addAttribute("weeklyTopicUrl", weeklyTopicUrl);
		model.addAttribute("weeklyTopicTitle", weeklyTopicTitle);
		model.addAttribute("weeklyTopicImageUrl", weeklyTopicImageUrl);
		model.addAttribute("testTrendTopicUrl", testTrendTopicUrl);
		model.addAttribute("testTrendTopicTitle", testTrendTopicTitle);
		model.addAttribute("testTrendTopicImageUrl", testTrendTopicImageUrl);
		return WebConstants.Views.HOME;
	}
		
	private String findSettingValueByGroupNameAndSettingName(List<SettingGroupBean> settingGroupBeans, String settingGroupName, String settingName) {
		String value = "";
		for (SettingGroupBean settingGroupBean : settingGroupBeans) {
			if (settingGroupBean.getSettingGroup().getSettingGroupName().equalsIgnoreCase(settingGroupName)) {
				List<Setting> settings = settingGroupBean.getSettings();
				for (Setting setting : settings) {
					if (setting.getSettingName().equalsIgnoreCase(settingName)) {
						value = setting.getSettingValue();
						break;
					}
				}
			}
		}
		return value;
	}
	
	

	@RequestMapping("/about_us")
	public String aboutUs(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		return WebConstants.Views.ABOUT_US;
	}

	@RequestMapping("/ask_for_advice")
	public String askForAdvice(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		return WebConstants.Views.ASK_FOR_ADVICE;
	}

	@RequestMapping("/admin")
	public String admin(HttpServletRequest request, HttpServletResponse response, Model model) {
		return WebConstants.Views.ADMIN;
	}

	@RequestMapping("/user")
	public String user(HttpServletRequest request, HttpServletResponse response, Model model) {
		return WebConstants.Views.USER;
	}
}
