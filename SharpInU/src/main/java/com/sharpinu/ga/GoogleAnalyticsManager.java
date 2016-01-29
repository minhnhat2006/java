package com.sharpinu.ga;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;
import com.google.api.services.analytics.model.Accounts;
import com.google.api.services.analytics.model.GaData;
import com.google.api.services.analytics.model.GaData.ColumnHeaders;
import com.google.api.services.analytics.model.Profiles;
import com.google.api.services.analytics.model.Webproperties;
import com.sharpinu.config.ConfigManager;
import com.sharpinu.constant.ConfigConstants;
import com.sharpinu.util.DateUtil;
import com.sharpinu.util.Lib;

public class GoogleAnalyticsManager {
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private static final String APPLICATION_NAME = "SIU Analytics";
	private static final String KEY_FILE = "google_sa_key.p12";
	private static final String SERVICE_ACCOUNT_EMAIL = ConfigManager.getInstance().getProperty(ConfigConstants.GOOGLE_SERVICE_ACCOUNT_EMAIL);
	private static final String EARLIEST_DATE = "2005-01-01";

	private static Analytics analytics;

	private static String profileId;

	private static GoogleAnalyticsManager instance = new GoogleAnalyticsManager();

	public static GoogleAnalyticsManager getInstance() {
		return instance;
	}

	private GoogleAnalyticsManager() {
		initializeAnalytics();
		String trackingId = ConfigManager.getInstance().getProperty(ConfigConstants.GOOGLE_ANALYTICS_TRACKING_ID);
		profileId = getProfileId(trackingId);
		if (StringUtils.isEmpty(profileId)) {
			profileId = getFirstProfileId();
		}
	}

