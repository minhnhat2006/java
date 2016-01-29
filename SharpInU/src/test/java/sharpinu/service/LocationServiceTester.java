package sharpinu.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sharpinu.service.ILocationService;

import sharpinu.BaseTester;

/**
 *
 * @author Mark
 *
 */
public class LocationServiceTester extends BaseTester {
	@Autowired
	ILocationService locationService;
	
	@Test
	public void testFindCitiesByCountryId () {
		for (int i = 0; i < 10; i++) {
			System.out.println(locationService.findByCountryId(11));	
		}
		
	}
}
