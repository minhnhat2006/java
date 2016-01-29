package com.sharpinu.persist.repositories;

import java.util.List;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.City;
import com.sharpinu.persist.domain.Country;

/**
 *
 * @author Mark
 *
 */
public interface CityRepo extends BaseRepo<City, Integer>, CityCustomRepo<City, Integer> {

	List<City> findByCountry(Country country);

}
