package com.sharpinu.web.dwr;

import java.util.Date;
import java.util.List;

import com.sharpinu.web.bean.OurPracticeBean;
import com.sharpinu.web.bean.OurPracticeCategoryBean;

public interface IDwrOurPracticeService {

	public List<OurPracticeCategoryBean> getOurPracticeCategories(String offset, int pageSize);

	public List<OurPracticeBean> searchOurPractices(String term, Date createdDate);

	public OurPracticeBean getOurPracticeById(int postId);

	public List<OurPracticeBean> loadMoreOurPractices(int categoryId, int pageIndex);

	public List<OurPracticeBean> loadMoreOurPracticesRecent(long offset);

}
