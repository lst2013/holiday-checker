package com.recruitment.task.holidaychecker.service.validation.exception;

import java.util.List;

public class HolidayCheckValidationException extends RuntimeException {

    private List<String> messages;

    public HolidayCheckValidationException(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }
}