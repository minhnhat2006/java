package com.sharpinu.persist.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
@Table(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	public interface PURPOSE {
		public String WORK = "WORK";
		public String HIRE = "HIRE";
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	private int userId;

	@Column(name="ACCOUNT_LOCKED")
	private boolean accountLocked;

	private boolean active;

	@Column(name="API_LOGIN")
	private boolean apiLogin;

	@Column(name="DATE_CREATED")
	private Timestamp dateCreated;

	@Column(name="DATE_UPDATED")
	private Timestamp dateUpdated;

	@Column(name="FAILED_LOGIN_ATTEMPTS")
	private int failedLoginAttempts;

	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name="LAST_FAILED_LOGIN_DATE")
	private Timestamp lastFailedLoginDate;

	@Column(name="LAST_LOGIN_DATE")
	private Timestamp lastLoginDate;

	@Column(name="LAST_NAME")
	private String lastName;

	@Column(name="PASSWORD_EXPIRED")
	private boolean passwordExpired;

	@Column(name="PASSWORD_HASH")
	private String passwordHash;

	@Column(name="PASSWORD_HASH_DATE")
	private Timestamp passwordHashDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PASSWORD_LAST_CHANGED_DATE")
	private Date passwordLastChangedDate;

	private String purpose;

	@Column(name="USER_EMAIL")
	private String userEmail;

	@Column(name="USER_NAME")
	private String userName;

	@Column(name="USER_PASSWORD")
	private String userPassword;

	//bi-directional many-to-one association to Job
	@OneToMany(mappedBy="createUser")
	private List<Job> jobs;

	//bi-directional many-to-one association to JobApply
	@OneToMany(mappedBy="appyUser")
	private List<JobApply> jobApplies;

	//bi-directional many-to-one association to TQuestion
	@OneToMany(mappedBy="user")
	private List<TQuestion> tQuestions;

	//bi-directional many-to-one association to TUserTest
	@OneToMany(mappedBy="user")
	private List<TUserTest> tUserTests;

	//bi-directional one-to-many association to UCertification
	@OneToMany(mappedBy = "user")
	private List<UCertification> uCertifications;
	
	//bi-directional one-to-many association to UEducation
	@OneToMany(mappedBy = "user")
	private List<UEducation> uEducations;
	
	//bi-directional one-to-many association to UEmployment
	@OneToMany(mappedBy = "user")
	private List<UEmployment> uEmployments;

	//bi-directional one-to-many association to ULicense
	@OneToMany(mappedBy = "user")
	private List<ULicense> uLicenses;
	
	//bi-directional one-to-many association to Profile
	@OneToMany(mappedBy="user")
	private List<Profile> profiles;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<UserRole> userRoles;

	public User() {
		super();
		this.userRoles = new ArrayList<UserRole>();
	}

	public User(int userId) {
		this.userId = userId;
	}
	
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean getAccountLocked() {
		return this.accountLocked;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean getApiLogin() {
		return this.apiLogin;
	}

	public void setApiLogin(boolean apiLogin) {
		this.apiLogin = apiLogin;
	}

	public Timestamp getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Timestamp getDateUpdated() {
		return this.dateUpdated;
	}

	public void setDateUpdated(Timestamp dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public int getFailedLoginAttempts() {
		return this.failedLoginAttempts;
	}

	public void setFailedLoginAttempts(int failedLoginAttempts) {
		this.failedLoginAttempts = failedLoginAttempts;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Timestamp getLastFailedLoginDate() {
		return this.lastFailedLoginDate;
	}

	public void setLastFailedLoginDate(Timestamp lastFailedLoginDate) {
		this.lastFailedLoginDate = lastFailedLoginDate;
	}

	public Timestamp getLastLoginDate() {
		return this.lastLoginDate;
	}

	public void setLastLoginDate(Timestamp lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean getPasswordExpired() {
		return this.passwordExpired;
	}

	public void setPasswordExpired(boolean passwordExpired) {
		this.passwordExpired = passwordExpired;
	}

	public String getPasswordHash() {
		return this.passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public Timestamp getPasswordHashDate() {
		return this.passwordHashDate;
	}

	public void setPasswordHashDate(Timestamp passwordHashDate) {
		this.passwordHashDate = passwordHashDate;
	}

	public Date getPasswordLastChangedDate() {
		return this.passwordLastChangedDate;
	}

	public void setPasswordLastChangedDate(Date passwordLastChangedDate) {
		this.passwordLastChangedDate = passwordLastChangedDate;
	}

	public String getPurpose() {
		return this.purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public List<Job> getJobs() {
		return this.jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public Job addJob(Job job) {
		getJobs().add(job);
		job.setCreateUser(this);

		return job;
	}

	public Job removeJob(Job job) {
		getJobs().remove(job);
		job.setCreateUser(null);

		return job;
	}

	public List<JobApply> getJobApplies() {
		return this.jobApplies;
	}

	public void setJobApplies(List<JobApply> jobApplies) {
		this.jobApplies = jobApplies;
	}

	public JobApply addJobApply(JobApply jobApply) {
		getJobApplies().add(jobApply);
		jobApply.setApplyUser(this);

		return jobApply;
	}

	public JobApply removeJobApply(JobApply jobApply) {
		getJobApplies().remove(jobApply);
		jobApply.setApplyUser(null);

		return jobApply;
	}

	public List<TQuestion> getTQuestions() {
		return this.tQuestions;
	}

	public void setTQuestions(List<TQuestion> TQuestions) {
		this.tQuestions = TQuestions;
	}

	public TQuestion addTQuestion(TQuestion TQuestion) {
		getTQuestions().add(TQuestion);
		TQuestion.setUser(this);

		return TQuestion;
	}

	public TQuestion removeTQuestion(TQuestion TQuestion) {
		getTQuestions().remove(TQuestion);
		TQuestion.setUser(null);

		return TQuestion;
	}

	public List<TUserTest> getTUserTests() {
		return this.tUserTests;
	}

	public void setTUserTests(List<TUserTest> TUserTests) {
		this.tUserTests = TUserTests;
	}

	public TUserTest addTUserTest(TUserTest TUserTest) {
		getTUserTests().add(TUserTest);
		TUserTest.setUser(this);

		return TUserTest;
	}

	public TUserTest removeTUserTest(TUserTest TUserTest) {
		getTUserTests().remove(TUserTest);
		TUserTest.setUser(null);

		return TUserTest;
	}

	public List<UCertification> getUCertifications() {
		return uCertifications;
	}

	public void setUCertifications(List<UCertification> uCertifications) {
		this.uCertifications = uCertifications;
	}
	
	public UCertification addUCertification(UCertification uCertification) {
		getUCertifications().add(uCertification);
		uCertification.setUser(this);
		
		return uCertification;
	}
	
	public UCertification removeUCertification(UCertification uCertification) {
		getUCertifications().remove(uCertification);
		uCertification.setUser(null);
		
		return uCertification;
	}

	public List<UEducation> getUEducations() {
		return uEducations;
	}

	public void setUEducations(List<UEducation> uEducations) {
		this.uEducations = uEducations;
	}
	
	public UEducation addUEducation(UEducation uEducation) {
		getUEducations().add(uEducation);
		uEducation.setUser(this);
		
		return uEducation;
	}
	
	public UEducation removeUEducation(UEducation uEducation) {
		getUEducations().remove(uEducation);
		uEducation.setUser(null);
		
		return uEducation;
	}

	public List<UEmployment> getUEmployments() {
		return uEmployments;
	}

	public void setUEmployments(List<UEmployment> uEmployments) {
		this.uEmployments = uEmployments;
	}

	public UEmployment addUEmployment(UEmployment uEmployment) {
		getUEmployments().add(uEmployment);
		uEmployment.setUser(this);
		
		return uEmployment;
	}
	
	public UEmployment removeUEmployment(UEmployment uEmployment) {
		getUEmployments().remove(uEmployment);
		uEmployment.setUser(null);
		
		return uEmployment;
	}
	
	public List<ULicense> getULicenses() {
		return uLicenses;
	}

	public void setULicenses(List<ULicense> uLicenses) {
		this.uLicenses = uLicenses;
	}
	
	public ULicense addUEmployment(ULicense uLicense) {
		getULicenses().add(uLicense);
		uLicense.setUser(this);
		
		return uLicense;
	}
	
	public ULicense removeULicense(ULicense uLicense) {
		getULicenses().remove(uLicense);
		uLicense.setUser(null);
		
		return uLicense;
	}

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}
	

	public Profile addProfile(Profile profile) {
		getProfiles().add(profile);
		profile.setUser(this);

		return profile;
	}

	public Profile removeProfile(Profile profile) {
		getProfiles().remove(profile);
		profile.setUser(null);

		return profile;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(UserRole userRole) {
		this.getUserRoles().add(userRole);
		userRole.setUser(this);

		return userRole;
	}
}