	private void initializeAnalytics() {
		try {
			// Initializes an authorized analytics service object.

			// Construct a GoogleCredential object with the service account email
			// and p12 file downloaded from the developer console.
			HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			String keyFileLocation = this.getClass().getClassLoader().getResource(KEY_FILE).getPath();
			GoogleCredential credential = new GoogleCredential.Builder().setTransport(httpTransport)
					.setJsonFactory(JSON_FACTORY).setServiceAccountId(SERVICE_ACCOUNT_EMAIL)
					.setServiceAccountPrivateKeyFromP12File(new File(keyFileLocation))
					.setServiceAccountScopes(AnalyticsScopes.all()).build();

			// Construct the Analytics service object.
			analytics = new Analytics.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME)
					.build();
		} catch (Exception e) {
			throw new RuntimeException("Failed to initialize Google Analytics Manager", e);
		}
	}

	private String getFirstProfileId() {
		try {
			// Get the first view (profile) ID for the authorized user.
			String profileId = null;

			// Query for the list of all accounts associated with the service
			// account.
			Accounts accounts = analytics.management().accounts().list().execute();

			if (accounts.getItems().isEmpty()) {
				System.out.println("No accounts found");
			} else {
				String firstAccountId = accounts.getItems().get(0).getId();

				// Query for the list of properties associated with the first
				// account.
				Webproperties properties = analytics.management().webproperties().list(firstAccountId).execute();

				if (properties.getItems().isEmpty()) {
					System.out.println("No Webproperties found");
				} else {
					String firstWebpropertyId = properties.getItems().get(0).getId();

					// Query for the list views (profiles) associated with the
					// property.
					Profiles profiles = analytics.management().profiles().list(firstAccountId, firstWebpropertyId)
							.execute();

					if (profiles.getItems().isEmpty()) {
						System.out.println("No views (profiles) found");
					} else {
						// Return the first (view) profile associated with the
						// property.
						profileId = profiles.getItems().get(0).getId();
					}
				}
			}
			return profileId;
		} catch (Exception e) {
			throw new RuntimeException("Failed to get first profile Id", e);
		}
	}

	/**
	 * Retrieve profile id by user's tracking id.
	 * 
	 * @param trackingId
	 *            : String
	 * @return profile id
	 * @throws IOException
	 */
	private String getProfileId(String trackingId) {
		try {
			// profile id from tracking ID
			String[] s = trackingId.split("-");// tracking ID: UA-XXXXXXXX-YY where
			// XXXXXXXX is account ID
			String myaccountId = s[1];

			String profileId = null;

			// Query accounts collection
			Accounts accounts = analytics.management().accounts().list().execute();

			String accountId = null;
			if (accounts.getItems().isEmpty()) {
				System.out.println("No accounts found");
			} else {
				if (accounts.getItems().get(0).getId().equalsIgnoreCase(myaccountId)) {
					accountId = accounts.getItems().get(0).getId();
					System.out.println("Account id if: " + accountId);
				} else {
					// account ID
					for (int i = 0; i < accounts.getItems().size(); i++) {
						if (accounts.getItems().get(i).getId().equalsIgnoreCase(myaccountId)) {
							accountId = accounts.getItems().get(i).getId();
							System.out.println("Account id for: " + accountId);
						}
					}
				}
			}

			// Web properties
			String webpropertyId = null;
			if (accountId != null) {
				// Query webproperties collection.
				Webproperties webproperties = analytics.management().webproperties().list(accountId).execute();

				if (webproperties.getItems().isEmpty()) {
					System.out.println("No Webproperties found");
				} else {
					if (webproperties.getItems().get(0).getId().equalsIgnoreCase(trackingId)) {
						webpropertyId = webproperties.getItems().get(0).getId();
						System.out.println("Webproperties id if: " + webpropertyId);
					} else {
						// account ID
						for (int i = 0; i < webproperties.getItems().size(); i++) {
							if (webproperties.getItems().get(i).getId().equalsIgnoreCase(trackingId)) {
								webpropertyId = webproperties.getItems().get(i).getId();
								System.out.println("Webproperties id for: " + webpropertyId);
							}
						}
					}
				}

			}

			// ProfileID
			if (webpropertyId != null) {
				// Query profiles collection.
				Profiles profiles = analytics.management().profiles().list(accountId, webpropertyId).execute();

				if (profiles.getItems().isEmpty()) {
					System.out.println("No profiles found");
				} else {
					profileId = profiles.getItems().get(0).getId();
				}
			}
			System.out.println("Profile id: " + profileId);
			return profileId;
		} catch (Exception e) {
			throw new RuntimeException("Failed to get profile Id", e);
		}
	}

	public int getTotalPageViews() throws IOException {
		// Today date
		String dToday = DateUtil.currentDateToString(DateUtil.GOOGLE_DATE_FORMAT);
		String startDate = EARLIEST_DATE;
		return getPageviews(startDate, dToday);
	}

	public int getPageviews(String startDate, String endDate) throws IOException {
		if (analytics != null) {
			GaData dataEvent = analytics.data()
					.ga()
					.get("ga:" + profileId, // Table Id. ga: + profile id.
							startDate, // Start date.
							endDate, // End date.
							"ga:pageviews")
							.setDimensions("ga:customVarValue1").execute();
			return Lib.getIntValue(dataEvent.getTotalsForAllResults().get("ga:pageviews"));
		}
		return 0;
	}

	public List<List<String>> getViewAnalyticsData(String startDate, String endDate) throws IOException {
		List<List<String>> results = new ArrayList<List<String>>();
		if (analytics != null) {
			GaData dataEvent = analytics.data()
					.ga()
					.get("ga:" + profileId, // Table Id. ga: + profile id.
							startDate, // Start date.
							endDate, // End date.
							"ga:pageviews")
							.setSort("-ga:date, -ga:hour, -ga:minute")
							.setDimensions("ga:pageTitle, ga:date, ga:hour, ga:minute, ga:customVarValue1").execute();
			if (dataEvent.getTotalResults() > 0) {
				results = dataEvent.getRows();
			}
		}
		return results;
	}

	public List<List<String>> getAllViewAnalyticsData() throws IOException {
		// Today date
		String dToday = DateUtil.currentDateToString(DateUtil.GOOGLE_DATE_FORMAT);
		String startDate = EARLIEST_DATE;
		return getViewAnalyticsData(startDate, dToday);
	}

	public List<List<String>> getViewDetailsAnalyticsData(String startDate, String endDate, String pageTitle) throws IOException {
		List<List<String>> results = new ArrayList<List<String>>();
		if (analytics != null) {
			GaData dataEvent = analytics.data()
					.ga()
					.get("ga:" + profileId, // Table Id. ga: + profile id.
							startDate, // Start date.
							endDate, // End date.
							"ga:pageviews")
							.setSort("-ga:date, -ga:hour, -ga:minute")
							.setFilters("ga:pageTitle==" + pageTitle)
							.setDimensions("ga:customVarValue1, ga:date, ga:hour, ga:minute").execute();
			if (dataEvent.getTotalResults() > 0) {
				results = dataEvent.getRows();
			}
		}
		return results;
	}

	public List<List<String>> getAllViewDetailsAnalyticsData(String pageTitle) throws IOException {
		// Today date
		String dToday = DateUtil.currentDateToString(DateUtil.GOOGLE_DATE_FORMAT);
		String startDate = EARLIEST_DATE;
		return getViewDetailsAnalyticsData(startDate, dToday, pageTitle);
	}

	public void printGaData(GaData results) {
		System.out.println("printing results for profile: " + results.getProfileInfo().getProfileName());

		if (results.getRows() == null || results.getRows().isEmpty()) {
			System.out.println("No results Found.");
		} else {

			// Print column headers.
			for (ColumnHeaders header : results.getColumnHeaders()) {
				System.out.printf("%30s", header.getName());
			}
			System.out.println();

			// Print actual data.
			for (List<String> row : results.getRows()) {
				for (String column : row) {
					System.out.printf("%30s", column);
				}
				System.out.println();
			}

			System.out.println();
		}
	}
}
