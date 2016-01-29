package com.sharpinu.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sharpinu.config.ConfigManager;
import com.sharpinu.constant.StayOnTheEdgeConst;
import com.sharpinu.persist.domain.Category;
import com.sharpinu.persist.domain.Post;
import com.sharpinu.persist.repositories.CategoryRepo;
import com.sharpinu.persist.repositories.PostRepo;
import com.sharpinu.util.DateUtil;
import com.sharpinu.util.Lib;
import com.sharpinu.web.bean.CategoryBean;
import com.sharpinu.web.bean.PostBean;
import com.sharpinu.web.bean.StayOnEdgeBean;

@Service
public class StayOnEdgeService extends BaseService implements IStayOnEdgeService {

	@Autowired
	@Qualifier("categoryRepo")
	private CategoryRepo categoryRepo;

	@Autowired
	@Qualifier("postRepo")
	private PostRepo postRepo;

	public StayOnEdgeBean loadDefault() {
		try {
			StayOnEdgeBean stayOnEdgeBean = new StayOnEdgeBean();
			List<CategoryBean> categoryBeans = this.getCategoriesHasPost(new Date(), StayOnTheEdgeConst.DEFAULT_CATEGORY_PAGE_SIZE);
			stayOnEdgeBean.setHasMoreCategory(categoryBeans.size() >= StayOnTheEdgeConst.DEFAULT_CATEGORY_PAGE_SIZE);
			stayOnEdgeBean.setCategoryBeans(categoryBeans);

			if (categoryBeans.size() > 0) {
				Post lastPost = postRepo.findLatestPost();
				stayOnEdgeBean.setPostBean(this.buildPostBean(lastPost, 0));
			}

			return stayOnEdgeBean;
		} catch (Exception e) {
			throw new RuntimeException("Failed to load default stay on the edge page", e);
		}
	}

	public StayOnEdgeBean loadSelectedPostAndPostsByCategory(Integer postId, Integer categoryId, Integer pageIndex) {
		try {
			if (pageIndex == null) {
				pageIndex = 1;
			}
			PageRequest pageRequest = new PageRequest(pageIndex - 1, StayOnTheEdgeConst.DEFAULT_CATEGORY_POST_PAGE_SIZE, Sort.Direction.DESC, "createdDate");
			Category category = categoryRepo.findOne(categoryId);
			Page<Post> page = postRepo.findByCategory(category, pageRequest);
			StayOnEdgeBean stayOnEdgeBean = buildStayOnEdgeBean(postId, page);

			return stayOnEdgeBean;
		} catch (Exception e) {
			throw new RuntimeException(String.format("Failed to load post[postId = %s, categoryId = %s, pageIndex = %s]", postId, categoryId, pageIndex), e);
		}
	}

	public StayOnEdgeBean filterPosts(String term, Integer pageIndex) {
		try {
			if (pageIndex == null) {
				pageIndex = 1;
			}
			PageRequest pageRequest = new PageRequest(pageIndex - 1, StayOnTheEdgeConst.DEFAULT_POST_PAGE_SIZE, Sort.Direction.DESC, "createdDate");
			Page<Post> page = this.findPostsByContent(term, pageRequest);
			StayOnEdgeBean stayOnEdgeBean = this.buildStayOnEdgeBean(page.getContent().get(0).getPostId(), page);
			return stayOnEdgeBean;
		} catch (Exception e) {
			throw new RuntimeException(String.format("Failed to filter post by [term = %s, pageIndex = %s]", term, pageIndex), e);
		}
	}

