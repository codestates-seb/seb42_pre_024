package com.codestates_pre024.stackoverflow.question.repository;

import com.codestates_pre024.stackoverflow.question.entity.Question;

import java.util.List;

public interface QuestionCustomRepository {
    List<Question> findByMemberId(Long id);
}
