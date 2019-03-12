package com.recruitment.task.holidaychecker.web.rest;

import com.recruitment.task.holidaychecker.service.exception.HolidayCheckException;
import com.recruitment.task.holidaychecker.service.validation.exception.HolidayCheckValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class HolidayCheckExceptionHandler {

    @ExceptionHandler(HolidayCheckValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<String> handleHolidayCheckValidationException(HolidayCheckValidationException exception) {
        return exception.getMessages();
    }

    @ExceptionHandler(HolidayCheckException.class)
    public ResponseEntity<String> handleHolidayCheckException(HolidayCheckException exception) {
        HttpStatus status = HttpStatus.valueOf(exception.getErrorCode());
        String message = exception.getMessage();

        if (StringUtils.isEmpty(message)) {
            message = status.getReasonPhrase();
        }

        return new ResponseEntity<>(message, status);
    }
}
