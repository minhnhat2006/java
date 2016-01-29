package com.sharpinu.persist.repositories;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.Job;

/**
 *
 * @author Mark
 *
 */
public interface JobRepo extends BaseRepo<Job, Integer>, JobCustomRepo<Job, Integer> {

}
