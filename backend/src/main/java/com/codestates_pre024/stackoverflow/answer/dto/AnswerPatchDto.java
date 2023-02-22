package com.codestates_pre024.stackoverflow.answer.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerPatchDto {
    private Long answerId;

    private Long memberId;

    @NotNull
    @Size(min = 2, message = "2글자 이상 입력하세요.")
    private String contents;
}
