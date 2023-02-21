package com.codestates_pre024.stackoverflow.member.mapper;

import com.codestates_pre024.stackoverflow.member.dto.MemberResponseDto;
import com.codestates_pre024.stackoverflow.member.entity.Member;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-21T11:38:33+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public MemberResponseDto MemberToMemberResponseDto(Member member) {
        if ( member == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String aboutMe = null;

        id = member.getId();
        name = member.getName();
        aboutMe = member.getAboutMe();

        MemberResponseDto memberResponseDto = new MemberResponseDto( id, name, aboutMe );

        return memberResponseDto;
    }

    @Override
    public List<MemberResponseDto> MemberListToMemberResponseDtoList(List<Member> allMembers) {
        if ( allMembers == null ) {
            return null;
        }

        List<MemberResponseDto> list = new ArrayList<MemberResponseDto>( allMembers.size() );
        for ( Member member : allMembers ) {
            list.add( MemberToMemberResponseDto( member ) );
        }

        return list;
    }
}
