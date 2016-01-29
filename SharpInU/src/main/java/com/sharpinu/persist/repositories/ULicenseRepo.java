package com.sharpinu.persist.repositories;

import java.util.List;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.ULicense;
import com.sharpinu.persist.domain.User;

public interface ULicenseRepo extends BaseRepo<ULicense, Integer>, ULicenseCustomRepo<ULicense, Integer> {

	public List<ULicense> findByUser(User user);
}
