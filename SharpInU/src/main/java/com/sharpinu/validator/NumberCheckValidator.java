package com.sharpinu.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sharpinu.validator.constraint.NumberCheck;

/**
 * Validator for NumberCheck annotation
 * @author Mark
 *
 */
public class NumberCheckValidator implements ConstraintValidator<NumberCheck, String> {
	
	public void initialize(NumberCheck constraintAnnotation) {
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			Double.parseDouble(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}