package com.recruitment.task.holidaychecker.model.validation.model;

import java.util.Locale;

public interface NoCountryDuplicationModel {

    Locale getFirstCountry();

    Locale getSecondCountry();
}