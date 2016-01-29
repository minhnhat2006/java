package com.sharpinu.service.admin;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sharpinu.persist.domain.Post;
import com.sharpinu.service.IBaseService;
import com.sharpinu.web.bean.PostBean;
import com.sharpinu.web.form.admin.PostForm;

/**
 * 
 * @author nhatnguyen
 *
 */
public interface IPostService extends IBaseService {

	String savePostForm(PostForm postForm);
	
	/**
	 * Find posts with pageIndex criteria
	 * 
	 * @param pageIndex
	 * @return
	 */
	Page<Post> getPostPageInfo(int pageIndex);

	/**
	 * Find posts with pageIndex criteria
	 * 
	 * @param pageIndex
	 * @param postBeans
	 * @return
	 */
	List<PostBean> findPostViewBean(int pageIndex, Page<Post> posts);
}
