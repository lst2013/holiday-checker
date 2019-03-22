package com.recruitment.task.holidaychecker.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.recruitment.task.holidaychecker.model.json.LocaleDeserializer;
import com.recruitment.task.holidaychecker.model.json.LocaleSerializer;
import com.recruitment.task.holidaychecker.model.validation.annotation.NoCountryDuplication;
import com.recruitment.task.holidaychecker.model.validation.group.DuplicationCheck;
import com.recruitment.task.holidaychecker.model.validation.group.NullCheck;
import com.recruitment.task.holidaychecker.model.validation.model.NoCountryDuplicationModel;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Locale;

@NoCountryDuplication(message = "Countries must be different", groups = DuplicationCheck.class)
public class HolidayCheckRequest implements NoCountryDuplicationModel {

    @NotNull(message = "First country cannot be null", groups = NullCheck.class)
    @JsonSerialize(using = LocaleSerializer.class)
    @JsonDeserialize(using = LocaleDeserializer.class)
    private Locale firstCountry;

    @NotNull(message = "Second country cannot be null", groups = NullCheck.class)
    @JsonSerialize(using = LocaleSerializer.class)
    @JsonDeserialize(using = LocaleDeserializer.class)
    private Locale secondCountry;

    @NotNull(message = "Date cannot be null", groups = NullCheck.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public Locale getFirstCountry() {
        return firstCountry;
    }

    public Locale getSecondCountry() {
        return secondCountry;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setFirstCountry(Locale firstCountry) {
        this.firstCountry = firstCountry;
    }

    public void setSecondCountry(Locale secondCountry) {
        this.secondCountry = secondCountry;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}