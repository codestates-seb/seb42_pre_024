package com.codestates_pre024.stackoverflow.question.controller;

import com.codestates_pre024.stackoverflow.answer.dto.AnswerDto;
import com.codestates_pre024.stackoverflow.answer.entity.Answer;
import com.codestates_pre024.stackoverflow.answer.mapper.AnswerMapper;
import com.codestates_pre024.stackoverflow.answer.service.AnswerService;
import com.codestates_pre024.stackoverflow.global.utils.ApiResponse;
import com.codestates_pre024.stackoverflow.global.utils.MultiResponse;
import com.codestates_pre024.stackoverflow.global.utils.UriMaker;
import com.codestates_pre024.stackoverflow.member.mapper.MemberMapper;
import com.codestates_pre024.stackoverflow.question.mapper.QuestionMapper;
import com.codestates_pre024.stackoverflow.question.dto.QuestionDto;
import com.codestates_pre024.stackoverflow.question.entity.Question;
import com.codestates_pre024.stackoverflow.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/questions")
@Validated
@RequiredArgsConstructor
public class QuestionController {
    private final static String QUESTION_DEFAULT_URL = "/questions";
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final QuestionMapper mapper;
    private final MemberMapper memberMapper;
    private final AnswerMapper answerMapper;
    // 질문 등록
    @PostMapping
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.Post postDto) {
        questionService.createQuestion(mapper.questionPostDtoToQuestion(postDto), postDto.getMemberId());

//        URI uri = UriMaker.getUri(QUESTION_DEFAULT_URL, question.getQuestionId());

        ApiResponse response = new ApiResponse(HttpStatus.CREATED, "CREATED");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // {question-id}에 해당하는 질문 수정
    @PatchMapping("/{question-id}")
    public ResponseEntity patchQuestion(@PathVariable("question-id") @Min(1) Long questionId,
                                        @Valid @RequestBody QuestionDto.Patch patchDto) {
        patchDto.setId(questionId);

        Question updateQuestion = questionService.updateQuestion(mapper.questionPatchDtoToQuestion(patchDto));

        URI uri = UriMaker.getUri(QUESTION_DEFAULT_URL, updateQuestion.getId());

        ApiResponse response = new ApiResponse(HttpStatus.OK, "UPDATED", uri);

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(String.valueOf(uri)).body(response);
    }

    // {question-id}에 해당하는 질문+답변 조회
    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") @Min(1) Long questionId) {
        Question question = questionService.findQuestion(questionId);

        ApiResponse response = new ApiResponse(HttpStatus.OK, "SUCCESS",
                mapper.questionToQuestionResponseDto(question, memberMapper, answerMapper));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 전체 질문 조회
    @GetMapping
    public ResponseEntity getQuestions(@Positive @RequestParam int page,
                                       @Positive @RequestParam int size) {
        Page<Question> pageQuestions = questionService.findQuestions(page - 1, size);
        List<Question> questions = pageQuestions.getContent();

        MultiResponse response = new MultiResponse(HttpStatus.OK, "SUCCESS",
                mapper.questionsToQuestionResponseDto(questions), pageQuestions);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // {question-id}에 해당하는 질문 삭제
    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id") @Min(1) Long questionId) {
        questionService.deleteQuestion(questionId);

        ApiResponse response = new ApiResponse(HttpStatus.NO_CONTENT, "DELETED");

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    //답변 등록
    @PostMapping("/{question-id}/answers")
    public ResponseEntity postAnswer(@PathVariable("question-id") @Positive Long questionId,
                                      @Valid @RequestBody AnswerDto answerDto) {
        answerDto.setQuestionId(questionId);

        Answer createAnswer = answerService.createAnswer(
                answerMapper.answerDtoToAnswer(answerDto), answerDto.getMemberId(), answerDto.getQuestionId());

        URI uri = UriMaker.getUri(QUESTION_DEFAULT_URL, createAnswer.getId());

        ApiResponse response = new ApiResponse(HttpStatus.CREATED, "CREATED", uri);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
