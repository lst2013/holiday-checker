package com.recruitment.task.holidaychecker.service.exception;

public class HolidayCheckException extends RuntimeException {

    private int errorCode;

    public HolidayCheckException(int errorCode, String message) {
        super(message);

        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}