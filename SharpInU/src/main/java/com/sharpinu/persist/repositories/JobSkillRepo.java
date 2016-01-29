package com.sharpinu.persist.repositories;

import java.util.List;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.Job;
import com.sharpinu.persist.domain.JobSkill;

/**
 *
 * @author Mark
 *
 */
public interface JobSkillRepo extends BaseRepo<JobSkill, Integer>, JobSkillCustomRepo<JobSkill, Integer> {
	public List<JobSkill> findByJob(Job job);
}
