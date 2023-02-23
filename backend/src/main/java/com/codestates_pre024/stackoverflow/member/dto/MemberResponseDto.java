package com.codestates_pre024.stackoverflow.member.dto;

import com.codestates_pre024.stackoverflow.question.dto.QuestionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String name;
    private String aboutMe;
    private String profileImage;

    private List<QuestionDto.myPageResponse> questions;
}
