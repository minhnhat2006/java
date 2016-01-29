/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sharpinu.persist.repositories;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.Ticket;

/**
 *
 * @author khanh nguyen
 */
public interface TicketRepo extends BaseRepo<Ticket, Integer>, TicketCustomRepo<Ticket, String> {

}
