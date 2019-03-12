package com.recruitment.task.holidaychecker.service.util.uri;

import com.recruitment.task.holidaychecker.service.model.HolidayCheckRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.Locale;
import java.util.function.Supplier;

@Component
public class UriBuilder {

    public URI buildUri(UriConfig uriConfig, HolidayCheckRequest holidayCheckRequest, Supplier<Locale> countrySupplier) {
        Locale country = countrySupplier.get();
        LocalDate date = holidayCheckRequest.getDate();

        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(uriConfig.getUrl())
                                        .queryParam("key", uriConfig.getKey())
                                        .queryParam("country", country.getLanguage())
                                        .queryParam("year", date.getYear())
                                        .queryParam("month", date.getMonthValue())
                                        .queryParam("day", date.getDayOfMonth())
                                        .build();

        return uriComponents.toUri();
    }
}