package com.codestates_pre024.stackoverflow.answer.controller;

import com.codestates_pre024.stackoverflow.answer.dto.AnswerDto;
import com.codestates_pre024.stackoverflow.answer.dto.AnswerPatchDto;
import com.codestates_pre024.stackoverflow.answer.entity.Answer;
import com.codestates_pre024.stackoverflow.answer.mapper.AnswerMapper;
import com.codestates_pre024.stackoverflow.answer.service.AnswerService;
import com.codestates_pre024.stackoverflow.global.utils.ApiResponse;
import com.codestates_pre024.stackoverflow.global.utils.UriMaker;
import com.codestates_pre024.stackoverflow.member.service.MemberService;
import com.codestates_pre024.stackoverflow.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/questions")
@RequiredArgsConstructor
@Slf4j
//@Validated
public class AnswerController {
    private final static String ANSWER_DEFAULT_URL = "/questions";
    private final AnswerService answerService;
    private final AnswerMapper mapper;

    //answer 등록
    @PostMapping("/{question-id}/answers")
    private ResponseEntity postAnswer(@PathVariable("question-id") @Positive Long questionId,
                                      @Valid @RequestBody AnswerDto answerDto) {
        answerDto.setQuestionId(questionId);

        Answer createAnswer = answerService.createAnswer(
                mapper.answerDtoToAnswer(answerDto), answerDto.getMemberId(), answerDto.getQuestionId());

        URI uri = UriMaker.getUri(ANSWER_DEFAULT_URL, createAnswer.getId());

        ApiResponse response = new ApiResponse(HttpStatus.CREATED, "CREATED", uri);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //answer 수정
    @PatchMapping("/{question-id}/answers/{answer-id}")
    public ResponseEntity patchAnswer(@PathVariable("question-id") @Positive Long questionId,
                                      @PathVariable("answer-id") @Positive Long id,
                                      @Valid @RequestBody AnswerPatchDto answerPatchDto) {
        answerPatchDto.setAnswerId(id);

        Answer updateAnswer = answerService.updateAnswer(
                mapper.answerPatchDtoToAnswer(answerPatchDto), answerPatchDto.getMemberId(), answerPatchDto.getAnswerId());

        URI uri = UriMaker.getUri(ANSWER_DEFAULT_URL, updateAnswer.getId());

        ApiResponse response = new ApiResponse(HttpStatus.OK, "SUCCESS", uri);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //    answer 가져오기(테스트용)
    @GetMapping("/{question-id}/answers/{answer-id}")
    public ResponseEntity getAnswer(@PathVariable("question-id") @Positive Long questionId,
                                    @PathVariable("answer-id") @Positive Long id) {

        Answer getAnswer = answerService.getAnswer(id);

        ApiResponse response = new ApiResponse(HttpStatus.OK, "SUCCESS",
                mapper.answerToAnswerResponseDto(getAnswer));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //한 개의 질문에 해당하는 answer 가져오기
    @GetMapping("/{question-id}/answers")
    public ResponseEntity getAnswers(@PathVariable("question-id") @Positive Long questionId) {

        List<Answer> getAnswers = answerService.findAnswers(questionId);

        ApiResponse response = new ApiResponse(HttpStatus.OK, "SUCCESS",
                mapper.answerListToAnswerResponseDtoList(getAnswers));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //answer 삭제
    @DeleteMapping("/{question-id}/answers/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("question-id") @Positive long questionId,
                                       @PathVariable("answer-id") @Positive long id,
                                       @Positive @RequestParam long memberId) {
        answerService.deleteAnswer(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
