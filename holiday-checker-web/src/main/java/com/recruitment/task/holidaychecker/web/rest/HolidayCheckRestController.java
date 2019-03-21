package com.recruitment.task.holidaychecker.web.rest;

import com.recruitment.task.holidaychecker.model.HolidayCheckRequest;
import com.recruitment.task.holidaychecker.model.HolidayCheckResponse;
import com.recruitment.task.holidaychecker.service.exception.HolidayCheckException;
import com.recruitment.task.holidaychecker.model.validation.group.Sequence;
import com.recruitment.task.holidaychecker.service.HolidayCheckService;
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
            throw new HolidayCheckException(messages);
        }

        return holidayCheckService.checkHoliday(holidayCheckRequest);
    }
}