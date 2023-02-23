package com.codestates_pre024.stackoverflow.member.mapper;

import com.codestates_pre024.stackoverflow.member.dto.MemberPatchDto;
import com.codestates_pre024.stackoverflow.member.dto.MemberResponseDto;
import com.codestates_pre024.stackoverflow.member.dto.SignupDto;
import com.codestates_pre024.stackoverflow.member.dto.WriterResponse;
import com.codestates_pre024.stackoverflow.member.entity.Member;
import com.codestates_pre024.stackoverflow.question.dto.QuestionDto;
import com.codestates_pre024.stackoverflow.question.entity.Question;
import com.codestates_pre024.stackoverflow.question.mapper.QuestionMapper;
import com.codestates_pre024.stackoverflow.question.service.QuestionService;
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
    default MemberResponseDto MemberToMemberResponseDto(Member member, QuestionService questionService, QuestionMapper questionMapper) {
        if ( member == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String aboutMe = null;
        String profileImage = null;
        List<QuestionDto.myPageResponse> questionDtoList = null;

        id = member.getId();
        name = member.getName();
        aboutMe = member.getAboutMe();
        profileImage = member.getProfileImage();
        List<Question> questions = questionService.findQuestionsOfMember(id);

        questionDtoList = questionMapper.questionListToMyPageResponseDtoList(questions);


        MemberResponseDto memberResponseDto = new MemberResponseDto( id, name, aboutMe, profileImage, questionDtoList );

        return memberResponseDto;
    }

    List<MemberResponseDto> MemberListToMemberResponseDtoList(List<Member> allMembers);
    WriterResponse MemberToWriterResponse(Member member);
}
