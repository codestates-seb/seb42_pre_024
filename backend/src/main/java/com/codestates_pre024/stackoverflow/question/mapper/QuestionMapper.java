package com.codestates_pre024.stackoverflow.question.mapper;

import com.codestates_pre024.stackoverflow.question.dto.QuestionDto;
import com.codestates_pre024.stackoverflow.question.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question questionPostDtoToQuestion(QuestionDto.Post postDto);
    Question questionPatchDtoToQuestion(QuestionDto.Patch patchDto);
    QuestionDto.Response questionToQuestionResponseDto(Question question);
    List<QuestionDto.listResponse> questionsToQuestionResponseDto(List<Question> questions);
    QuestionDto.myPageResponse questionToMyPageResponseDto(Question question);
}
