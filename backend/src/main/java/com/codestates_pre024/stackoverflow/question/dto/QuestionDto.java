package com.codestates_pre024.stackoverflow.question.dto;

import com.codestates_pre024.stackoverflow.answer.dto.AnswerResponseDto;
import com.codestates_pre024.stackoverflow.member.dto.WriterResponse;
import com.codestates_pre024.stackoverflow.member.entity.Member;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class QuestionDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        @NotBlank
        @Size(min = 2, message = "제목은 2자 이상이어야 합니다.")
        private String title;
        @NotBlank(message = "내용은 공백이 아니어야 합니다.")
        @Size(min = 2, message = "내용은 2자 이상이어야 합니다.")
        private String contents;
        private Long memberId;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Patch {
        private Long id;
        @Nullable
        @Size(min = 2, message = "제목은 2자 이상이어야 합니다.")
        private String title;
        @Nullable
        @Size(min = 2, message = "내용은 2자 이상이어야 합니다.")
        private String contents;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String title;
        private String contents;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private WriterResponse member;
        private List<AnswerResponseDto> answers;
    }

    // List로 보내주는 Question 응답 데이터
    @Getter
    @Setter
    @AllArgsConstructor
    public static class listResponse {
        private Long id;
        private String title;
        private String contents;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private WriterResponse member;
    }

    // 마이페이지로 넘겨주는 Response 데이터
    @Getter
    @Setter
    @AllArgsConstructor
    public static class myPageResponse {
        private Long id;
        private String title;
//        answerNum? 자신이 작성한 answer List size 함수?
        private LocalDateTime createdAt;
    }
}