package com.recruitment.task.holidaychecker.service.exception;

import java.util.List;

public class HolidayCheckException extends RuntimeException {

    private List<String> messages;

    public HolidayCheckException(List<String> messages) {
        super();

        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }
}