package com.recruitment.task.holidaychecker.rest.client.uri;

import com.recruitment.task.holidaychecker.config.repository.HolidayCheckConfigRepository;
import com.recruitment.task.holidaychecker.model.HolidayCheckRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.Locale;
import java.util.function.Supplier;

@Component
public class HolidayApiUriBuilder {

    @Autowired
    private HolidayCheckConfigRepository holidayCheckConfigRepository;

    public URI buildUri(HolidayCheckRequest holidayCheckRequest, Supplier<Locale> countrySupplier) {
        String url = holidayCheckConfigRepository.getHolidayCheckConfigValueByKey("url");
        String key = holidayCheckConfigRepository.getHolidayCheckConfigValueByKey("key");

        Locale country = countrySupplier.get();
        LocalDate date = holidayCheckRequest.getDate();

        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url)
                                                          .queryParam("key", key)
                                                          .queryParam("country", country.getLanguage())
                                                          .queryParam("year", date.getYear())
                                                          .queryParam("month", date.getMonthValue())
                                                          .queryParam("day", date.getDayOfMonth())
                                                          .build();

        return uriComponents.toUri();
    }
}