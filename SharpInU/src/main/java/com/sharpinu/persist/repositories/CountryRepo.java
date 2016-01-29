package com.sharpinu.persist.repositories;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.Country;

/**
 *
 * @author Mark
 *
 */
public interface CountryRepo extends BaseRepo<Country, Integer>, CountryCustomRepo<Country, Integer> {

}
