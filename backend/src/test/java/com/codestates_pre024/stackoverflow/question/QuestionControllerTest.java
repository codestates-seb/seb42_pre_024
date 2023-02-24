package com.codestates_pre024.stackoverflow.question;

import com.codestates_pre024.stackoverflow.question.dto.QuestionDto;
import com.codestates_pre024.stackoverflow.question.entity.Question;
import com.codestates_pre024.stackoverflow.question.mapper.QuestionMapper;
import com.codestates_pre024.stackoverflow.question.service.QuestionService;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private QuestionService questionService;

    @Autowired
    private QuestionMapper mapper;

    @Test
    void postQuestionTest() throws Exception {
        // given
        QuestionDto.Post post = new QuestionDto.Post("제목", "내용", 1L);

        Question question = mapper.questionPostDtoToQuestion(post);
        question.setId(1L);

        given(questionService.createQuestion(Mockito.any(Question.class), eq(1L)))
                .willReturn(question);

        String content = gson.toJson(post);

        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/questions")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions.andExpect(status().isCreated());
    }

    @Test
    void patchQuestionTest() throws Exception {
        // given
        QuestionDto.Patch patch = new QuestionDto.Patch(1L, "질문", "내용");

        Question question = mapper.questionPatchDtoToQuestion(patch);

        given(questionService.updateQuestion(Mockito.any(Question.class)))
                .willReturn(question);

        String content = gson.toJson(patch);

        // when
        ResultActions actions =
                mockMvc.perform(
                        patch("/questions/" + question.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions.andExpect(status().isMovedPermanently());
    }

    @Test
    void getQuestionTest() throws Exception {
        // given
        Question question = new Question("질문", "내용");
        question.setId(1L);

        given(questionService.findQuestion(Mockito.anyLong()))
                .willReturn(question);

        // when / when
        mockMvc.perform(
                get("/questions/" + question.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.title").value(question.getTitle()))
                .andExpect(jsonPath("$.data.contents").value(question.getContents()));
    }

    @Test
    void getQuestionsTest() throws Exception {
        // given
        Question question1 = new Question("제목1", "내용1");

        Question question2 = new Question("제목2", "내용2");

        Page<Question> pageQuestions =
                new PageImpl<>(List.of(question1, question2),
                        PageRequest.of(0, 10, Sort.by("id").descending()), 2);

        given(questionService.findQuestions(Mockito.anyInt(), Mockito.anyInt()))
                .willReturn(pageQuestions);

        String page = "1";
        String size = "10";
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", page);
        queryParams.add("size", size);

        // when
        ResultActions actions =
                mockMvc.perform(
                        get("/questions")
                                .params(queryParams)
                                .accept(MediaType.APPLICATION_JSON)
                );

        // then
        MvcResult result = actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andReturn();

        List list = JsonPath.parse(result.getResponse().getContentAsString()).read("$.data");

        assertThat(list.size(), is(2));
    }

    @Test
    void deleteQuestionTest() throws Exception {
        // given
        Long questionId = 1L;

        doNothing().when(questionService).deleteQuestion(questionId);

        // when
        ResultActions actions = mockMvc.perform(
                delete("/questions/" + questionId)
        );

         // when
        actions.andExpect(status().isNoContent());
    }
}
