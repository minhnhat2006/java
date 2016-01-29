package com.sharpinu.validator;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sharpinu.validator.constraint.EnumCheck;

/**
 * 
 * @author Mark
 *
 */
public class EnumCheckValidator implements ConstraintValidator<EnumCheck, String> {
	private List<String> enumValues;
	
	public void initialize(EnumCheck constraintAnnotation) {
		this.enumValues = Arrays.asList(constraintAnnotation.enumValues());
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		return enumValues.contains(value);
	}

}
