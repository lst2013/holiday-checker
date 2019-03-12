package com.recruitment.task.holidaychecker.service.impl;

import com.recruitment.task.holidaychecker.config.repository.HolidayCheckConfigRepository;
import com.recruitment.task.holidaychecker.service.exception.HolidayCheckException;
import com.recruitment.task.holidaychecker.service.model.HolidayApiResponse;
import com.recruitment.task.holidaychecker.service.model.HolidayApiResult;
import com.recruitment.task.holidaychecker.service.model.HolidayCheckRequest;
import com.recruitment.task.holidaychecker.service.model.HolidayCheckResponse;
import com.recruitment.task.holidaychecker.service.util.uri.UriBuilder;
import com.recruitment.task.holidaychecker.service.util.uri.UriConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Locale;
import java.util.function.Supplier;

@Service
public class HolidayCheckService {

    private static final HolidayCheckResponse EMPTY_HOLIDAY_CHECK_RESPONSE = new HolidayCheckResponse();

    @Autowired
    private HolidayCheckConfigRepository holidayCheckConfigRepository;

    @Autowired
    private UriBuilder uriBuilder;

    @Autowired
    private RestTemplate restTemplate;

    public HolidayCheckResponse checkHoliday(HolidayCheckRequest holidayCheckRequest) {
        UriConfig uriConfig = createUriConfig();

        HolidayApiResponse responseForFirstCountry = getHolidayApiResponse(uriConfig, holidayCheckRequest, () -> holidayCheckRequest.getFirstCountry());

        if (responseForFirstCountry.isEmpty()) {
            return EMPTY_HOLIDAY_CHECK_RESPONSE;
        }

        HolidayApiResponse responseForSecondCountry = getHolidayApiResponse(uriConfig, holidayCheckRequest, () -> holidayCheckRequest.getSecondCountry());

        if (responseForSecondCountry.isEmpty()) {
            return EMPTY_HOLIDAY_CHECK_RESPONSE;
        }

        return getHolidayCheckResponse(responseForFirstCountry, responseForSecondCountry);
    }

    private UriConfig createUriConfig() {
        UriConfig uriConfig = new UriConfig();
        uriConfig.setUrl(holidayCheckConfigRepository.getHolidayCheckConfigValueByKey("url"));
        uriConfig.setKey(holidayCheckConfigRepository.getHolidayCheckConfigValueByKey("key"));

        return uriConfig;
    }

    private HolidayApiResponse getHolidayApiResponse(UriConfig uriConfig, HolidayCheckRequest holidayCheckRequest, Supplier<Locale> countrySupplier) {
        URI uri = uriBuilder.buildUri(uriConfig, holidayCheckRequest, countrySupplier);
        HolidayApiResponse holidayApiResponse;

        try {
            holidayApiResponse = restTemplate.getForObject(uri, HolidayApiResponse.class);
        } catch (RestClientException exception) {
            throw new HolidayCheckException(500, exception.getMessage());
        }

        if (holidayApiResponse.hasError()) {
            throw new HolidayCheckException(holidayApiResponse.getStatus(), holidayApiResponse.getError());
        }

        return holidayApiResponse;
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