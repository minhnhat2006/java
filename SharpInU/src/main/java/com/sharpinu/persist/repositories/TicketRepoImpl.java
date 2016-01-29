/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.persist.repositories;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.sharpinu.persist.BaseRepoImpl;
import com.sharpinu.persist.domain.Ticket;

/**
 *
 * @author administrator
 */
public class TicketRepoImpl extends BaseRepoImpl implements TicketCustomRepo<Ticket, Integer> {

	@Autowired
	private TicketRepo ticketRepo;

	/**
	 *
	 * @param userId
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Ticket> findAllTicketByUserId(int userId, int offset, int pageSize) {
		try {
			String queryString = "SELECT t FROM Ticket t WHERE t.toUser = :toUser  ORDER BY t.createdDate DESC";
			Query query = this.em.createQuery(queryString).setParameter("toUser", userId);
			if (offset < 0) {
				offset = 0;
			}
			if (pageSize > 0) {
				query.setMaxResults(pageSize);
			}
			query.setFirstResult(offset);
			return query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException(String.format("Failed to find tickets by current user", offset), e);
		}
	}

	public Ticket findByCareerId(int careerId) {
		try {
			String queryString = "SELECT t FROM Ticket t WHERE t.type = 1 AND t.refId = :careerId";
			Query query = this.em.createQuery(queryString).setParameter("careerId", careerId);
			query.setMaxResults(1);

			return (Ticket) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public Ticket findByCompanyId(int companyId) {
		try {
			String queryString = "SELECT t FROM Ticket t WHERE t.type = 2 AND t.refId = :companyId";
			Query query = this.em.createQuery(queryString).setParameter("companyId", companyId);
			query.setMaxResults(1);

			return (Ticket) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public Ticket findByResumeId(int resumeId) {
		try {
			String queryString = "SELECT t FROM Ticket t WHERE t.type = 3 AND t.refId = :resumeId";
			Query query = this.em.createQuery(queryString).setParameter("resumeId", resumeId);
			query.setMaxResults(1);

			return (Ticket) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
