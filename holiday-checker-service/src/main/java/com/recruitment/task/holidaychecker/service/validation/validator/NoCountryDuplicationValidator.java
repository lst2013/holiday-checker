package com.recruitment.task.holidaychecker.service.validation.validator;

import com.recruitment.task.holidaychecker.service.validation.model.NoCountryDuplicationModel;
import com.recruitment.task.holidaychecker.service.validation.annotation.NoCountryDuplication;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Locale;

public class NoCountryDuplicationValidator implements ConstraintValidator<NoCountryDuplication, NoCountryDuplicationModel> {

    public void initialize(NoCountryDuplication constraintAnnotation) {

    }

    public boolean isValid(NoCountryDuplicationModel noCountryDuplicationModel, ConstraintValidatorContext constraintValidatorContext) {
        Locale firstCountry = noCountryDuplicationModel.getFirstCountry();
        Locale secondCountry = noCountryDuplicationModel.getSecondCountry();

        return !firstCountry.equals(secondCountry);
    }
}