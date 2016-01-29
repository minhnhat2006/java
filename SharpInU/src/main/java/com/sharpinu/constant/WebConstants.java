package com.sharpinu.constant;

import java.util.HashMap;
import java.util.Map;

public interface WebConstants {
	public static final String BUILD_NUMBER = "buildNumber";
	public static final String JSESSIONID = ";jsessionid=";
	public static final String USER_ID = "userId";
	public static final String SUPER_ADMIN = "Super Admin";
	public static final String PAGE_SUFFIX = ".in";
	public static final String LAST_LOGIN = "lastLogin";
	public static final String DATE_FORMAT = "MM/dd/yyyy h:mm a";
	public static final String NOT_AVAILABLE = "N/A";

	public interface SessionParams {
		public static final String LOGINED_USER = "logined.user";
		public static final String LOGINED_AUTHENTICATION = "logined.authentication";
		public static final String LOGINED_USER_ROLES = "logined.user.roles";
		public static final String REQUEST_HISTORIES = "REQUEST_HISTORIES";
	}

	public interface HttpRequestHeaders {
		public static final String CACHE_CONTROL = "Cache-Control";
	}

	public interface FixValue {
		public static final int PASSWORD_HASH_VALID = 0;
		public static final int PASSWORD_HASH_NOT_EXIST = 1;
		public static final int PASSWORD_HASH_EXPIRED = 2;
		public static final int PASSWORD_HASH_INVALID = 3;
		public static final int PASSWORD_USED_ALREADY = 4;
		public static final int PASSWORD_HASH_DAYS_TO_EXPIRE = 1;
		public static final int PASSWORD_HASH_LENGTH = 32;

		public static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";
		public static final String MYSQL_DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
		public static final int PASSWORD_HASH_EXPIRED_IN_HOURS = 24;
		public static final int NO_DAYS_OF_WEEK = 7;

		public static final int REGISTER_REPORT = 1;
		public static final int COMPANY_REPORT = 2;
		public static final int CAREER_REPORT = 3;
		public static final int RESUME_REPORT = 4;
		public static final int CONTACT_US_REPORT = 5;
		public static final int VIEW_REPORT = 6;
		public static final int VIEW_REPORT_DETAILS = 7;

		public static final String REGISTER_REPORT_TITLE = "Register Report";
		public static final String COMPANY_REPORT_TITLE = "Ask for Company Advice Report";
		public static final String CAREER_REPORT_TITLE = "Ask for Career Advice Report";
		public static final String RESUME_REPORT_TITLE = "Ask for Resume Report";
		public static final String CONTACT_US_REPORT_TITLE = "Contact Us Request";
		public static final String VIEW_REPORT_TITLE = "View Report";

		public static final String ALL_STRING = "all";
	}

	public interface Params {
		public static final String TIME_ZONE = "timeZone";
	}

