package com.sharpinu.persist;

import java.util.Date;

public interface BaseCustomRepo {

	int countInDateRange(Date from, Date to, String entityClass);

}
