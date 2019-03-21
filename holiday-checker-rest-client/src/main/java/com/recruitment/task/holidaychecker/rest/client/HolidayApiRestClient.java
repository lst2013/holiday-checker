package com.recruitment.task.holidaychecker.rest.client;

import com.recruitment.task.holidaychecker.model.HolidayApiResponse;
import com.recruitment.task.holidaychecker.model.HolidayCheckRequest;
import com.recruitment.task.holidaychecker.rest.client.uri.HolidayApiUriBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Locale;
import java.util.function.Supplier;

@Service
public class HolidayApiRestClient {

    @Autowired
    private HolidayApiUriBuilder holidayApiUriBuilder;

    @Autowired
    private RestTemplate restTemplate;

    public HolidayApiResponse getHolidayApiResponse(HolidayCheckRequest holidayCheckRequest, Supplier<Locale> countrySupplier) {
        URI uri = holidayApiUriBuilder.buildUri(holidayCheckRequest, countrySupplier);
        return restTemplate.getForObject(uri, HolidayApiResponse.class);
    }
}
