package com.recruitment.task.holidaychecker.service.validation.model;

import java.util.Locale;

public interface NoCountryDuplicationModel {

    Locale getFirstCountry();

    Locale getSecondCountry();
}