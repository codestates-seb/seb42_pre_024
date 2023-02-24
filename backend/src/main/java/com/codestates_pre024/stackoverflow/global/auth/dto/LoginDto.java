package com.codestates_pre024.stackoverflow.global.auth.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class LoginDto {
    private String email;
    private String password;
}
