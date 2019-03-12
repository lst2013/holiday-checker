package com.recruitment.task.holidaychecker.web.rest;

import com.recruitment.task.holidaychecker.service.impl.HolidayCheckService;
import com.recruitment.task.holidaychecker.service.model.HolidayCheckRequest;
import com.recruitment.task.holidaychecker.service.model.HolidayCheckResponse;
import com.recruitment.task.holidaychecker.service.validation.exception.HolidayCheckValidationException;
import com.recruitment.task.holidaychecker.service.validation.group.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HolidayCheckRestController {

    @Autowired
    private HolidayCheckService holidayCheckService;

    @PostMapping(value = "/holidayCheck", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HolidayCheckResponse checkHoliday(@RequestBody @Validated(Sequence.class) HolidayCheckRequest holidayCheckRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> messages = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
            throw new HolidayCheckValidationException(messages);
        }

        return holidayCheckService.checkHoliday(holidayCheckRequest);
    }
}