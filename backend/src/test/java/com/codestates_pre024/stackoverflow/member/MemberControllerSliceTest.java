package com.codestates_pre024.stackoverflow.member;


import com.codestates_pre024.stackoverflow.global.utils.ApiResponse;
import com.codestates_pre024.stackoverflow.global.utils.UriMaker;
import com.codestates_pre024.stackoverflow.member.dto.MemberPatchDto;
import com.codestates_pre024.stackoverflow.member.dto.MemberResponseDto;
import com.codestates_pre024.stackoverflow.member.dto.SignupDto;
import com.codestates_pre024.stackoverflow.member.entity.Member;
import com.codestates_pre024.stackoverflow.member.mapper.MemberMapper;
import com.codestates_pre024.stackoverflow.member.service.MemberService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import java.net.URI;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerSliceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MemberService memberService;

    private final static String MEMBER_DEFAULT_URL = "/members";

    @Test
    @DisplayName("[컨트롤러] CreateMember")
    void createMember() throws Exception {

        //given
        String name = "testUser";
        String email = "test@mail.com";
        String password = "Te12Pw34";

        SignupDto signupDto = SignupDto.builder().name(name).email(email).password(password).build();
        String content = gson.toJson(signupDto);

        given(memberService.createMember(Mockito.any(Member.class))).willReturn(new Member());

        //when
        MvcResult result = mockMvc.perform(
                post(MEMBER_DEFAULT_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andReturn();

        //then
        ApiResponse<?> response = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<ApiResponse<?>>(){});
        assertEquals("201", response.getCode());
        assertEquals("CREATED", response.getMessage());
        assertEquals(null, response.getData());

    }

    @Test
    @DisplayName("[컨트롤러] GetMember")
    void getMember() throws Exception {

        //given
        Long id  = 1L;
        given(memberService.getMember(Mockito.any(Long.class))).willReturn(new Member());

        //when
        MvcResult result = mockMvc.perform(
                        get(MEMBER_DEFAULT_URL+"/"+id)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        //then
        ApiResponse<MemberResponseDto> response = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<ApiResponse<MemberResponseDto>>(){});
        assertEquals("200", response.getCode());
        assertEquals("SUCCESS", response.getMessage());
        assertEquals(MemberResponseDto.class, response.getData().getClass());
    }
    @Test
    @DisplayName("[컨트롤러] UpdateMember")
    void updateMember() throws Exception {

        //given
        Long id  = 1L;
        String name = "coco";
        String aboutMe = "안녕하세요? 코코입니다.";
        URI uri = UriMaker.getUri(MEMBER_DEFAULT_URL, id);

        MemberPatchDto memberPatchDto = MemberPatchDto.builder()
                .id(id).name(name).aboutMe(aboutMe).build();

        given(memberService.updateMemberMyPage(Mockito.any(Member.class))).willReturn(new Member());

        String content = gson.toJson(memberPatchDto);
        String uriString = gson.toJson(uri);

        //when
        MvcResult result = mockMvc.perform(
                        patch("/members/"+id)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content))
                .andReturn();

        //then
        ApiResponse<?> response = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<ApiResponse<?>>(){});
        assertEquals("200", response.getCode());
        assertEquals("UPDATED", response.getMessage());
    }

    @Test
    @DisplayName("[컨트롤러] DeleteMember")
    void deleteMember() throws Exception {

        //given
        Long id  = 1L;

        Mockito.doNothing().when(memberService).deleteMember(any(Long.class));

        //when
        MvcResult result = mockMvc.perform(
                        delete(MEMBER_DEFAULT_URL+"/"+id)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        //then
        ApiResponse<?> response = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<ApiResponse<?>>(){});
        assertEquals("204", response.getCode());
        assertEquals("DELETED", response.getMessage());

    }

}

