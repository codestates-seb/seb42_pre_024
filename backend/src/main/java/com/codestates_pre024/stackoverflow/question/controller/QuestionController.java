package com.codestates_pre024.stackoverflow.question.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @GetMapping()
    private ResponseEntity<?> getQuestions(){

        return new ResponseEntity<>("hello", HttpStatus.OK);
    }
}
