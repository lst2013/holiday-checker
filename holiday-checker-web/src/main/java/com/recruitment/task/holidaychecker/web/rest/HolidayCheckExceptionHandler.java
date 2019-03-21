package com.recruitment.task.holidaychecker.web.rest;

import com.recruitment.task.holidaychecker.rest.client.exception.HolidayApiException;
import com.recruitment.task.holidaychecker.service.exception.HolidayCheckException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class HolidayCheckExceptionHandler {

    @ExceptionHandler(HolidayCheckException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<String> handleHolidayCheckException(HolidayCheckException exception) {
        return exception.getMessages();
    }

    @ExceptionHandler(HolidayApiException.class)
    public ResponseEntity<String> handleHolidayApiException(HolidayApiException exception) {
        HttpStatus status = HttpStatus.valueOf(exception.getErrorCode());
        String message = exception.getMessage();

        if (StringUtils.isEmpty(message)) {
            message = status.getReasonPhrase();
        }

        return new ResponseEntity<>(message, status);
    }
}
