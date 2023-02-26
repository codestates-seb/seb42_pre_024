package com.codestates_pre024.stackoverflow.global.auth.utils;

import com.codestates_pre024.stackoverflow.advice.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorResponseSender {
    public static void sendResponse(HttpServletResponse response, HttpStatus status) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        ErrorResponse errorResponse = ErrorResponse.of(status);
        String errorJson = mapper.writeValueAsString(errorResponse);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE); //APPLICATION_JSON's STRING type
        response.setStatus(status.value());

        //getWriter() : 문자 텍스트를 클라이언트에 보낼 수 있는 PrintWriter 개체
        //write() : Writes a string. (param - String s)
        response.getWriter().write(errorJson);

    }
}
