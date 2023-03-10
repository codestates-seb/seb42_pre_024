package com.codestates_pre024.stackoverflow.member;

import com.codestates_pre024.stackoverflow.member.dto.MemberPatchDto;
import com.codestates_pre024.stackoverflow.member.dto.MemberResponseDto;
import com.codestates_pre024.stackoverflow.member.dto.SignupDto;
import com.codestates_pre024.stackoverflow.member.entity.Member;
import com.codestates_pre024.stackoverflow.member.mapper.MemberMapper;
import com.codestates_pre024.stackoverflow.member.service.MemberService;
import com.codestates_pre024.stackoverflow.question.dto.QuestionDto;
import com.codestates_pre024.stackoverflow.question.mapper.QuestionMapper;
import com.codestates_pre024.stackoverflow.question.service.QuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.ResultActions;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.codestates_pre024.stackoverflow.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.codestates_pre024.stackoverflow.util.ApiDocumentUtils.getResponsePreProcessor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class MemberControllerSliceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberMapper mapper;


    private final static String MEMBER_DEFAULT_URL = "/members";


    @Test
    @DisplayName("CreateMember")
    void createMember() throws Exception {

        //given
        String name = "testUser";
        String email = "test@mail.com";
        String password = "Te12Pw34";

        SignupDto signupDto = SignupDto.builder().name(name).email(email).password(password).build();
        String content = gson.toJson(signupDto);

        given(memberService.createMember(Mockito.any(Member.class))).willReturn(new Member());

        //when
        ResultActions actions = mockMvc.perform(
                        post(MEMBER_DEFAULT_URL)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content));

        actions
                .andExpect(status().isCreated())
                .andDo(document(
                        "post-member",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("?????????"),
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("?????????"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("????????????")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("code").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("?????? ?????? ?????????"),
                                        fieldWithPath("data").type(JsonFieldType.STRING).description("?????? ?????????").optional()
                                )
                        )
                ));
    }


    @Test
    @DisplayName("GetMember(mypage)")
    void getMember() throws Exception {

        //given

        List<QuestionDto.myPageResponse> questions = List.of(
                new QuestionDto.myPageResponse(1L,"????????????1",LocalDateTime.now()),
                new QuestionDto.myPageResponse(2L,"????????????2",LocalDateTime.now())
        );

        MemberResponseDto memberMyPage = new MemberResponseDto(1L,"?????? ??????", "???????????????!",
                "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/f38066b8-ad73-4e02-8bb2-8d6a69110a9c/7839bcaccc3359d426e4b9bda6598fe6.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230221%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230221T030701Z&X-Amz-Expires=86400&X-Amz-Signature=6a33b1e1ab867f28d08f35cd0050503634a6b6b6ea8ef788520b97ea37f99911&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%227839bcaccc3359d426e4b9bda6598fe6.png%22&x-id=GetObject",
                questions);

        Long id  = 1L;

        given(memberService.getMember(Mockito.any(Long.class))).willReturn(new Member());
        given(mapper.MemberToMemberResponseDto(
                Mockito.any(Member.class), Mockito.any(QuestionService.class), Mockito.any(QuestionMapper.class)))
                .willReturn(memberMyPage);

        //when
        ResultActions actions = mockMvc.perform(
                RestDocumentationRequestBuilders.get(MEMBER_DEFAULT_URL+"/{member-id}",id)
                                .accept(MediaType.APPLICATION_JSON));

        //then
        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "get-member",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("member-id").description("?????? ?????????")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("code").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("?????? ?????? ?????????"),
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("?????? ?????????"),

                                        fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("data.name").type(JsonFieldType.STRING).description("?????? ?????????"),
                                        fieldWithPath("data.aboutMe").type(JsonFieldType.STRING).description("?????? ?????????"),
                                        fieldWithPath("data.profileImage").type(JsonFieldType.STRING).description("?????? ?????????????????? URL"),

                                        fieldWithPath("data.questions").type(JsonFieldType.ARRAY).description("????????? ????????? ?????? ?????????"),
                                        fieldWithPath("data.questions[].id").type(JsonFieldType.NUMBER).description("?????? ?????????"),
                                        fieldWithPath("data.questions[].title").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.questions[].createdAt").type(JsonFieldType.STRING).description("?????? ?????? ??????")
                                )
                        )
                ));
    }

    @WithMockUser(authorities = "ROLE_USER")
    @Test
    @DisplayName("UpdateMember")
    void updateMember() throws Exception {

        //given
        Long id  = 1L;
        String name = "coco";
        String aboutMe = "???????????????? ???????????????.";

        MemberPatchDto memberPatchDto = new MemberPatchDto(id,name,aboutMe);


        given(mapper.MemberPatchDtoToMember(Mockito.any(MemberPatchDto.class))).willReturn(new Member());
        given(memberService.updateMemberMyPage(Mockito.any(Member.class))).willReturn(new Member());

        String content = gson.toJson(memberPatchDto);

        //when
        ResultActions actions = mockMvc.perform(
                RestDocumentationRequestBuilders.
                        patch(MEMBER_DEFAULT_URL+"/{member-id}",id)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content));

        //then
        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "patch-member",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("member-id").description("?????? ?????????")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("?????? ?????????").ignored(),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("?????? ?????????").optional(),
                                        fieldWithPath("aboutMe").type(JsonFieldType.STRING).description("?????? ?????????").optional()
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("code").type(JsonFieldType.STRING).description("?????? ?????????"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("?????? ?????? ?????????"),
                                        fieldWithPath("data").type(JsonFieldType.STRING).description("?????? ?????????").optional()
                                )
                        )
                ));

    }

    @WithMockUser(authorities = "ROLE_USER")
    @Test
    @DisplayName("DeleteMember")
    void deleteMember() throws Exception {

        //given
        Long id  = 1L;

        Mockito.doNothing().when(memberService).deleteMember(any(Long.class));

        //when
        ResultActions actions = mockMvc.perform(
                RestDocumentationRequestBuilders.delete(MEMBER_DEFAULT_URL+"/{member-id}",id));

        //then
        actions
                .andExpect(status().isNoContent())
                .andDo(
                        document(
                                "delete-member",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                pathParameters(
                                        Arrays.asList(parameterWithName("member-id").description("?????? ?????????"))
                                )
                        )
                );
    }
}

