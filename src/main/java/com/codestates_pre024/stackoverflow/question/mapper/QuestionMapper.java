package com.codestates_pre024.stackoverflow.question.mapper;

import com.codestates_pre024.stackoverflow.question.dto.QuestionDto;
import com.codestates_pre024.stackoverflow.question.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

//@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface QuestionMapper {
    @Mapping(source = "questionId", target = "question.questionId")
    Question QuestionPostDtoToQuestion(QuestionDto.Post requestBody);
    Question QuestionPatchDtoToQuestion(QuestionDto.Patch requestBody);
    QuestionDto.Response QuestionToQuestionResponseDto(Question question);
}
