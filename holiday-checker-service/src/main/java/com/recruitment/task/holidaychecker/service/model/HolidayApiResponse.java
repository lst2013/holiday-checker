package com.recruitment.task.holidaychecker.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HolidayApiResponse {

    @JsonProperty("holidays")
    private List<HolidayApiResult> results;

    public List<HolidayApiResult> getResults() {
        return results;
    }

    public boolean isEmpty() {
        return results == null || results.isEmpty();
    }

    public void setResults(List<HolidayApiResult> results) {
        this.results = results;
    }
}