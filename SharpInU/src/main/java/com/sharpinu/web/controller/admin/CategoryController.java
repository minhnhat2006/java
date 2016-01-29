package com.sharpinu.web.controller.admin;

import java.io.IOException;
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
import com.sharpinu.persist.repositories.CategoryRepo;
import com.sharpinu.service.admin.ICategoryService;
import com.sharpinu.web.controller.BaseController;
import com.sharpinu.web.form.admin.CategoryForm;

@Controller
@RequestMapping(value = "/admin/category")
public class CategoryController extends BaseController {

	@Autowired
	ICategoryService categoryService;

	@Autowired
	CategoryRepo categoryRepo;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String viewCategoryAdd(HttpServletRequest request, HttpServletResponse response, @ModelAttribute CategoryForm categoryForm, BindingResult result, Model model) {
		return WebConstants.Views.CATAGORY_ADD;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveCategoryAdd(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes, @Valid @ModelAttribute CategoryForm categoryForm,
			BindingResult result, Model model) {
		boolean hasErrors = result.hasErrors();

		if (!hasErrors) {
			categoryService.saveCategoryForm(categoryForm);
			redirectAttributes.addFlashAttribute("successMsg", "New category has been added.");
			return "redirect:" + "/admin/category/" + categoryForm.getCategoryId() + "/review.in";
		} else {
			return WebConstants.Views.CATAGORY_ADD;
		}
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String viewCategoryEdit(@PathVariable("id") int categoryId, HttpServletRequest request, HttpServletResponse response, @ModelAttribute CategoryForm categoryForm, BindingResult result,
			Model model) {
		Category category = categoryRepo.findOne(categoryId);
		categoryForm = CategoryForm.fromCategory(category);
		model.addAttribute("categoryForm", categoryForm);
		model.addAttribute("categoryId", categoryId);
		return WebConstants.Views.CATAGORY_EDIT;
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String saveCategoryEdit(@PathVariable("id") int categoryId, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,
			@Valid @ModelAttribute CategoryForm categoryForm, BindingResult result, Model model) {
		boolean hasErrors = result.hasErrors();

		if (!hasErrors) {
			Category category = categoryRepo.findOne(categoryId);
			categoryForm.setCategoryId(categoryId);
			categoryForm.setCreatedDate(category.getCreatedDate());
			categoryService.saveCategoryForm(categoryForm);
			redirectAttributes.addFlashAttribute("successMsg", "New category has been updated.");
			return "redirect:" + "/admin/category/" + String.valueOf(categoryId) + "/review.in";
		} else {
			return WebConstants.Views.CATAGORY_EDIT;
		}
	}

	@RequestMapping(value = "/{id}/review", method = RequestMethod.GET)
	public String reviewCategoryForm(@PathVariable("id") int categoryId, HttpServletRequest request, HttpServletResponse response, Model model) {
		Category category = categoryRepo.findOne(categoryId);
		model.addAttribute("category", category);
		return WebConstants.Views.CATAGORY_REVIEW;
	}

	@ModelAttribute
	public CategoryForm categoryForm() {
		return new CategoryForm();
	}

	@RequestMapping("/{pageIndex}/list")
	public String showCategories(HttpServletRequest request, HttpServletResponse response, @PathVariable("pageIndex") int pageIndex, Model model) throws IOException {

		Page<Category> page = categoryService.getCategoryPageInfo(pageIndex - 1);
		List<Category> categories = categoryService.findCategoryViewBean(pageIndex - 1, page);

		model.addAttribute("categories", categories);
		model.addAttribute("page", page);

		int current = page.getNumber() + 1;
		int begin = 1;
		int end = page.getTotalPages();
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);

		return WebConstants.Views.CATEGORY_LIST;
	}

	@RequestMapping("/{id}/delete")
	public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id, Model model) throws IOException {
		categoryRepo.delete(id);
		return "redirect:" + "/admin/category/1/list.in";
	}

}
