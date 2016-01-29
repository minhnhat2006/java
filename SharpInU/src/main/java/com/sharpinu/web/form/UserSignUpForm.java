package com.sharpinu.web.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Form for sign_up page
 * 
 * @author Mark
 *
 */
public class UserSignUpForm {

	@NotBlank(message = "First name is required.")
	private String firstName;

	@NotBlank(message = "Last name is required.")
	private String lastName;

	@NotBlank(message = "Email address is required.")
	@Email(message = "Email address must be well-formed.")
	private String email;

	@Length(min = 6, max = 32, message = "Password length is between 6 to 32 characters.")
	private String password;

	private String confirmPassword;

	private boolean acceptAgreement;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public boolean isAcceptAgreement() {
		return acceptAgreement;
	}

	public void setAcceptAgreement(boolean acceptAgreement) {
		this.acceptAgreement = acceptAgreement;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
