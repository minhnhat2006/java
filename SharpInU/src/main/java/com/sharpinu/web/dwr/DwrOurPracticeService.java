package com.sharpinu.web.dwr;

import java.util.Date;
import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;

import com.sharpinu.constant.OurPracticeConst;
import com.sharpinu.service.IOurPracticeService;
import com.sharpinu.web.bean.OurPracticeBean;
import com.sharpinu.web.bean.OurPracticeCategoryBean;

@RemoteProxy
public class DwrOurPracticeService implements IDwrOurPracticeService {

	@Autowired
	IOurPracticeService ourPracticeService;

	@RemoteMethod
	public List<OurPracticeCategoryBean> getOurPracticeCategories(String offset, int pageSize) {
		return ourPracticeService.getCategoriesHasOurPractice(offset, pageSize);
	}

	@RemoteMethod
	public List<OurPracticeBean> searchOurPractices(String term, Date createdDate) {
		return ourPracticeService.searchOurPracticesSortByCreated(term, createdDate);
	}

	@RemoteMethod
	public OurPracticeBean getOurPracticeById(int postId) {
		return ourPracticeService.getOurPractice(postId);
	}

	@RemoteMethod
	public List<OurPracticeBean> loadMoreOurPractices(int categoryId, int pageIndex) {
		return ourPracticeService.getOurPracticesByOurPracticeCategory(categoryId, pageIndex);
	}

	@RemoteMethod
	public List<OurPracticeBean> loadMoreOurPracticesRecent(long offset) {
		Date date = new Date(offset);
		return ourPracticeService.getOurPracticeByDate(date, OurPracticeConst.DEFAULT_POST_PAGE_SIZE);
	}
}
