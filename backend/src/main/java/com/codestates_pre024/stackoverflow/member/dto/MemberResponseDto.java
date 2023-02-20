package com.codestates_pre024.stackoverflow.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String name;
    private String aboutMe;

    //private String profileImage;
    //private List<QuestionDto.Response> questions;
}
