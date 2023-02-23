package com.codestates_pre024.stackoverflow.question.repository;

import com.codestates_pre024.stackoverflow.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long>, QuestionCustomRepository {
}
