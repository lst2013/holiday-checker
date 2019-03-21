package com.recruitment.task.holidaychecker.rest.client.exception;

public class HolidayApiException extends RuntimeException {

    private int errorCode;

    public HolidayApiException(int errorCode, String message) {
        super(message);

        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}