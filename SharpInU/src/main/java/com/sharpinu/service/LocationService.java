package com.sharpinu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharpinu.persist.domain.City;
import com.sharpinu.persist.domain.Country;
import com.sharpinu.persist.repositories.CityRepo;
import com.sharpinu.web.bean.CityBean;

/**
 *
 * @author Mark
 *
 */
@Service
public class LocationService implements ILocationService {
	@Autowired
	private CityRepo cityRepo;
	
	public List<CityBean> findByCountryId(int countryId) {
		List<City> cities = cityRepo.findByCountry(new Country(countryId));
		List<CityBean> cityBeans = new ArrayList<CityBean>();
		for (City city : cities) {
			cityBeans.add(new CityBean(city.getCityId(), city.getCityName(), city.getCountry().getCountryId()));
		}
		return cityBeans;
	}
	
	
	public Country findCountryById(int countryId) {
		// TODO Auto-generated method stub
		return null;
	}

	public City findCityById(int cityId) {
		// TODO Auto-generated method stub
		return null;
	}

}
