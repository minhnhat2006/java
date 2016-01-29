package com.sharpinu.service;

import java.util.Date;
import java.util.List;

import com.sharpinu.web.bean.OurPracticeBean;
import com.sharpinu.web.bean.OurPracticeCategoryBean;
import com.sharpinu.web.bean.OurPracticePageBean;

public interface IOurPracticeService extends IBaseService {

	OurPracticePageBean loadDefault();

	OurPracticePageBean loadSelectedOurPracticeAndOurPracticesByOurPracticeCategory(Integer postId, Integer ourPracticeCategoryId, Integer pageIndex);

	OurPracticePageBean loadAllOurPracticesAndCategories(Integer pageIndex);

	OurPracticePageBean searchOurPracticesSortByCreated(String term);

	List<OurPracticeCategoryBean> getCategoriesHasOurPractice(String offset, int pageSize);

	List<OurPracticeBean> searchOurPracticesSortByCreated(String term, Date createdDate);

	List<OurPracticeBean> getOurPracticesByOurPracticeCategory(int ourPracticeCategoryId, int pageIndex);

	List<OurPracticeBean> getOurPracticeByDate(Date offset, int pageSize);

	OurPracticeBean getOurPractice(int postId);

	OurPracticeBean getOurPracticeBySlug(String slug);
}
