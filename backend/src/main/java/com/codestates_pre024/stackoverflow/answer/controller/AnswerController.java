package com.codestates_pre024.stackoverflow.answer.controller;

import com.codestates_pre024.stackoverflow.answer.dto.AnswerPatchDto;
import com.codestates_pre024.stackoverflow.answer.entity.Answer;
import com.codestates_pre024.stackoverflow.answer.mapper.AnswerMapper;
import com.codestates_pre024.stackoverflow.answer.service.AnswerService;
import com.codestates_pre024.stackoverflow.global.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping(path = "/answers")
@RequiredArgsConstructor
@Slf4j
@Validated
public class AnswerController {
    private final static String ANSWER_DEFAULT_URL = "/answers";
    private final AnswerService answerService;
    private final AnswerMapper mapper;

    //answer 수정
    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer(@PathVariable("answer-id") @Positive Long id,
                                      @Valid @RequestBody AnswerPatchDto answerPatchDto) {
        answerPatchDto.setAnswerId(id);

        Answer updateAnswer = answerService.updateAnswer(
                mapper.answerPatchDtoToAnswer(answerPatchDto), answerPatchDto.getMemberId(), answerPatchDto.getAnswerId());

        //response data 부분 어떻게 처리할 건지 프론트 분들과 논의 후 확정 예정
        ApiResponse response = new ApiResponse(HttpStatus.OK, "SUCCESS",
                mapper.answerToAnswerResponseDto(updateAnswer));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //answer 삭제
    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") @Positive long id,
                                       @Positive @RequestParam Long memberId) {
        answerService.deleteAnswer(id, memberId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
