package com.recruitment.task.holidaychecker.service.validation.annotation;

import com.recruitment.task.holidaychecker.service.validation.validator.NoCountryDuplicationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NoCountryDuplicationValidator.class)
public @interface NoCountryDuplication {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}