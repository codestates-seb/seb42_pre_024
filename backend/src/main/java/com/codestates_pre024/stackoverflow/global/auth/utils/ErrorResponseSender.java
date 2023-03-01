package com.codestates_pre024.stackoverflow.global.auth.utils;

import com.codestates_pre024.stackoverflow.advice.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorResponseSender {
    public static void sendResponse(HttpServletResponse response, HttpStatus status) throws IOException {

        ErrorResponse errorResponse = ErrorResponse.of(status);
        sendResponse(response,status, errorResponse);
    }

    public static void sendResponse(HttpServletResponse response, HttpStatus status, String message) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse(status.value(), message);
        sendResponse(response,status, errorResponse);
    }

    private static void sendResponse(HttpServletResponse response, HttpStatus status, ErrorResponse errorResponse) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String errorJson = mapper.writeValueAsString(errorResponse);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE); //APPLICATION_JSON's STRING type
        response.setStatus(status.value());

        response.getWriter().write(errorJson);
    }
}
