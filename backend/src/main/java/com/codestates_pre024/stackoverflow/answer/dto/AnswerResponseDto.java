package com.codestates_pre024.stackoverflow.answer.dto;

import com.codestates_pre024.stackoverflow.member.dto.WriterResponse;
import lombok.*;

import java.time.LocalDateTime;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerResponseDto {
    private Long id;

    private String contents;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private WriterResponse member;

}
