package com.codestates_pre024.stackoverflow.exception;

import lombok.Getter;

public enum ExceptionCode {
    //400 - BAD_REQUEST
    BAD_REQUEST(400, "Bad Request"),

    //404 - NOT FOUND
    QUESTION_NOT_FOUND(404, "Question Not Found"),
    MEMBER_NOT_FOUND(404, "Member Not Found"),

    ANSWER_NOT_FOUND(404, "Answer Not Found"),

    //409 - CONFLICT
    EMAIL_ALREADY_EXIST(409, "Email Already Exist"),
    NOT_RESOURCE_OWNER(409, "this member is not the owner of resource"),

    //500 - server error
    INTERNAL_SERVER_ERROR(500, "Internal_Server_Error)");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
