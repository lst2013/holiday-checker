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

    private static final String URL = "url";
    private static final String KEY = "key";
    private static final String COUNTRY = "country";
    private static final String YEAR = "year";
    private static final String MONTH = "month";
    private static final String DAY = "day";

    @Autowired
    private HolidayCheckConfigRepository holidayCheckConfigRepository;

    public URI buildUri(HolidayCheckRequest holidayCheckRequest, Supplier<Locale> countrySupplier) {
        String url = holidayCheckConfigRepository.getHolidayCheckConfigValueByKey(URL);
        String key = holidayCheckConfigRepository.getHolidayCheckConfigValueByKey(KEY);

        Locale country = countrySupplier.get();
        LocalDate date = holidayCheckRequest.getDate();

        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url)
                                                          .queryParam(KEY, key)
                                                          .queryParam(COUNTRY, country.getLanguage())
                                                          .queryParam(YEAR, date.getYear())
                                                          .queryParam(MONTH, date.getMonthValue())
                                                          .queryParam(DAY, date.getDayOfMonth())
                                                          .build();

        return uriComponents.toUri();
    }
}