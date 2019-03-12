package com.recruitment.task.holidaychecker.service.util.rest;

import com.recruitment.task.holidaychecker.service.exception.HolidayCheckException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class HolidayApiResponseErrorHandler implements ResponseErrorHandler {

    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        HttpStatus statusCode = clientHttpResponse.getStatusCode();
        return statusCode.is4xxClientError() || statusCode.is5xxServerError();
    }

    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        HttpStatus statusCode = clientHttpResponse.getStatusCode();
        String message;

        try (InputStream inputStream = clientHttpResponse.getBody();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            message = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        }

        throw new HolidayCheckException(statusCode.value(), message);
    }
}