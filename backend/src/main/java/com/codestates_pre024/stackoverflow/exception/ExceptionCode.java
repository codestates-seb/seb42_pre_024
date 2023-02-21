package com.codestates_pre024.stackoverflow.exception;

import lombok.Getter;

public enum ExceptionCode {

    //404 - NOT FOUND
    QUESTION_NOT_FOUND(404, "Question Not Found"),
    MEMBER_NOT_FOUND(404, "Member Not Found"),

    //409 - CONFLICT

    EMAIL_AREADY_EXIST(409, "Email Aready Exist");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
