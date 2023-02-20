package com.codestates_pre024.stackoverflow.global.utils;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

@Getter
public class ApiResponse<T> {
    private String code;
    private String message;
    private T data;

    public ApiResponse(HttpStatus status, String message) {
        this.code = String.valueOf(status.value());
        this.message = message;
    }

    public ApiResponse(HttpStatus status, String message, @Nullable T data) {
        this(status, message);
        this.data = data;
    }
}
