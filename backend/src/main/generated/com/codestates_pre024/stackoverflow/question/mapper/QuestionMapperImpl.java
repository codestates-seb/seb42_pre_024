package com.codestates_pre024.stackoverflow.question.mapper;

import com.codestates_pre024.stackoverflow.answer.dto.AnswerResponseDto;
import com.codestates_pre024.stackoverflow.question.dto.QuestionDto.Patch;
import com.codestates_pre024.stackoverflow.question.dto.QuestionDto.Post;
import com.codestates_pre024.stackoverflow.question.dto.QuestionDto.Response;
import com.codestates_pre024.stackoverflow.question.entity.Question;
import java.time.LocalDateTime;
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
public class QuestionMapperImpl implements QuestionMapper {

    @Override
    public Question questionPostDtoToQuestion(Post postDto) {
        if ( postDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setTitle( postDto.getTitle() );
        question.setContents( postDto.getContents() );

        return question;
    }

    @Override
    public Question questionPatchDtoToQuestion(Patch patchDto) {
        if ( patchDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setQuestionId( patchDto.getQuestionId() );
        question.setTitle( patchDto.getTitle() );
        question.setContents( patchDto.getContents() );

        return question;
    }

    @Override
    public Response questionToQuestionResponseDto(Question question) {
        if ( question == null ) {
            return null;
        }

        Long questionId = null;
        String title = null;
        String contents = null;
        LocalDateTime modifiedAt = null;

        questionId = question.getQuestionId();
        title = question.getTitle();
        contents = question.getContents();
        modifiedAt = question.getModifiedAt();

        LocalDateTime createAt = null;
        List<AnswerResponseDto> answers = null;

        Response response = new Response( questionId, title, contents, createAt, modifiedAt, answers );

        return response;
    }

    @Override
    public List<Response> questionsToQuestionResponseDto(List<Question> questions) {
        if ( questions == null ) {
            return null;
        }

        List<Response> list = new ArrayList<Response>( questions.size() );
        for ( Question question : questions ) {
            list.add( questionToQuestionResponseDto( question ) );
        }

        return list;
    }
}
