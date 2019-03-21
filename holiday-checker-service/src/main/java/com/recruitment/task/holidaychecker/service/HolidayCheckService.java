package com.recruitment.task.holidaychecker.service;

import com.recruitment.task.holidaychecker.model.HolidayApiResponse;
import com.recruitment.task.holidaychecker.model.HolidayApiResult;
import com.recruitment.task.holidaychecker.model.HolidayCheckRequest;
import com.recruitment.task.holidaychecker.model.HolidayCheckResponse;
import com.recruitment.task.holidaychecker.rest.client.HolidayApiRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        for (HolidayApiResult resultForFirstCountry : responseForFirstCountry.getResults()) {
            for (HolidayApiResult resultForSecondCountry : responseForSecondCountry.getResults()) {
                if (resultsMatch(resultForFirstCountry, resultForSecondCountry)) {
                    return createHolidayCheckResponseFromResults(resultForFirstCountry, resultForSecondCountry);
                }
            }
        }

        return EMPTY_HOLIDAY_CHECK_RESPONSE;
    }

    private boolean resultsMatch(HolidayApiResult resultForFirstCountry, HolidayApiResult resultForSecondCountry) {
        return resultForFirstCountry.getDate().isEqual(resultForSecondCountry.getDate());
    }

    private HolidayCheckResponse createHolidayCheckResponseFromResults(HolidayApiResult resultForFirstCountry, HolidayApiResult resultForSecondCountry) {
        HolidayCheckResponse holidayCheckResponse = new HolidayCheckResponse();
        holidayCheckResponse.setDate(resultForFirstCountry.getDate());
        holidayCheckResponse.setName1(resultForFirstCountry.getName());
        holidayCheckResponse.setName2(resultForSecondCountry.getName());

        return holidayCheckResponse;
    }
}