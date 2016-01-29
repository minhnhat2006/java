package com.sharpinu.persist.repositories;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.Role;
import com.sharpinu.persist.domain.Statistic;

/**
 *
 * @author Mark
 *
 */
public interface StatisticRepo extends BaseRepo<Statistic, String>, StatisticCustomRepo<Statistic, String> {

}