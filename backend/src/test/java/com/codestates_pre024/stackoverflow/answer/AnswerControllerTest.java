package com.codestates_pre024.stackoverflow.answer;

import com.codestates_pre024.stackoverflow.answer.dto.AnswerDto;
import com.codestates_pre024.stackoverflow.answer.entity.Answer;
import com.codestates_pre024.stackoverflow.answer.mapper.AnswerMapper;
import com.codestates_pre024.stackoverflow.answer.service.AnswerService;
import com.codestates_pre024.stackoverflow.question.dto.QuestionDto;
import com.codestates_pre024.stackoverflow.question.entity.Question;
import com.codestates_pre024.stackoverflow.question.mapper.QuestionMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    private final static String ANSWER_DEFAULT_URL = "/questions/{question-id}/answers";

    @Test
    void createAnswer() throws Exception {

        //given
        AnswerDto answerPost = new AnswerDto(
                1L, 1L, "답변을 작성했습니다.");
        Answer answer = answerMapper.answerDtoToAnswer(answerPost);

        QuestionDto.Post questionPost = new QuestionDto.Post();
        Question question = questionMapper.questionPostDtoToQuestion(questionPost);

        answer.setId(1L);
        question.setId(1L);

        given(answerService.createAnswer(Mockito.any(Answer.class), eq(1L), eq(1L)))
                .willReturn(answer);

        String content = gson.toJson(answerPost);

//        URI uri = UriComponentsBuilder.newInstance().path("/questions/{question-id}/answers").buildAndExpand(question.getId()).toUri();


        //when
        ResultActions actions =
                mockMvc.perform(
                        post(ANSWER_DEFAULT_URL,question.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );


        //then
        actions
                .andExpect(status().isCreated());
//                .andExpect(header().string("Location", is(startsWith("/questions/1/answers"))));
    }

    @Test
    void updateAnswer() throws Exception {

    }

    @Test
    void deleteAnswer() throws Exception {

    }
}
