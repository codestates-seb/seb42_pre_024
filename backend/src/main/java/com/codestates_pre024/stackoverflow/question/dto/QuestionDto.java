package com.codestates_pre024.stackoverflow.question.dto;

import com.codestates_pre024.stackoverflow.answer.dto.AnswerResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
        private Long questionId;
        @NotBlank
        @Size(min = 2, message = "제목은 2자 이상이어야 합니다.")
        private String title;
        @NotBlank(message = "내용은 공백이 아니여야 합니다.")
        @Size(min = 2, message = "내용은 2자 이상이어야 합니다.")
        private String contents;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {
        private Long questionId;
        @NotBlank
        @Size(min = 2, message = "제목은 2자 이상이어야 합니다.")
        private String title;
        @NotBlank(message = "내용은 공백이 아니여야 합니다.")
        @Size(min = 2, message = "내용은 2자 이상이어야 합니다.")
        private String contents;
        private LocalDateTime createAt;
        private LocalDateTime modifiedAt;
        private List<AnswerResponseDto> answers;
    }
}