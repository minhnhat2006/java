package com.sharpinu.persist.repositories;

import com.sharpinu.persist.BaseRepo;
import com.sharpinu.persist.domain.EmailSystem;

public interface EmailSystemRepo extends BaseRepo<EmailSystem, Integer>, EmailSystemCustomRepo<EmailSystem, Integer> {
	public EmailSystem findByIsDefaultTrue();
}
