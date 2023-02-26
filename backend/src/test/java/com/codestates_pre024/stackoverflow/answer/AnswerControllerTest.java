package com.codestates_pre024.stackoverflow.answer;

import com.codestates_pre024.stackoverflow.answer.dto.AnswerDto;
import com.codestates_pre024.stackoverflow.answer.dto.AnswerPatchDto;
import com.codestates_pre024.stackoverflow.answer.entity.Answer;
import com.codestates_pre024.stackoverflow.answer.mapper.AnswerMapper;
import com.codestates_pre024.stackoverflow.answer.service.AnswerService;
import com.codestates_pre024.stackoverflow.question.dto.QuestionDto;
import com.codestates_pre024.stackoverflow.question.entity.Question;
import com.codestates_pre024.stackoverflow.question.mapper.QuestionMapper;
import com.codestates_pre024.stackoverflow.question.service.QuestionService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AnswerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;
    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @MockBean
    private AnswerService answerService;
    @MockBean
    private QuestionService questionService;
    private final static String ANSWER_POST_URL = "/questions/{question-id}/answers";

    private final static String ANSWER_DEFAULT_URL = "/answers/{answer-id}";

    @Test
    void postAnswer() throws Exception {

        //given
        AnswerDto answerPost = new AnswerDto(
                1L, 1L, "답변을 작성했다.");
        Answer answer = answerMapper.answerDtoToAnswer(answerPost);

        QuestionDto.Post questionPost = new QuestionDto.Post("제목을 작성했다.", "질문을 작성했다.", 1L);
        Question question = questionMapper.questionPostDtoToQuestion(questionPost);
        question.setId(1L);

        given(answerService.createAnswer(Mockito.any(Answer.class), eq(1L), eq(1L)))
                .willReturn(answer);

        String content = gson.toJson(answerPost);

        //when
        ResultActions actions =
                mockMvc.perform(
                        post(ANSWER_POST_URL,question.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is(startsWith("/questions/1"))));
    }

    @Test
    void patchAnswer() throws Exception {

        //given_patch
        AnswerPatchDto answerPatch = new AnswerPatchDto(
                1L, 1L, "답변을 수정했다."
        );
        Answer answer = answerMapper.answerPatchDtoToAnswer(answerPatch);

        String patchContent = gson.toJson(answerPatch);

        given(answerService.updateAnswer(Mockito.any(Answer.class), eq(1L), eq(1L)))
                .willReturn(answer);

        //when
        ResultActions actions =
                mockMvc.perform(
                        patch(ANSWER_DEFAULT_URL,answer.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(patchContent)
                );

        //then
        actions
                .andExpect(status().isOk());
    }

    @Test
    void deleteAnswer() throws Exception {
        //given
        Long answerId = 1L;
        Long memberId = 1L;

        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("memberId", String.valueOf(memberId));

        doNothing().when(answerService).deleteAnswer(answerId);

        //when then
        mockMvc.perform(delete(ANSWER_DEFAULT_URL, answerId)
                        .param("memberId", String.valueOf(memberId)))
                .andExpect(status().isNoContent());
    }
}
