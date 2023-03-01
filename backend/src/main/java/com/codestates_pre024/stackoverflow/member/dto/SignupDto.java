package com.codestates_pre024.stackoverflow.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class SignupDto {

    @Pattern(regexp = "^[a-zA-Z가-힣0-9]{4,16}$", message = "이름은 특수문자 없이 4 ~ 16자 사이로 만들어야 합니다. ")
    @NotBlank
    private String name;

    @Pattern(regexp = "^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$$", message =  "이메일 형식이 잘못되었습니다.")
    @NotBlank
    private String email;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])[a-zA-Z\\d]{8,}$", message = "비밀번호는 영어와 숫자룰 최소 1개씩 포함해 8자 이상으로 만들어야 합니다.")
    @NotBlank
    private String password;

    @Builder
    public SignupDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
