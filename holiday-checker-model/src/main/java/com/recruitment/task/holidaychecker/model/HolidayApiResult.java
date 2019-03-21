package com.recruitment.task.holidaychecker.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HolidayApiResult {

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        HolidayApiResult that = (HolidayApiResult) other;

        return new EqualsBuilder()
                   // Only date is needed to compare objects
                   .append(date, that.date)
                   .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                   .append(date)
                   .toHashCode();
    }
}