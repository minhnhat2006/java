package com.sharpinu.validator.constraint;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.sharpinu.validator.EnumCheckValidator;

/**
 * 
 * Constraint validation. String must be existed in enumeration.
 * 
 * @author Mark
 *
 */
@Documented
@Constraint(validatedBy = {EnumCheckValidator.class})
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface EnumCheck {
	String message() default "{sharpinu.validator.constraint.EnumCheck.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	String[] enumValues ();
}
