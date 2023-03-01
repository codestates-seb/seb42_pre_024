package com.codestates_pre024.stackoverflow.answer.repository;

import com.codestates_pre024.stackoverflow.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long>, AnswerCustomRepository {
}
