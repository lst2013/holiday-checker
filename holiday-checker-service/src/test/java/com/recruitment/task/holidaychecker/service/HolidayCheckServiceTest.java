package com.recruitment.task.holidaychecker.service;

import com.recruitment.task.holidaychecker.model.HolidayApiResponse;
import com.recruitment.task.holidaychecker.model.HolidayApiResult;
import com.recruitment.task.holidaychecker.model.HolidayCheckRequest;
import com.recruitment.task.holidaychecker.model.HolidayCheckResponse;
import com.recruitment.task.holidaychecker.rest.client.HolidayApiRestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;

@ExtendWith(MockitoExtension.class)
class HolidayCheckServiceTest {

    private static HolidayCheckRequest holidayCheckRequest;

    @Mock
    private HolidayApiRestClient holidayApiRestClient;

    @InjectMocks
    private HolidayCheckService holidayCheckService;

    @BeforeAll
    static void initialize() {
        holidayCheckRequest = new HolidayCheckRequest();
        holidayCheckRequest.setDate(LocalDate.now());
        holidayCheckRequest.setFirstCountry(Locale.JAPAN);
        holidayCheckRequest.setSecondCountry(Locale.ITALY);
    }

    @Test
    void whenGetsResultsThenReturnsNearestHolidayForBothCountries() {
        HolidayApiResponse holidayApiResponseForJapan = createHolidayApiResponse(
            createHolidayApiResult(2019, 3, 15, "holiday 1 japan"),
            createHolidayApiResult(2019, 4, 27, "holiday 2 japan"),
            createHolidayApiResult(2019, 5, 30, "holiday 4 japan"),
            createHolidayApiResult(2019, 6, 8, "holiday 3 japan")
        );

        HolidayApiResponse holidayApiResponseForItaly = createHolidayApiResponse(
            createHolidayApiResult(2019, 4, 27, "holiday 1 italy"),
            createHolidayApiResult(2019, 5, 30, "holiday 2 italy"),
            createHolidayApiResult(2019, 6, 3, "holiday 3 italy")
        );

        Mockito.when(holidayApiRestClient.getHolidayApiResponse(Mockito.any(), Mockito.any())).thenReturn(holidayApiResponseForJapan).thenReturn(holidayApiResponseForItaly);

        HolidayCheckResponse holidayCheckResponse = holidayCheckService.checkHoliday(holidayCheckRequest);

        Assertions.assertAll(
            () -> Assertions.assertEquals(LocalDate.of(2019, 4, 27), holidayCheckResponse.getDate()),
            () -> Assertions.assertEquals("holiday 2 japan", holidayCheckResponse.getName1()),
            () -> Assertions.assertEquals("holiday 1 italy", holidayCheckResponse.getName2())
        );
    }

    @Test
    void whenGetsEmptyFirstResultThenReturnsEmptyResponse() {
        Mockito.when(holidayApiRestClient.getHolidayApiResponse(Mockito.any(), Mockito.any())).thenReturn(createHolidayApiResponse());

        HolidayCheckResponse holidayCheckResponse = holidayCheckService.checkHoliday(holidayCheckRequest);

        Assertions.assertAll(
                () -> Assertions.assertNull(holidayCheckResponse.getDate()),
                () -> Assertions.assertNull(holidayCheckResponse.getName1()),
                () -> Assertions.assertNull(holidayCheckResponse.getName2())
        );
    }

    private HolidayApiResponse createHolidayApiResponse(HolidayApiResult... holidayApiResultList) {
        HolidayApiResponse holidayApiResponse = new HolidayApiResponse();
        holidayApiResponse.setResults(Arrays.asList(holidayApiResultList));

        return holidayApiResponse;
    }

    private HolidayApiResult createHolidayApiResult(int year, int month, int day, String holidayName) {
        HolidayApiResult holidayApiResult;
        holidayApiResult = new HolidayApiResult();
        holidayApiResult.setDate(LocalDate.of(year, month, day));
        holidayApiResult.setName(holidayName);

        return holidayApiResult;
    }
}