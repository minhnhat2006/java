package com.sharpinu.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sharpinu.constant.WebConstants;
import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.repositories.CategoryRepo;
import com.sharpinu.persist.repositories.PostRepo;
import com.sharpinu.service.IStayOnEdgeService;
import com.sharpinu.web.bean.PostBean;
import com.sharpinu.web.bean.StayOnEdgeBean;
import com.sharpinu.web.common.util.SecurityUtil;

@Controller
@RequestMapping("/stay_on_the_edge")
public class StayOnEdgeController extends BaseController {

	@Autowired
	@Qualifier("postRepo")
	PostRepo postRepo;

	@Autowired
	@Qualifier("categoryRepo")
	CategoryRepo categoryRepo;

	@Autowired
	@Qualifier("stayOnEdgeService")
	IStayOnEdgeService stayOnEdgeService;

	@RequestMapping(method = RequestMethod.GET)
	public String showDefaultPage(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		StayOnEdgeBean stayOnEdgeBean = stayOnEdgeService.loadDefault();
		model.addAttribute("stayOnEdgeBean", stayOnEdgeBean);

		if (!stayOnEdgeBean.getCategoryBeans().isEmpty()) {
			model.addAttribute("offset", stayOnEdgeBean.getCategoryBeans().get(stayOnEdgeBean.getCategoryBeans().size() - 1).getCreatedDate().getTime());
		}

		return WebConstants.Views.STAY_ON_THE_EDGE;
	}

	@RequestMapping("/{slug}/view")
	public String showPost(@PathVariable(value = "slug") String slug, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		PostBean post = stayOnEdgeService.getPostBySlug(slug);
		User user = SecurityUtil.getCurrentUser();

		if (post != null) {
			if (post.getIsPublic() == 0) {
				if (user == null) {
					return "redirect:/sec/sign_in.in";
				}

				String advisorToken = this.userPreferences.getAdvisorToken();

				if (!StringUtils.isEmpty(advisorToken)) {
					Object postObj = postRepo.findByAdvisorTokenAndUserId(advisorToken, user.getUserId());

					if (postObj != null) {
						StayOnEdgeBean stayOnEdgeBean = stayOnEdgeService.loadDefault();
						stayOnEdgeBean.setPostBean(post);
						model.addAttribute("stayOnEdgeBean", stayOnEdgeBean);

						return WebConstants.Views.STAY_ON_THE_EDGE;

					} else {
						redirectAttributes.addFlashAttribute("errorMsg", "Your token is invalid or has expired.");
						this.userPreferences.setPostSlugBeingView(slug);

						return "redirect:/advisor/stay_on_the_edge.in";
					}
				} else {
					this.userPreferences.setPostSlugBeingView(slug);

					return "redirect:/advisor/stay_on_the_edge.in";
				}
			} else {
				StayOnEdgeBean stayOnEdgeBean = stayOnEdgeService.loadDefault();
				stayOnEdgeBean.setPostBean(post);
				model.addAttribute("stayOnEdgeBean", stayOnEdgeBean);

				return WebConstants.Views.STAY_ON_THE_EDGE;
			}
		}

		return "redirect:/stay_on_the_edge.in";
	}

	@RequestMapping("/posts/{pageIndex}/list")
	public String showMostRecentPost(@PathVariable(value = "pageIndex") Integer pageIndex, @RequestParam(value = "search", required = false) String search, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		StayOnEdgeBean stayOnEdgeBean = stayOnEdgeService.filterPosts(search, pageIndex);
		model.addAttribute("stayOnEdgeBean", stayOnEdgeBean);
		model.addAttribute("search", search);

		if (!stayOnEdgeBean.getPostBeans().isEmpty()) {
			model.addAttribute("offset", stayOnEdgeBean.getPostBeans().get(stayOnEdgeBean.getPostBeans().size() - 1).getCreatedDate().getTime());
		}

		return WebConstants.Views.STAY_ON_THE_EDGE_POST;
	}

	@RequestMapping("/categories/posts/{pageIndex}/list")
	public String showAllPostCategory(@PathVariable(value = "pageIndex") Integer pageIndex, HttpServletRequest request, HttpServletResponse response, Model model) {
		StayOnEdgeBean stayOnEdgeBean = stayOnEdgeService.loadAllPostsAndCategories(pageIndex);
		model.addAttribute("stayOnEdgeBean", stayOnEdgeBean);

		if (!stayOnEdgeBean.getPostBeans().isEmpty()) {
			model.addAttribute("offset", stayOnEdgeBean.getPostBeans().get(stayOnEdgeBean.getPostBeans().size() - 1).getCreatedDate().getTime());
		}

		return WebConstants.Views.STAY_ON_THE_EDGE_ALL;
	}

	@RequestMapping("/posts/{term}/search")
	public String searchPosts(@PathVariable(value = "term") String term, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (term != null && !term.isEmpty()) {
			StayOnEdgeBean stayOnEdgeBean = stayOnEdgeService.searchPostsSortByCreated(term);
			model.addAttribute("stayOnEdgeBean", stayOnEdgeBean);
			model.addAttribute("term", term);

			if (stayOnEdgeBean.getPostBeans() != null && !stayOnEdgeBean.getPostBeans().isEmpty()) {
				model.addAttribute("offset", stayOnEdgeBean.getPostBeans().get(stayOnEdgeBean.getPostBeans().size() - 1).getCreatedDate().getTime());
			}

			return WebConstants.Views.STAY_ON_THE_EDGE_SEARCH;
		} else {
			return "redirect:/categories/posts/1/list.in";
		}
	}

}
