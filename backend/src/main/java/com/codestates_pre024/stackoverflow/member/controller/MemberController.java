package com.codestates_pre024.stackoverflow.member.controller;

import com.codestates_pre024.stackoverflow.global.utils.ApiResponse;
import com.codestates_pre024.stackoverflow.global.utils.UriMaker;
import com.codestates_pre024.stackoverflow.member.dto.MemberPatchDto;
import com.codestates_pre024.stackoverflow.member.dto.SignupDto;
import com.codestates_pre024.stackoverflow.member.entity.Member;
import com.codestates_pre024.stackoverflow.member.mapper.MemberMapper;
import com.codestates_pre024.stackoverflow.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/members")
//@Validated 얘때문에 안 됨 -- 트러블슈팅 예정 ^_^ (2023.02.20 강지은)
@RequiredArgsConstructor
public class MemberController {
    private final static String MEMBER_DEFAULT_URL = "/members";
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    @PostMapping
    private ResponseEntity createMember( @Valid @RequestBody SignupDto signupDto){

        Member member = memberService.createMember(memberMapper.SignupDtoToMember(signupDto));

        ApiResponse response = new ApiResponse(HttpStatus.CREATED, "CREATED");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{member-id}")
    private ResponseEntity getMember(@PathVariable("member-id") @Min(1) Long id){

        Member member = memberService.getMember(id);

        ApiResponse response = new ApiResponse(HttpStatus.OK, "SUCCESS",
                memberMapper.MemberToMemberResponseDto(member));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping
    private ResponseEntity getMembers(){

        List<Member> allMembers = memberService.getMembers();

        ApiResponse response = new ApiResponse(HttpStatus.OK, "SUCCESS",
                memberMapper.MemberListToMemberResponseDtoList(allMembers));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("{member-id}")
    private ResponseEntity updateMember(@PathVariable("member-id") @Min(1) Long id,
                              @Valid @RequestBody MemberPatchDto memberPatchDto){
        memberPatchDto.setId(id);
        Member updateMember = memberService.updateMemberMyPage(memberMapper.MemberPatchDtoToMember(memberPatchDto));

        URI uri = UriMaker.getUri(MEMBER_DEFAULT_URL,updateMember.getId());
        ApiResponse response = new ApiResponse(HttpStatus.OK, "UPDATED", uri);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("{member-id}")
    private ResponseEntity deleteMember(@PathVariable("member-id") @Min(1) Long id){

        memberService.deleteMember(id);

        ApiResponse response = new ApiResponse(HttpStatus.NO_CONTENT, "DELETED");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
