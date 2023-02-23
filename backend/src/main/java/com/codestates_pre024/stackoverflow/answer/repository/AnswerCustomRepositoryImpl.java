package com.codestates_pre024.stackoverflow.answer.repository;

import com.codestates_pre024.stackoverflow.answer.entity.Answer;
import com.codestates_pre024.stackoverflow.answer.entity.QAnswer;
import com.codestates_pre024.stackoverflow.question.entity.QQuestion;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class AnswerCustomRepositoryImpl implements AnswerCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Answer> findByQuestionId(Long id) {
        QAnswer a = new QAnswer("a");
        return queryFactory
                .select(a)
                .from(a)
                .where(a.question.id.eq(id))
                .fetch();
    }
}