	public StayOnEdgeBean loadAllPostsAndCategories(Integer pageIndex) {
		try {
			StayOnEdgeBean stayOnEdgeBean = new StayOnEdgeBean();
			PageRequest pageRequest = new PageRequest(pageIndex - 1, StayOnTheEdgeConst.DEFAULT_POST_PAGE_SIZE, Sort.Direction.DESC, "createdDate");
			Page<Post> page = this.findPostsByContent(null, pageRequest);
			List<PostBean> postBeans = this.buildListPostBean(page.getContent(), ConfigManager.getNumOfTopWords());
			stayOnEdgeBean.setPostBeans(postBeans);
			stayOnEdgeBean.setHasMorePost(page.getTotalPages() > 1);

			List<CategoryBean> categoryBeans = this.getCategoriesHasPost(new Date(), StayOnTheEdgeConst.DEFAULT_CATEGORY_PAGE_SIZE);
			stayOnEdgeBean.setHasMoreCategory(categoryBeans.size() >= StayOnTheEdgeConst.DEFAULT_CATEGORY_PAGE_SIZE);
			stayOnEdgeBean.setCategoryBeans(categoryBeans);

			return stayOnEdgeBean;
		} catch (Exception e) {
			throw new RuntimeException(String.format("Failed to load all posts and categories [pageIndex = %s]", pageIndex), e);
		}
	}

	public List<CategoryBean> getCategoriesHasPost(Date offset, int pageSize) {
		List<Category> categories = categoryRepo.findHasPostByOffset(offset, pageSize);
		List<CategoryBean> categoryBeans = new ArrayList<CategoryBean>();

		for (Category category : categories) {
			Integer categoryId = category.getCategoryId();
			List<Post> posts = postRepo.findLatestPostByCategory(categoryId, new Date(), StayOnTheEdgeConst.DEFAULT_CATEGORY_POST_PAGE_SIZE);
			Boolean hasMorePost = postRepo.countByCategory(categoryId) > StayOnTheEdgeConst.DEFAULT_CATEGORY_POST_PAGE_SIZE;

			if (hasMorePost) {
				posts = posts.subList(0, StayOnTheEdgeConst.DEFAULT_CATEGORY_POST_PAGE_SIZE);
			}

			List<PostBean> postBeans = buildListPostBean(posts, 0);
			CategoryBean categoryBean = new CategoryBean(categoryId, category.getName());
			categoryBean.setCreatedDate(category.getCreatedDate());
			categoryBean.setHasMorePost(hasMorePost);
			categoryBean.setPostBeans(postBeans);
			categoryBeans.add(categoryBean);
		}

		return categoryBeans;
	}

	public StayOnEdgeBean searchPostsSortByCreated(String term) {
		try {
			StayOnEdgeBean stayOnEdgeBean = new StayOnEdgeBean();
			Calendar now = Calendar.getInstance();
			now.set(9999, 1, 1);
			List<Post> posts = postRepo.findByContentContainingOrderCreatedDesc(term, now.getTime(), StayOnTheEdgeConst.DEFAULT_POST_PAGE_SIZE);
			if (posts != null && !posts.isEmpty()) {
				List<PostBean> postBeans = this.buildListPostBean(posts, 0);
				stayOnEdgeBean.setPostBeans(postBeans);
				stayOnEdgeBean.setPostBean(postBeans.get(0));
				stayOnEdgeBean.setHasMorePost(posts.size() >= StayOnTheEdgeConst.DEFAULT_POST_PAGE_SIZE);
			}

			return stayOnEdgeBean;
		} catch (Exception e) {
			throw new RuntimeException(String.format("Failed to load posts[term = '%s']", term), e);
		}
	}

	public List<PostBean> searchPostsSortByCreated(String term, Date createdDate) {
		List<Post> posts = postRepo.findByContentContainingOrderCreatedDesc(term, createdDate, StayOnTheEdgeConst.DEFAULT_POST_PAGE_SIZE);
		List<PostBean> postBeans = this.buildListPostBean(posts, 0);

		return postBeans;
	}

	public PostBean getPost(int postId) {
		Post post = postRepo.findOne(postId);

		if (post != null) {
			PostBean postBean = this.buildPostBean(post, 0);

			if (post.getSlug() == null) {
				post.setSlug(Lib.toSlug(post.getSummary()));
				postRepo.save(post);
			}

			return postBean;
		} else {
			return null;
		}
	}

	public PostBean getPostBySlug(String slug) {
		Post post = postRepo.findOneBySlug(slug);

		if (post != null) {
			PostBean postBean = this.buildPostBean(post, 0);
			return postBean;
		} else {
			return null;
		}
	}

