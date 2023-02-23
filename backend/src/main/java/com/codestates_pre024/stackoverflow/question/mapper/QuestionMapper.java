package com.codestates_pre024.stackoverflow.question.mapper;

import com.codestates_pre024.stackoverflow.answer.dto.AnswerResponseDto;
import com.codestates_pre024.stackoverflow.answer.mapper.AnswerMapper;
import com.codestates_pre024.stackoverflow.member.dto.WriterResponse;
import com.codestates_pre024.stackoverflow.member.mapper.MemberMapper;
import com.codestates_pre024.stackoverflow.question.dto.QuestionDto;
import com.codestates_pre024.stackoverflow.question.entity.Question;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question questionPostDtoToQuestion(QuestionDto.Post postDto);
    Question questionPatchDtoToQuestion(QuestionDto.Patch patchDto);
    default QuestionDto.Response questionToQuestionResponseDto(Question question, MemberMapper memberMapper, AnswerMapper answerMapper) {
        if ( question == null && memberMapper == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        String contents = null;
        LocalDateTime createdAt = null;
        LocalDateTime modifiedAt = null;
        WriterResponse member = null;
        List<AnswerResponseDto> answers = null;
        if ( question != null ) {
            id = question.getId();
            title = question.getTitle();
            contents = question.getContents();
            createdAt = question.getCreatedAt();
            modifiedAt = question.getModifiedAt();
            member = memberMapper.MemberToWriterResponse( question.getMember() );
            answers = answerMapper.answerListToAnswerResponseDtoList( question.getAnswers() );
        }

        QuestionDto.Response response = new QuestionDto.Response( id, title, contents, createdAt, modifiedAt, member, answers );

        return response;
    }
    List<QuestionDto.listResponse> questionsToQuestionResponseDto(List<Question> questions);
    QuestionDto.myPageResponse questionToMyPageResponseDto(Question question);
    List<QuestionDto.myPageResponse> questionListToMyPageResponseDtoList(List<Question> questions);

}
