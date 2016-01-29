package com.sharpinu.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sharpinu.web.form.PostJobForm;

/**
 * Validator for post job form
 * 
 * @author Mark
 *
 */
public class PostJobFormValidator implements Validator {

	public boolean supports(Class<?> candidate) {
		return PostJobForm.class.isAssignableFrom(candidate);
	}

	public void validate(Object target, Errors errors) {
		PostJobForm postJobForm = (PostJobForm) target;

	}

}