	public static final Map<String, String> TIME_ZONES = new HashMap<String, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("-12:00,0", "International Date Line Wes");
			put("-11:00,0", "Midway Island");
			put("-10:00,0", "Hawaii");
			put("-09:00,1", "Alaska");
			put("-08:00,1", "Pacific Time");
			put("-07:00,0", "Arizona");
			put("-07:00,1", "Mountain Time");
			put("-06:00,0", "Central America");
			put("-06:00,1", "Central Time");
			put("-05:00,0", "Indiana");
			put("-05:00,1", "Eastern");
			put("-04:00,1", "Atlantic");
			put("-04:00,0", "Caracas");
			put("-03:30,1", "Newfoundland");
			put("-03:00,1", "Greenland");
			put("-03:00,0", "Buenos Aires");
			put("-02:00,1", "Mid-Atlantic");
			put("-01:00,1", "Azores");
			put("-01:00,0", "Cape Verde Is.");
			put("00:00,1", "GMT: Dublin");
			put("+01:00,1", "Amsterdam");
			put("+01:00,0", "West Central Africa");
			put("+02:00,1", "Amman");
			put("+02:00,0", "Harare");
			put("+03:00,1", "Baghdad");
			put("+03:00,0", "Kuwait");
			put("+03:30,0", "Tehran");
			put("+04:00,0", "Abu Dhadi");
			put("+04:00,1", "Baku");
			put("+04:30,0", "Kabul");
			put("+05:00,1", "Ekaterinburg");
			put("+05:00,0", "Islamabad");
			put("+05:30,0", "Chennai");
			put("+05:45,0", "Kathmandu");
			put("+06:00,0", "Astana");
			put("+06:00,1", "Almaty");
			put("+06:30,0", "Rangoon");
			put("+07:00,1", "Krasnoyarsk");
			put("+07:00,0", "Bangkok");
			put("+08:00,0", "Beijing");
			put("+08:00,1", "Irkutsk");
			put("+09:00,1", "Yakutsk");
			put("+09:00,0", "Seoul");
			put("+09:30,0", "Darwin");
			put("+09:30,1", "Adelaide");
			put("+10:00,0", "Brisbane");
			put("+10:00,1", "Canberra");
			put("+11:00,0", "Magadan");
			put("+12:00,1", "Auckland");
			put("+12:00,0", "Fiji");
			put("+13:00,0", "Nuku'alofa");
		}
	};

	public interface Views {
		public static final String PAGE_NOT_FOUND = "contest404";
		public static final String PAGE_UNDER_CONSTRUCTION = "page_under_construction";

		public static final String SIGN_IN = "security/sign_in";
		public static final String SIGN_UP = "security/sign_up";
		public static final String SIGN_UP_SUCCESS = "security/sign_up_success";
		public static final String SESSION_EXPIRED = "security/sessionExpired";
		public static final String FORGOT_PASSWORD = "security/forgot_password";
		public static final String PASSWORD_IS_SENT = "security/password_is_sent";
		public static final String NEW_PASSWORD = "security/new_password";

		public static final String POST_JOB = "post_job";
		public static final String POST_JOB_PREVIEW = "post_job_preview";
		public static final String POST_JOB_DONE = "post_job_done";
		public static final String JOBS_LIST = "jobs_list";
		public static final String JOB_DETAIL = "job_detail";
		public static final String SKILL_SET = "skill_set";

		public static final String USER_PROFILE_OVERVIEW = "user_profile_overview";
		public static final String USER_JOBS_HISTORY = "user_jobs_history";
		public static final String USER_SKILLS = "user_skills";

		public static final String SKILL_TEST = "skill_test";
		public static final String SKILL_TEST_PRACTICE = "skill_test_practice";
		public static final String SKILL_TEST_PRACTICE_COMPLETE = "skill_test_practice_complete";
		public static final String SKILL_TEST_PRACTICE_OUT_OF_TIME = "skill_test_practice_out_of_time";

		public static final String SKILL_TEST_QUESTION = "skill_test_question";
		public static final String SKILL_TEST_QUESTION_COMPLETE = "skill_test_question_complete";
		public static final String SKILL_TEST_QUESTION_TIMEOUT = "skill_test_question_timeout";

		public static final String RESET_PASSWORD = "security/resetPassword";
		public static final String ACCESS_DENIED = "security/AccessDenied";

		public static final String HOME = "home";
		public static final String ABOUT_US = "about_us";
		public static final String ASK_FOR_ADVICE = "ask_for_advice";
		public static final String CONTACT_US = "contact_us";
		public static final String ERRORS = "errors";
		public static final String CAREER_FORM = "career_form";
		public static final String COMPANY_FORM = "company_form";
		public static final String CONTEST404 = "contest404";
		public static final String RESUME_FORM = "resume_form";
		public static final String RESUME_FORM_REVIEW = "resume_form_review";
		public static final String CAREER_FORM_REVIEW = "career_form_review";
		public static final String COMPANY_FORM_REVIEW = "company_form_review";
		public static final String CONTACT_US_REVIEW = "contact_us_review";
		public static final String NEWS = "news";
		public static final String USER_TICKET = "user_tickets";
		public static final String SINGLE_TICKET = "single_ticket";

		public static final String ADMIN = "admin";
		public static final String USER = "user";
		public static final String CATAGORY_ADD = "admin/category/add";
		public static final String CATAGORY_EDIT = "admin/category/edit";
		public static final String CATAGORY_REVIEW = "admin/category/review";
		public static final String CATEGORY_LIST = "admin/category/list";

		public static final String OUR_PRACTICE_CATAGORY_ADD = "admin/our_practice_category/add";
		public static final String OUR_PRACTICE_CATAGORY_EDIT = "admin/our_practice_category/edit";
		public static final String OUR_PRACTICE_CATAGORY_REVIEW = "admin/our_practice_category/review";
		public static final String OUR_PRACTICE_CATEGORY_LIST = "admin/our_practice_category/list";

		public static final String RESUME_LIST = "admin/resume/list";
		public static final String RESUME_EDIT = "admin/resume/edit";
		public static final String RESUME_VIEW = "admin/resume/view";

		public static final String POST_ADD = "admin/post/add";
		public static final String POST_EDIT = "admin/post/edit";
		public static final String POST_REVIEW = "admin/post/review";
		public static final String POST_LIST = "admin/post/list";

		public static final String OUR_PRACTICE_ADD = "admin/our_practice/add";
		public static final String OUR_PRACTICE_EDIT = "admin/our_practice/edit";
		public static final String OUR_PRACTICE_REVIEW = "admin/our_practice/review";
		public static final String OUR_PRACTICE_LIST = "admin/our_practice/list";

		public static final String TREND_ADD = "admin/trend/add";
		public static final String TREND_EDIT = "admin/trend/edit";
		public static final String TREND_REVIEW = "admin/trend/review";
		public static final String TREND_LIST = "admin/trend/list";

		public static final String USER_MANAGER_REVIEW = "admin/user_manager/review";
		public static final String USER_MANAGER_LIST = "admin/user_manager/list";
		
		public static final String GLOBAL_SETTING = "admin/setting/global";

		public static final String STAY_ON_THE_EDGE = "stay_on_the_edge/default";
		public static final String STAY_ON_THE_EDGE_POST = "stay_on_the_edge/post";
		public static final String STAY_ON_THE_EDGE_ALL = "stay_on_the_edge/all";
		public static final String STAY_ON_THE_EDGE_SEARCH = "stay_on_the_edge/search";

		public static final String OUR_PRACTICE = "our_practice/default";
		public static final String OUR_PRACTICE_SEARCH = "our_practice/search";
		public static final String OUR_PRACTICE_VIEW = "our_practice/view";

		public static final String TREND = "trend/default";
		public static final String TREND_SEARCH = "trend/search";

		public static final String ADVISOR_STAY_ON_THE_EDGE = "advisor/stay_on_the_edge";

		public static final String ADMIN_ADVISOR_IMPORT = "admin/advisor/import";

		public static final String COMPANY_LIST = "admin/company/list";
		public static final String COMPANY_EDIT = "admin/company/edit";
		public static final String COMPANY_VIEW = "admin/company/view";

		public static final String CONTACT_US_LIST = "admin/contact_us/list";
		public static final String CONTACT_US_EDIT = "admin/contact_us/edit";
		public static final String CONTACT_US_VIEW = "admin/contact_us/view";

		public static final String CAREER_LIST = "admin/career/list";
		public static final String CAREER_EDIT = "admin/career/edit";
		public static final String CAREER_VIEW = "admin/career/view";

		public static final String ADMIN_DASHBOARD_VIEW = "admin/dashboard/index";

		public static final String NEWS_ADD = "admin/news/add";
		public static final String NEWS_REVIEW = "admin/news/review";
		public static final String NEWS_LIST = "admin/news/list";
		public static final String NEWS_EDIT = "admin/news/edit";
		public static final String NEWS_DELETE = "admin/news/delete";

		public static final String OVERVIEW_REPORT = "admin/report/overview";
		public static final String DETAILS_REPORT = "admin/report/details";

		public static final String TICKET_ADD = "admin/ticket/add";
		public static final String TICKET_EDIT = "admin/ticket/edit";
		public static final String TICKET_REVIEW = "admin/ticket/review";
		public static final String TICKET_LIST = "admin/ticket/list";

	}

	public interface Pages {
		public static final String JOBS_LIST = "jobs/%s/list." + PAGE_SUFFIX;
		public static final String USER_PROFILE_OVERVIEW = "admin/dashboard" + PAGE_SUFFIX;
	}

}