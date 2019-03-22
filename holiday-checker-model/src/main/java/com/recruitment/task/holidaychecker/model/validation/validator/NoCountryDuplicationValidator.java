package com.recruitment.task.holidaychecker.model.validation.validator;

import com.recruitment.task.holidaychecker.model.validation.model.NoCountryDuplicationModel;
import com.recruitment.task.holidaychecker.model.validation.annotation.NoCountryDuplication;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Locale;

public class NoCountryDuplicationValidator implements ConstraintValidator<NoCountryDuplication, NoCountryDuplicationModel> {

    public boolean isValid(NoCountryDuplicationModel noCountryDuplicationModel, ConstraintValidatorContext constraintValidatorContext) {
        Locale firstCountry = noCountryDuplicationModel.getFirstCountry();
        Locale secondCountry = noCountryDuplicationModel.getSecondCountry();

        return !firstCountry.equals(secondCountry);
    }
}