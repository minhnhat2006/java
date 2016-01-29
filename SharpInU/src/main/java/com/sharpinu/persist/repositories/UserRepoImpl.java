package com.sharpinu.persist.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.sharpinu.persist.BaseRepoImpl;
import com.sharpinu.persist.domain.User;

/**
 * 
 * @author Mark
 *
 */
public class UserRepoImpl extends BaseRepoImpl implements UserCustomRepo {

	@Autowired
	private UserRepo userRepo;

	public int countInDateRange(Date from, Date to) {
		String queryString = "SELECT COUNT(u) FROM User u WHERE u.dateCreated BETWEEN :from AND :to";
		Query query = this.em.createQuery(queryString).setParameter("from", from).setParameter("to", to);

		return ((Long) query.getSingleResult()).intValue();
	}

	public List<User> findInDateRange(Date from, Date to) {
		try {
			List<User> results = new ArrayList<User>();
			if (from == null && to == null) {
				results = userRepo.findAll();
			} else {
				results = userRepo.findByDateCreatedBetween(from, to);
			}
			return results;
		} catch (Exception e) {
			throw new RuntimeException("Failed to find user", e);
		}
	}

}
