package com.sharpinu.web.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sharpinu.constant.WebConstants;
import com.sharpinu.persist.domain.Category;
import com.sharpinu.persist.domain.Post;
import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.repositories.PostRepo;
import com.sharpinu.service.admin.ICategoryService;
import com.sharpinu.service.admin.IPostService;
import com.sharpinu.web.bean.PostBean;
import com.sharpinu.web.bean.admin.FormSelectOptionBean;
import com.sharpinu.web.common.util.SecurityUtil;
import com.sharpinu.web.controller.BaseController;
import com.sharpinu.web.form.admin.PostForm;

@Controller
@RequestMapping(value = "/admin/post")
public class PostController extends BaseController {

	@Autowired
	ICategoryService categoryService;

	@Autowired
	IPostService postService;

	@Autowired
	PostRepo postRepo;

	@ModelAttribute
	public PostForm postForm() {
		return new PostForm(); // populates form for the first time if its null
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String viewPostAdd(HttpServletRequest request, HttpServletResponse response, @ModelAttribute PostForm postForm, BindingResult result, Model model) {
		this.setModelAttributesForPostJob(postForm, model);
		return WebConstants.Views.POST_ADD;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String savePostAdd(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes, @Valid @ModelAttribute PostForm postForm, BindingResult result,
			Model model) {
		boolean hasErrors = result.hasErrors();

		if (!hasErrors) {
			User currentUser = SecurityUtil.getCurrentUser();

			if (currentUser != null) {
				postForm.setUserId(currentUser.getUserId());
			}

			Category category = categoryService.findById(postForm.getCategoryId());
			postForm.setCategory(category);
			postService.savePostForm(postForm);
			redirectAttributes.addFlashAttribute("successMsg", "New post has been added.");
			// String idEncrypt =
			// CryptoHelper.encrypt(String.valueOf(postForm.getPostId()));
			return "redirect:" + "/admin/post/" + postForm.getPostId() + "/review.in";
		} else {
			this.setModelAttributesForPostJob(postForm, model);
			return WebConstants.Views.POST_ADD;
		}
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String viewPostEdit(@PathVariable("id") int postId, HttpServletRequest request, HttpServletResponse response, @ModelAttribute PostForm postForm, BindingResult result, Model model) {
		this.setModelAttributesForPostJob(postForm, model);

		Post post = postRepo.findOne(postId);
		postForm = PostForm.fromPost(post);
		postForm.setCategoryId(post.getCategory().getCategoryId());
		model.addAttribute("postForm", postForm);
		model.addAttribute("postId", postId);
		return WebConstants.Views.POST_EDIT;
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String savePostEdit(@PathVariable("id") int postId, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,
			@Valid @ModelAttribute PostForm postForm, BindingResult result, Model model) {
		boolean hasErrors = result.hasErrors();

		if (!hasErrors) {
			Post post = postRepo.findOne(postId);
			Category category = categoryService.findById(postForm.getCategoryId());
			postForm.setPostId(postId);
			postForm.setCategory(category);
			postForm.setUserId(post.getUserId());
			postForm.setCreatedDate(post.getCreatedDate());
			postService.savePostForm(postForm);
			redirectAttributes.addFlashAttribute("successMsg", "New post has been updated.");
			return "redirect:" + "/admin/post/" + String.valueOf(postId) + "/review.in";
		} else {
			this.setModelAttributesForPostJob(postForm, model);
			Post post = postRepo.findOne(postId);
			postForm = PostForm.fromPost(post);
			model.addAttribute("postForm", postForm);
			model.addAttribute("postId", postId);
			return WebConstants.Views.POST_EDIT;
		}
	}

	@RequestMapping(value = "/{id}/review", method = RequestMethod.GET)
	public String reviewPostForm(@PathVariable("id") int postId, HttpServletRequest request, HttpServletResponse response, Model model) {
		Post post = postRepo.findOne(postId);
		model.addAttribute("post", post);
		return WebConstants.Views.POST_REVIEW;
	}

	private void setModelAttributesForPostJob(PostForm postForm, Model model) {
		List<Category> categories = categoryService.findAll();
		Category selectCategory = new Category();
		selectCategory.setCategoryId(0);
		selectCategory.setName("Select");
		categories.add(0, selectCategory);
		model.addAttribute("categories", categories);

		List<FormSelectOptionBean> options = new ArrayList<FormSelectOptionBean>();
		options.add(new FormSelectOptionBean("Public", true));
		options.add(new FormSelectOptionBean("Private", false));
		model.addAttribute("scopes", options);
	}

	@RequestMapping("/{pageIndex}/list")
	public String showPosts(HttpServletRequest request, HttpServletResponse response, @PathVariable("pageIndex") int pageIndex, Model model) throws IOException {

		Page<Post> page = postService.getPostPageInfo(pageIndex - 1);
		List<PostBean> posts = postService.findPostViewBean(pageIndex - 1, page);

		model.addAttribute("posts", posts);
		model.addAttribute("page", page);

		int current = page.getNumber() + 1;
		int begin = 1;
		int end = page.getTotalPages();
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);

		return WebConstants.Views.POST_LIST;
	}

	@RequestMapping("/{id}/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id, Model model) throws IOException {
		postRepo.delete(id);
		return "redirect:" + "/admin/post/1/list.in";
	}
}
