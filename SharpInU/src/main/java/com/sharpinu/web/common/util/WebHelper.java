package com.sharpinu.web.common.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharpinu.persist.domain.City;
import com.sharpinu.persist.domain.Country;
import com.sharpinu.persist.domain.Job;
import com.sharpinu.persist.repositories.CityRepo;
import com.sharpinu.persist.repositories.CountryRepo;
import com.sharpinu.persist.repositories.SkillRepo;

/**
 *
 * @author Mark
 *
 */
@Service
public class WebHelper {
	@Autowired
	private CountryRepo countryRepo;
	
	@Autowired
	private CityRepo cityRepo;
	
	@Autowired
	private SkillRepo skillRepo;
	
	public String buildocationText(int countryId, int cityId) {
		String location = "";
		
		if (countryId > 0) {
			Country country = countryRepo.findOne(countryId);
			location = country.getCountryName();
		}
		
		if (cityId > 0) {
			City city = cityRepo.findOne(cityId);
			location = location + "/" + city.getCityName();
		}
		
		if (StringUtils.isEmpty(location)) {
			location = "Anywhere";
		}
		return location;
	}
	
	public String buildSalaryTypeText(String salaryType) {
		return Job.SALARY_TYPE_TEXT_MAPPING.get(salaryType);
	}
	
	public String buildSalaryText(long salaryFromAmount, long salaryToAmount) {
		if (salaryFromAmount == 0 && salaryToAmount == 0) {
			return "Not sure";
		}
		
		if (salaryFromAmount == salaryToAmount) {
			return salaryFromAmount + "$";
		}
		
		if (salaryFromAmount == 0) {
			return "Less than " + salaryFromAmount + "$";
		}
		
		return salaryFromAmount + "$ - " + salaryToAmount + "$";
	}
	
}
