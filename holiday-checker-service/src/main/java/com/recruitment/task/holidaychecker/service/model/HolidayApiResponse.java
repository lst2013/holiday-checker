package com.recruitment.task.holidaychecker.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.StringUtils;

import java.util.List;

public class HolidayApiResponse {

    private int status;

    private String error;

    @JsonProperty("holidays")
    private List<HolidayApiResult> results;

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public List<HolidayApiResult> getResults() {
        return results;
    }

    public boolean hasError() {
        return status != 200 && !StringUtils.isEmpty(error);
    }

    public boolean isEmpty() {
        return results == null || results.isEmpty();
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setResults(List<HolidayApiResult> results) {
        this.results = results;
    }
}