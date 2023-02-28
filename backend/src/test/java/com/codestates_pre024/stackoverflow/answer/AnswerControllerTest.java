package com.codestates_pre024.stackoverflow.answer;

import com.codestates_pre024.stackoverflow.answer.dto.AnswerDto;
import com.codestates_pre024.stackoverflow.answer.dto.AnswerPatchDto;
import com.codestates_pre024.stackoverflow.answer.dto.AnswerResponseDto;
import com.codestates_pre024.stackoverflow.answer.entity.Answer;
import com.codestates_pre024.stackoverflow.answer.mapper.AnswerMapper;
import com.codestates_pre024.stackoverflow.answer.service.AnswerService;
import com.codestates_pre024.stackoverflow.member.dto.SignupDto;
import com.codestates_pre024.stackoverflow.member.dto.WriterResponse;
import com.codestates_pre024.stackoverflow.member.entity.Member;
import com.codestates_pre024.stackoverflow.member.mapper.MemberMapper;
import com.codestates_pre024.stackoverflow.member.service.MemberService;
import com.codestates_pre024.stackoverflow.question.dto.QuestionDto;
import com.codestates_pre024.stackoverflow.question.entity.Question;
import com.codestates_pre024.stackoverflow.question.mapper.QuestionMapper;
import com.codestates_pre024.stackoverflow.question.service.QuestionService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;
import java.util.List;

import static com.codestates_pre024.stackoverflow.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.codestates_pre024.stackoverflow.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs
public class AnswerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;
    @MockBean
    private AnswerMapper answerMapper;
    @MockBean
    private QuestionMapper questionMapper;
    @MockBean
    private MemberMapper memberMapper;
    @MockBean
    private AnswerService answerService;
    @MockBean
    private QuestionService questionService;
    @MockBean
    private MemberService memberService;
    private final static String ANSWER_POST_URL = "/questions/{question-id}/answers";

    private final static String ANSWER_DEFAULT_URL = "/answers/{answer-id}";

    @Test
    void postAnswer() throws Exception {
        //given
        SignupDto signupDto = new SignupDto(
                "coco", "coco@gmail.com", "cococo1234");
        Member member = memberMapper.SignupDtoToMember(signupDto);
        given(memberService.createMember(Mockito.any(Member.class)))
                .willReturn(member);

        QuestionDto.Post questionPost = new QuestionDto.Post("제목을 작성했다.", "질문을 작성했다.", 1L);
        Question question = questionMapper.questionPostDtoToQuestion(questionPost);
        given(questionService.createQuestion(Mockito.any(Question.class), eq(1L)))
                .willReturn(question);

        AnswerDto answerPost = new AnswerDto(
                1L, 1L, "답변을 작성했다.");
        Answer answer = answerMapper.answerDtoToAnswer(answerPost);
        given(answerService.createAnswer(Mockito.any(Answer.class), eq(1L), eq(1L)))
                .willReturn(answer);

        String content = gson.toJson(answerPost);

        //when
        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.post(ANSWER_POST_URL,1)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        //then
        actions
                .andExpect(status().isCreated())
                .andDo(document(
                        "post-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("contents").type(JsonFieldType.STRING).description("답변 내용")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("code").type(JsonFieldType.STRING).description("상태 코드"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("상태 코드 메세지"),
                                        fieldWithPath("data").type(JsonFieldType.STRING).description("응답 데이터").optional()
                                )
                        ),
                        pathParameters(
                                parameterWithName("question-id").description("질문 식별자")
                        )
                ));
    }

    @Test
    void patchAnswer() throws Exception {

        //given_patch
        Long answerId = 1L;
        AnswerPatchDto answerPatch = new AnswerPatchDto(
                1L, 1L, "답변을 수정했다.");
        String patchContent = gson.toJson(answerPatch);

//        Answer answer = answerMapper.answerPatchDtoToAnswer(Mockito.any(answerPatch.class));

        AnswerResponseDto responseDto = new AnswerResponseDto(
                1L, "답변을 수정했다.", LocalDateTime.now(), LocalDateTime.now(),
                new WriterResponse(1L, "회원 이름", "회원 이미지"));

        given(answerMapper.answerPatchDtoToAnswer(Mockito.any(AnswerPatchDto.class)))
                .willReturn(new Answer());
        given(answerService.updateAnswer(Mockito.any(Answer.class), eq(1L), eq(1L)))
                .willReturn(new Answer());
        given(answerMapper.answerToAnswerResponseDto(
                Mockito.any(Answer.class)))
                .willReturn(responseDto);

        //when
        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders
                                .patch(ANSWER_DEFAULT_URL,1)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(patchContent)
                );

        //then
        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "patch-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("contents").type(JsonFieldType.STRING).description("수정할 답변 내용")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("code").type(JsonFieldType.STRING).description("상태 코드"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("상태 코드 메세지"),
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터").optional(),
                                        fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        fieldWithPath("data.contents").type(JsonFieldType.STRING).description("수정된 답변 내용"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("답변을 등록한 시간"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("답변을 수정한 시간"),
                                        fieldWithPath("data.member.id").type(JsonFieldType.NUMBER).description("답변을 등록한 회원 식별자"),
                                        fieldWithPath("data.member.name").type(JsonFieldType.STRING).description("답변을 등록한 회원 이름"),
                                        fieldWithPath("data.member.profileImage").type(JsonFieldType.STRING).description("답변을 등록한 회원의 프로필 사진")
                                )
                        ),
                        pathParameters(
                                parameterWithName("answer-id").description("답변 식별자")
                        )
                ));
    }

    @Test
    void deleteAnswer() throws Exception {
        //given
        Long answerId = 1L;
        Long memberId = 1L;

        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("memberId", String.valueOf(memberId));

        doNothing().when(answerService).deleteAnswer(answerId, memberId);

        //when then
        mockMvc.perform(RestDocumentationRequestBuilders.delete(ANSWER_DEFAULT_URL, answerId)
                        .param("memberId", String.valueOf(memberId)))
                .andExpect(status().isNoContent())
                .andDo(document(
                        "delete-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("answer-id").description("답변 식별자")
                        )
                ));
    }
}
