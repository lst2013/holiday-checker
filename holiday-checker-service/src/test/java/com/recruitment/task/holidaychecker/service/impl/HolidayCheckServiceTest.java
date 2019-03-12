package com.recruitment.task.holidaychecker.service.impl;

import com.recruitment.task.holidaychecker.config.repository.HolidayCheckConfigRepository;
import com.recruitment.task.holidaychecker.service.model.HolidayCheckRequest;
import com.recruitment.task.holidaychecker.service.util.uri.UriBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Locale;

/*@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { HolidayCheckService.class, HolidayCheckConfigRepository.class, UriBuilder.class, RestTemplate.class })
@TestPropertySource(locations = "classpath:application-test.properties")*/
public class HolidayCheckServiceTest {

    //@Autowired
    private HolidayCheckService holidayCheckService;

    //@Test
    public void test() {
        HolidayCheckRequest holidayCheckRequest = new HolidayCheckRequest();
        holidayCheckRequest.setDate(LocalDate.now());
        holidayCheckRequest.setFirstCountry(Locale.US);
        holidayCheckRequest.setSecondCountry(Locale.ITALY);

        holidayCheckService.checkHoliday(holidayCheckRequest);

    }
}