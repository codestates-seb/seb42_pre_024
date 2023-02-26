package com.codestates_pre024.stackoverflow.answer.mapper;

import com.codestates_pre024.stackoverflow.answer.dto.AnswerDto;
import com.codestates_pre024.stackoverflow.answer.dto.AnswerPatchDto;
import com.codestates_pre024.stackoverflow.answer.dto.AnswerResponseDto;
import com.codestates_pre024.stackoverflow.answer.entity.Answer;
import com.codestates_pre024.stackoverflow.member.entity.Member;
import com.codestates_pre024.stackoverflow.question.entity.Question;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    default Answer answerDtoToAnswer(AnswerDto answerDto) {
        if (answerDto == null ) {
            return null;
        } else {
            Member member = new Member();
            member.setId(answerDto.getMemberId());

            Question question = new Question();
            question.setId(answerDto.getQuestionId());

            Answer answer = new Answer();
            answer.setContents(answerDto.getContents());
            answer.setCreatedAt(LocalDateTime.now());
            answer.setMember(member);
            answer.setQuestion(question);

            return answer;
        }
    }

    default Answer answerPatchDtoToAnswer(AnswerPatchDto answerPatchDto) {
        Answer answer = new Answer();
        answer.setId(answerPatchDto.getAnswerId());
        answer.setContents(answerPatchDto.getContents());

        return answer;
    }

    AnswerResponseDto answerToAnswerResponseDto(Answer answer);

    List<AnswerResponseDto> answerListToAnswerResponseDtoList(List<Answer> answerList);
}
