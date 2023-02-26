package com.codestates_pre024.stackoverflow.global.auth.handler;

import com.codestates_pre024.stackoverflow.global.auth.utils.ErrorResponseSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class MemberAuthenticationEntryPoint implements AuthenticationEntryPoint {


    // SignatureException, ExpiredJwtException등 발생해 SecurityContext에 저장되지 않은 경우 호출되는 핸들러
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        Exception exception = (Exception) request.getAttribute("exception"); //JwtVerificationFilter에서 설정한 속성 이름
        ErrorResponseSender.sendResponse(response, HttpStatus.UNAUTHORIZED);

        //log
        String errorMessage = "";

        if (exception != null)
            errorMessage = exception.getMessage();
        else // AuthenticationException
            errorMessage = authException.getMessage();

        log.warn("(401) Unauthorized error : " + errorMessage );
    }
}
