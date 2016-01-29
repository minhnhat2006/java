package com.sharpinu.persist.repositories;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.AdvisorPost;

/**
 *
 * @author Mark
 *
 */
public interface AdvisorPostRepo extends BaseRepo<AdvisorPost, Integer>, AdvisorPostCustomRepo<AdvisorPost, Integer> {

}