	public List<PostBean> getPostsByCategory(int categoryId, int pageIndex) {
		Category category = categoryRepo.findOne(categoryId);

		if (category == null) {
			return null;
		}

		PageRequest pageRequest = new PageRequest(pageIndex - 1, StayOnTheEdgeConst.DEFAULT_CATEGORY_POST_PAGE_SIZE, Sort.Direction.DESC, "createdDate");
		Page<Post> pagePost = postRepo.findByCategory(category, pageRequest);
		List<PostBean> postBeans = this.buildListPostBean(pagePost.getContent(), StayOnTheEdgeConst.DEFAULT_POST_WORDS_LIMIT);

		return postBeans;
	}

	public List<PostBean> getPostsByDate(Date offset, int pageSize) {
		List<Post> posts = postRepo.findPostsByDate(offset, pageSize);
		List<PostBean> postBeans = this.buildListPostBean(posts, StayOnTheEdgeConst.DEFAULT_POST_WORDS_LIMIT);

		return postBeans;
	}

	private Page<Post> findPostsByContent(String term, Pageable pageable) {
		Page<Post> page;
		if (StringUtils.isNotBlank(term)) {
			page = postRepo.findByContentContaining(term, pageable);
		} else {
			page = postRepo.findAll(pageable);
		}

		return page;
	}

	private StayOnEdgeBean buildStayOnEdgeBean(Integer postId, Page<Post> page) {
		StayOnEdgeBean stayOnEdgeBean = new StayOnEdgeBean();
		List<PostBean> postBeans = buildListPostBean(page.getContent(), 0);
		Map<Integer, PostBean> mapPostBeans = new HashMap<Integer, PostBean>();
		buildMapPostBean(postBeans, mapPostBeans);
		PostBean displayPost = findRelatedPostToDisplay(postId, mapPostBeans);

		stayOnEdgeBean.setPostBeans(postBeans);
		stayOnEdgeBean.setPostBean(displayPost);
		stayOnEdgeBean.setHasMorePost(page.getTotalPages() > 1);

		return stayOnEdgeBean;
	}

	private List<PostBean> buildListPostBean(List<Post> posts, int numOfTopWords) {
		List<PostBean> postBeans = new ArrayList<PostBean>();
		for (Post p : posts) {
			PostBean postBean = buildPostBean(p, numOfTopWords);
			postBeans.add(postBean);
		}

		return postBeans;
	}

	private PostBean buildPostBean(Post post, int numOfTopWords) {
		PostBean postBean = new PostBean();
		postBean.setPostId(post.getPostId());
		postBean.setSummary(post.getSummary());
		postBean.setSlug(post.getSlug());
		postBean.setIsPublic(post.getIsPublic() ? 1 : 0);
		postBean.setCreatedDate(post.getCreatedDate());
		postBean.setUpdatedDate(DateUtil.convertDateTimeToString(post.getCreatedDate().getTime()));

		if (numOfTopWords > 0) {
			postBean.setContent(Lib.getStringLimitWords(post.getContent(), numOfTopWords));
		} else {
			postBean.setContent(post.getContent());
		}

		return postBean;
	}

	private PostBean findRelatedPostToDisplay(Integer postId, Map<Integer, PostBean> mapPostBeans) {
		PostBean postBean = null;
		if (postId == null) {
			postBean = findLastestPost(mapPostBeans);
		} else {
			postBean = buildPostBean(postRepo.findOne(postId), 0);
		}
		return postBean;
	}

	private PostBean findLastestPost(Map<Integer, PostBean> mapPostBeans) {
		List<PostBean> postBeans = new LinkedList<PostBean>(mapPostBeans.values());
		if (postBeans != null && !postBeans.isEmpty()) {
			Collections.sort(postBeans, new Comparator<PostBean>() {

				public int compare(PostBean p1, PostBean p2) {
					Date date1 = p1.getCreatedDate();
					Date date2 = p2.getCreatedDate();
					return date2.compareTo(date1);
				}
			});
			return postBeans.get(0);
		}
		return null;
	}

	private void buildMapPostBean(List<PostBean> postBeans, Map<Integer, PostBean> mapPostBeans) {
		for (PostBean pb : postBeans) {
			mapPostBeans.put(pb.getPostId(), pb);
		}
	}
}