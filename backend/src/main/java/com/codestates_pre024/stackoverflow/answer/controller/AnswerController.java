package com.codestates_pre024.stackoverflow.answer.controller;

import com.codestates_pre024.stackoverflow.answer.dto.AnswerDto;
import com.codestates_pre024.stackoverflow.answer.dto.AnswerPatchDto;
import com.codestates_pre024.stackoverflow.answer.entity.Answer;
import com.codestates_pre024.stackoverflow.answer.mapper.AnswerMapper;
import com.codestates_pre024.stackoverflow.answer.service.AnswerService;
import com.codestates_pre024.stackoverflow.global.utils.ApiResponse;
import com.codestates_pre024.stackoverflow.global.utils.UriMaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;

@RestController
@RequestMapping(path = "/answers")
@RequiredArgsConstructor
@Slf4j
@Validated
public class AnswerController {
    private final static String ANSWER_DEFAULT_URL = "/questions";
    private final AnswerService answerService;
    private final AnswerMapper mapper;

    //answer 수정
    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer(@PathVariable("answer-id") @Positive Long id,
                                      @Valid @RequestBody AnswerPatchDto answerPatchDto) {
        answerPatchDto.setAnswerId(id);

        Answer updateAnswer = answerService.updateAnswer(
                mapper.answerPatchDtoToAnswer(answerPatchDto), answerPatchDto.getMemberId(), answerPatchDto.getAnswerId());

        URI uri = UriMaker.getUri(ANSWER_DEFAULT_URL, updateAnswer.getQuestion().getId());

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).location(uri).build();
    }

    //answer 삭제
    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") @Positive long id,
                                       @Positive @RequestParam Long memberId) {
        answerService.deleteAnswer(id, memberId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
