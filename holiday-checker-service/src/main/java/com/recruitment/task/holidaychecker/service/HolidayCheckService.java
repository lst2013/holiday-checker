package com.recruitment.task.holidaychecker.service;

import com.recruitment.task.holidaychecker.model.HolidayApiResponse;
import com.recruitment.task.holidaychecker.model.HolidayApiResult;
import com.recruitment.task.holidaychecker.model.HolidayCheckRequest;
import com.recruitment.task.holidaychecker.model.HolidayCheckResponse;
import com.recruitment.task.holidaychecker.rest.client.HolidayApiRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayCheckService {

    private static final HolidayCheckResponse EMPTY_HOLIDAY_CHECK_RESPONSE = new HolidayCheckResponse();

    @Autowired
    private HolidayApiRestClient holidayApiRestClient;

    public HolidayCheckResponse checkHoliday(HolidayCheckRequest holidayCheckRequest) {
        HolidayApiResponse responseForFirstCountry = holidayApiRestClient.getHolidayApiResponse(holidayCheckRequest, holidayCheckRequest::getFirstCountry);

        if (responseForFirstCountry.isEmpty()) {
            return EMPTY_HOLIDAY_CHECK_RESPONSE;
        }

        HolidayApiResponse responseForSecondCountry = holidayApiRestClient.getHolidayApiResponse(holidayCheckRequest, holidayCheckRequest::getSecondCountry);

        if (responseForSecondCountry.isEmpty()) {
            return EMPTY_HOLIDAY_CHECK_RESPONSE;
        }

        return getHolidayCheckResponse(responseForFirstCountry, responseForSecondCountry);
    }

    private HolidayCheckResponse getHolidayCheckResponse(HolidayApiResponse responseForFirstCountry, HolidayApiResponse responseForSecondCountry) {
        List<HolidayApiResult> resultsForFirstCountry = responseForFirstCountry.getResults();
        List<HolidayApiResult> resultsForSecondCountry = responseForSecondCountry.getResults();

        resultsForFirstCountry.retainAll(resultsForSecondCountry);
        resultsForSecondCountry.retainAll(resultsForFirstCountry);

        if (resultsForFirstCountry.isEmpty() || resultsForSecondCountry.isEmpty()) {
            return EMPTY_HOLIDAY_CHECK_RESPONSE;
        }

        return createHolidayCheckResponseFromResults(resultsForFirstCountry.get(0), resultsForSecondCountry.get(0));
    }

    private HolidayCheckResponse createHolidayCheckResponseFromResults(HolidayApiResult resultForFirstCountry, HolidayApiResult resultForSecondCountry) {
        HolidayCheckResponse holidayCheckResponse = new HolidayCheckResponse();
        holidayCheckResponse.setDate(resultForFirstCountry.getDate());
        holidayCheckResponse.setName1(resultForFirstCountry.getName());
        holidayCheckResponse.setName2(resultForSecondCountry.getName());

        return holidayCheckResponse;
    }
}