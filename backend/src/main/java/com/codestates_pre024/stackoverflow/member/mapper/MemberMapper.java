package com.codestates_pre024.stackoverflow.member.mapper;

import com.codestates_pre024.stackoverflow.member.dto.MemberPatchDto;
import com.codestates_pre024.stackoverflow.member.dto.MemberResponseDto;
import com.codestates_pre024.stackoverflow.member.dto.SignupDto;
import com.codestates_pre024.stackoverflow.member.dto.WriterResponse;
import com.codestates_pre024.stackoverflow.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    default Member SignupDtoToMember(SignupDto signupDto) {
        if (signupDto == null) {
            return null;
        } else {
            return Member.CreateNewMember()
                    .email(signupDto.getEmail())
                    .name(signupDto.getName())
                    .password(signupDto.getPassword())
                    .build();
        }
    }

    default Member MemberPatchDtoToMember(MemberPatchDto memberPatchDto) {
        if (memberPatchDto == null) {
            return null;
        } else {
            return Member.UpdateMember()
                    .id(memberPatchDto.getId())
                    .name(memberPatchDto.getName())
                    .aboutMe(memberPatchDto.getAboutMe())
                    .build();
        }
    }
    MemberResponseDto MemberToMemberResponseDto(Member member);
    List<MemberResponseDto> MemberListToMemberResponseDtoList(List<Member> allMembers);
    WriterResponse MemberToWriterResponse(Member member);
}
