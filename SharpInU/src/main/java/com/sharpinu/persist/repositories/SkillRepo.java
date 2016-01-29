package com.sharpinu.persist.repositories;

import java.util.List;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.Skill;
import com.sharpinu.persist.domain.WorkCategory;

/**
 * 
 * @author Mark
 *
 */
public interface SkillRepo extends BaseRepo<Skill, Integer>, SkillCustomRepo<Skill, Integer> {
	
	List<Skill> findByWorkCategory(WorkCategory workCategory);
}
