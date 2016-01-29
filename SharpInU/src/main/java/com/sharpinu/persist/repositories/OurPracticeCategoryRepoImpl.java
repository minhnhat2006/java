package com.sharpinu.persist.repositories;

import java.util.List;

import javax.persistence.Query;

import com.sharpinu.constant.SQLQueryConst;
import com.sharpinu.persist.BaseRepoImpl;
import com.sharpinu.persist.domain.OurPracticeCategory;

/**
 *
 * @author Mark
 *
 */
public class OurPracticeCategoryRepoImpl extends BaseRepoImpl implements OurPracticeCategoryCustomRepo<OurPracticeCategory, Integer> {

	@SuppressWarnings("unchecked")
	public List<OurPracticeCategory> findHasOurPracticeByOffset(String offset, int pageSize, String order) {
		if (order != SQLQueryConst.ORDER_DESC) {
			order = SQLQueryConst.ORDER_ASC;
		}

		try {
			String queryString = "SELECT distinct(c) FROM " + OurPracticeCategoryCustomRepo.TABLE + " c JOIN c.ourPractices p WHERE c.name > :offset ORDER BY c.name " + order;
			Query query = this.em.createQuery(queryString).setParameter("offset", offset).setMaxResults(pageSize);

			return (List<OurPracticeCategory>) query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException("findHasOurPracticeByOffset: failed to find OurPracticeCategory.", e);
		}
	}
}
