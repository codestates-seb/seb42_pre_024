package com.codestates_pre024.stackoverflow.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class QuestionDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        private String title;
        private String contents;
        private long memberId;
    }

    @Getter
    public static class Patch {
        private String title;
        private String contents;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long id;
        private String title;
        private String contents;
//        private List<Answer> answers;
    }
}
