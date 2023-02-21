package com.codestates_pre024.stackoverflow.question.controller;

import com.codestates_pre024.stackoverflow.question.service.QuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private QuestionService questionService;

    private final static String QUESTION_DEFAULT_URL = "/questions";

    @Test
    @DisplayName("[컨트롤러] PostQuestion")
    void postQuestion() throws Exception {
        // given

        // when

        // then

    }

    @Test
    @DisplayName("[컨트롤러] PatchQuestion")
    void patchQuestion() throws Exception {
        // given

        // when

        // then

    }

    @Test
    @DisplayName("[컨트롤러] GetQuestion")
    void getQuestion() throws Exception {
        // given

        // when

        // then

    }

    @Test
    @DisplayName("[컨트롤러] GetQuestions")
    void getQuestions() throws Exception {
        // given

        // when

        // then

    }

    @Test
    @DisplayName("[컨트롤러] DeleteQuestion")
    void deleteQuestion() throws Exception {
        // given

        // when

        // then

    }
}
