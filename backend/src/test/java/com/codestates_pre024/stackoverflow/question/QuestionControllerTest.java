package com.codestates_pre024.stackoverflow.question;

import com.codestates_pre024.stackoverflow.answer.dto.AnswerResponseDto;
import com.codestates_pre024.stackoverflow.answer.mapper.AnswerMapper;
import com.codestates_pre024.stackoverflow.member.dto.WriterResponse;
import com.codestates_pre024.stackoverflow.member.mapper.MemberMapper;
import com.codestates_pre024.stackoverflow.question.dto.QuestionDto;
import com.codestates_pre024.stackoverflow.question.entity.Question;
import com.codestates_pre024.stackoverflow.question.mapper.QuestionMapper;
import com.codestates_pre024.stackoverflow.question.service.QuestionService;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.codestates_pre024.stackoverflow.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.codestates_pre024.stackoverflow.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class QuestionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private QuestionMapper mapper;

    @WithMockUser(authorities = "ROLE_USER")
    @Test
    void postQuestionTest() throws Exception {
        // given
        QuestionDto.Post post = new QuestionDto.Post("질문 제목", "질문 내용", 1L);
        String content = gson.toJson(post);

        given(mapper.questionPostDtoToQuestion(Mockito.any(QuestionDto.Post.class))).willReturn(new Question());

        Question mockResultQuestion = new Question();
        mockResultQuestion.setId(1L);
        given(questionService.createQuestion(Mockito.any(Question.class), eq(1L))).willReturn(new Question());

        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/questions")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is(startsWith("/questions/"))))
                .andDo(document(
                        "post-question",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("질문 제목"),
                                        fieldWithPath("contents").type(JsonFieldType.STRING).description("질문 내용"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자")
                                )
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.LOCATION).description("Location header. 등록된 리소스의 URI")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("code").type(JsonFieldType.STRING).description("질문 식별자"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("상태 코드 메시지"),
                                        fieldWithPath("data").type(JsonFieldType.STRING).description("결과 데이터").optional()
                                )
                        )
                ));
    }

    @WithMockUser(authorities = "ROLE_USER")
    @Test
    void patchQuestionTest() throws Exception {
        // given
        Long questionId = 1L;
        QuestionDto.Patch patch = new QuestionDto.Patch(1L, "질문 제목 수정", "질문 내용 수정", 1L);
        String content = gson.toJson(patch);

        QuestionDto.Response responseDto = new QuestionDto.Response(1L, "질문 제목", "질문 내용",
                LocalDateTime.now(), LocalDateTime.now(), new WriterResponse(1L, "회원 이름", "회원 이미지"), null);

        given(mapper.questionPatchDtoToQuestion(Mockito.any(QuestionDto.Patch.class))).willReturn(new Question());
        given(questionService.updateQuestion(Mockito.any(Question.class), Mockito.anyLong())).willReturn(new Question());
        given(mapper.questionToQuestionResponseDto(
                Mockito.any(Question.class), Mockito.any(MemberMapper.class), Mockito.any(AnswerMapper.class))).willReturn(responseDto);

        // when
        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders
                                .patch("/questions/{question-id}", questionId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "patch-question",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("question-id").description("질문 식별자 ID")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("질문 식별자").ignored(),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("질문 제목").optional(),
                                        fieldWithPath("contents").type(JsonFieldType.STRING).description("질문 내용").optional(),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("code").type(JsonFieldType.STRING).description("질문 식별자"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("상태 코드 메시지"),
                                        fieldWithPath("data").type(JsonFieldType.STRING).description("결과 데이터").optional()
                                )
                        )
                ));
    }

    @Test
    void getQuestionTest() throws Exception {
        // given
        Long questionId = 1L;

        List<AnswerResponseDto> answers = List.of(
                new AnswerResponseDto(1L, "답변1", LocalDateTime.now(), LocalDateTime.now(),
                        new WriterResponse(1L, "회원 이름1", "회원 이미지1")),
                new AnswerResponseDto(2L, "답변2", LocalDateTime.now(), LocalDateTime.now(),
                        new WriterResponse(2L, "회원 이름2", "회원 이미지2"))
        );

        QuestionDto.Response response = new QuestionDto.Response(1L, "질문 제목", "질문 내용",
                LocalDateTime.now(), LocalDateTime.now(), new WriterResponse(1L, "회원 이름1", "회원 이미지1"),
                answers);

        given(questionService.findQuestion(Mockito.anyLong())).willReturn(new Question());
        given(mapper.questionToQuestionResponseDto(
                Mockito.any(Question.class), Mockito.any(MemberMapper.class), Mockito.any(AnswerMapper.class)))
                .willReturn(response);

        // when
        ResultActions actions = mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get("/questions/{question-id}", questionId)
                        .accept(MediaType.APPLICATION_JSON));

        // then
        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "get-question",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                        parameterWithName("question-id").description("질문 식별자 ID")
                        ),
                        responseFields(
                                List.of(fieldWithPath("code").type(JsonFieldType.STRING).description("응답 코드"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메시지"),
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터").optional(),
                                        fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("질문 제목"),
                                        fieldWithPath("data.contents").type(JsonFieldType.STRING).description("질문 내용"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("질문 생성 날짜"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("질문 수정 날짜"),
                                        fieldWithPath("data.member").type(JsonFieldType.OBJECT).description("질문회원 데이터").optional(),
                                        fieldWithPath("data.member.id").type(JsonFieldType.NUMBER).description("질문회원 식별자"),
                                        fieldWithPath("data.member.name").type(JsonFieldType.STRING).description("질문회원 이름"),
                                        fieldWithPath("data.member.profileImage").type(JsonFieldType.STRING).description("질문회원 이미지"),
                                        fieldWithPath("data.answers").type(JsonFieldType.ARRAY).description("답변 데이터").optional(),
                                        fieldWithPath("data.answers[].id").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        fieldWithPath("data.answers[].contents").type(JsonFieldType.STRING).description("답변 내용"),
                                        fieldWithPath("data.answers[].createdAt").type(JsonFieldType.STRING).description("답변 생성 날짜"),
                                        fieldWithPath("data.answers[].modifiedAt").type(JsonFieldType.STRING).description("답변 수정 날짜"),
                                        fieldWithPath("data.answers[].member").type(JsonFieldType.OBJECT).description("답변회원 데이터").optional(),
                                        fieldWithPath("data.answers[].member.id").type(JsonFieldType.NUMBER).description("답변회원 식별자"),
                                        fieldWithPath("data.answers[].member.name").type(JsonFieldType.STRING).description("답변회원 이름"),
                                        fieldWithPath("data.answers[].member.profileImage").type(JsonFieldType.STRING).description("답변회원 이미지")
                                )
                        )
                ));
    }

    @Test
    void getQuestionsTest() throws Exception {
        // given
        String page = "1";
        String size = "10";
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", page);
        queryParams.add("size", size);

        Question question1 = new Question("제목1", "내용1");

        Question question2 = new Question("제목2", "내용2");

        Page<Question> pageQuestions =
                new PageImpl<>(List.of(question1, question2),
                        PageRequest.of(0, 10, Sort.by("id").descending()), 2);

        List<QuestionDto.listResponse> responses = List.of(
                new QuestionDto.listResponse(1L, "질문 제목1", "질문 내용2",
                        LocalDateTime.now(), LocalDateTime.now(), new WriterResponse(1L, "회원 이름1", "회원 이미지2")),
                new QuestionDto.listResponse(2L, "질문 제목2", "질문 내용2",
                        LocalDateTime.now(), LocalDateTime.now(), new WriterResponse(2L, "회원 이름2", "회원 이미지2"))
        );

        given(questionService.findQuestions(Mockito.anyInt(), Mockito.anyInt())).willReturn(pageQuestions);
        given(mapper.questionsToQuestionResponseDto(Mockito.anyList())).willReturn(responses);

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
                .andDo(
                        document(
                                "get-questions",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                requestParameters(
                                        List.of(
                                                parameterWithName("page").description("Page 번호"),
                                                parameterWithName("size").description("Page Size")
                                        )
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("code").type(JsonFieldType.STRING).description("응답 코드"),
                                                fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메시지"),
                                                fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터").optional(),
                                                fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                                fieldWithPath("data[].title").type(JsonFieldType.STRING).description("질문 제목"),
                                                fieldWithPath("data[].contents").type(JsonFieldType.STRING).description("질문 내용"),
                                                fieldWithPath("data[].createdAt").type(JsonFieldType.STRING).description("질문 생성 날짜"),
                                                fieldWithPath("data[].modifiedAt").type(JsonFieldType.STRING).description("질문 수정 날짜"),
                                                fieldWithPath("data[].member").type(JsonFieldType.OBJECT).description("질문회원 데이터").optional(),
                                                fieldWithPath("data[].member.id").type(JsonFieldType.NUMBER).description("질문회원 식별자"),
                                                fieldWithPath("data[].member.name").type(JsonFieldType.STRING).description("질문회원 이름"),
                                                fieldWithPath("data[].member.profileImage").type(JsonFieldType.STRING).description("질문회원 이미지"),
                                                fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 정보"),
                                                fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("페이지 번호"),
                                                fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지 사이즈"),
                                                fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("전체 건 수"),
                                                fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수")
                                        )
                                )
                        )
                )
                .andReturn();

        List list = JsonPath.parse(result.getResponse().getContentAsString()).read("$.data");

        assertThat(list.size(), is(2));
    }

    @WithMockUser(authorities = "ROLE_USER")
    @Test
    void deleteQuestionTest() throws Exception {
        // given
        Long questionId = 1L;

        doNothing().when(questionService).deleteQuestion(questionId);

        // when
        ResultActions actions = mockMvc.perform(
                RestDocumentationRequestBuilders
                        .delete("/questions/{question-id}", questionId)
        );

         // when
        actions
                .andExpect(status().isNoContent())
                .andDo(
                        document(
                                "delete-question",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                pathParameters(
                                        Arrays.asList(parameterWithName("question-id").description("질문 식별자 ID"))
                                )
                        )
                );
    }
}
