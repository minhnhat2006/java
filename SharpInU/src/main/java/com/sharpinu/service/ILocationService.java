package com.sharpinu.service;

import java.util.List;

import com.sharpinu.persist.domain.City;
import com.sharpinu.persist.domain.Country;
import com.sharpinu.web.bean.CityBean;

/**
 *
 * @author Mark
 *
 */
public interface ILocationService extends IBaseService {
	public List<CityBean> findByCountryId(int countryId);

	public Country findCountryById(int countryId);

	public City findCityById(int cityId);
}
