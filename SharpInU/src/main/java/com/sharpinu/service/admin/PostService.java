package com.sharpinu.service.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.sharpinu.persist.domain.Category;
import com.sharpinu.persist.domain.Post;
import com.sharpinu.persist.domain.User;
import com.sharpinu.persist.repositories.CategoryRepo;
import com.sharpinu.persist.repositories.PostRepo;
import com.sharpinu.persist.repositories.UserRepo;
import com.sharpinu.service.BaseService;
import com.sharpinu.service.IRepositoryService;
import com.sharpinu.util.Lib;
import com.sharpinu.web.bean.PostBean;
import com.sharpinu.web.form.admin.PostForm;

/**
 * 
 * @author Mark
 *
 */
@Service
public class PostService extends BaseService implements IPostService {
	final int PAGE_SIZE = 10;
	@Autowired
	PostRepo postRepo;

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	IRepositoryService repositoryService;

	public String savePostForm(PostForm postForm) {
		try {
			Post post = new Post(postForm);
			post.setSlug(Lib.toSlug(post.getSummary()));
			postRepo.save(post);
			postForm.setPostId(post.getPostId());
		} catch (Exception e) {
			throw new RuntimeException("Failed to save Post Form", e);
		}

		return null;
	}

	public Page<Post> getPostPageInfo(int pageIndex) {
		Sort sort = new Sort(new Order(Direction.ASC, "summary"));
		Pageable pageable = new PageRequest(pageIndex, PAGE_SIZE, sort);
		Page<Post> page = postRepo.findAll(pageable);
		return page;
	}

	public List<PostBean> findPostViewBean(int pageIndex, Page<Post> posts) {
		try {
			List<PostBean> postBeans = new ArrayList<PostBean>();

			for (Post post : posts) {
				Category cate = categoryRepo.findOne(post.getCategory().getCategoryId());
				User user = userRepo.findOne(post.getUserId());
				PostBean postBean = new PostBean(post.getPostId(), "", post.getContent(), post.getSummary(), post.getIsPublic() ? 1 : 0);

				if (cate != null) {
					postBean.setCategoryId(cate.getCategoryId());
					postBean.setCategoryName(cate.getName());
				}

				if (user != null) {
					postBean.setUserId(user.getUserId());
					postBean.setUserEmail(user.getUserEmail());
				}

				postBeans.add(postBean);
			}

			return postBeans;
		} catch (Exception e) {
			log.error("Failed to findPostViewBean", e);
			throw new RuntimeException("Failed to findPostViewBean", e);
		}
	}
}
