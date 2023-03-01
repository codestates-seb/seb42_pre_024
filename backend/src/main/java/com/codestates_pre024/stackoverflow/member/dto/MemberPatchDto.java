package com.codestates_pre024.stackoverflow.member.dto;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class MemberPatchDto {

    private Long id;
    @Nullable
    @Pattern(regexp = "^[a-zA-Z가-힣0-9]{4,16}$")
    private String name;
    @Nullable
    @NotBlank
    private String aboutMe;

    @Builder
    public MemberPatchDto(Long id, @Nullable String name, @Nullable String aboutMe) {
        this.id = id;
        this.name = name;
        this.aboutMe = aboutMe;
    }
}
