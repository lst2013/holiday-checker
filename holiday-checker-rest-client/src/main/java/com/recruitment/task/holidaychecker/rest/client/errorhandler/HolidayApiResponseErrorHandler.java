package com.recruitment.task.holidaychecker.rest.client.errorhandler;

import com.recruitment.task.holidaychecker.rest.client.exception.HolidayApiException;
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
        int statusCode = clientHttpResponse.getStatusCode().value();
        String errorMessage;

        try (InputStream inputStream = clientHttpResponse.getBody();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

             errorMessage = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        }

        throw new HolidayApiException(statusCode, errorMessage);
    }
}