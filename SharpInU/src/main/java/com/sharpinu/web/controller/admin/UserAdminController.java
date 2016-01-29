package com.sharpinu.web.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sharpinu.constant.WebConstants;
import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.repositories.UserRepo;
import com.sharpinu.persist.repositories.UserRoleRepo;
import com.sharpinu.service.admin.IUserAdminService;
import com.sharpinu.util.CryptoHelper;
import com.sharpinu.web.controller.BaseController;

@Controller
@RequestMapping(value = "/admin/user")
public class UserAdminController extends BaseController {

	@Autowired
	IUserAdminService userAdminService;

	@Autowired
	UserRepo userRepo;

	@Autowired
	UserRoleRepo userRoleRepo;

	@RequestMapping("/{pageIndex}/list")
	public String showUsers(HttpServletRequest request, HttpServletResponse response, @PathVariable("pageIndex") int pageIndex, Model model) throws IOException {

		Page<User> page = userAdminService.getUserPageInfo(pageIndex - 1);
		List<User> users = userAdminService.findUserViewBean(pageIndex - 1, page);

		model.addAttribute("users", users);
		model.addAttribute("page", page);

		int current = page.getNumber() + 1;
		int begin = 1;
		int end = page.getTotalPages();
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);

		return WebConstants.Views.USER_MANAGER_LIST;
	}

	@RequestMapping("/{id}/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id, Model model) throws IOException {
		User user = userRepo.findOne(id);

		if (user != null) {
			userRoleRepo.deleteInBatch(userRoleRepo.findRolesByUserId(id));
			userRepo.delete(user);
		}

		return "redirect:" + "/admin/user/1/list.in";
	}

	@RequestMapping(value = "/{id}/review", method = RequestMethod.GET)
	public String viewUser(@PathVariable("id") int userId, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = userRepo.findOne(userId);
		String idEncrypt = CryptoHelper.encrypt(String.valueOf(user.getUserId()));
		model.addAttribute("idEncrypt", idEncrypt);
		model.addAttribute("user", user);
		return WebConstants.Views.USER_MANAGER_REVIEW;
	}
}
