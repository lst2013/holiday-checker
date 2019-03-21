package com.recruitment.task.holidaychecker.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class HolidayCheckResponse {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String name1;

    private String name2;

    public LocalDate getDate() {
        return date;
    }

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }
}