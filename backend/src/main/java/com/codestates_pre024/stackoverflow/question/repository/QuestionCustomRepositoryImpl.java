package com.codestates_pre024.stackoverflow.question.repository;

import com.codestates_pre024.stackoverflow.question.entity.QQuestion;
import com.codestates_pre024.stackoverflow.question.entity.Question;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class QuestionCustomRepositoryImpl implements QuestionCustomRepository {
    private final JPAQueryFactory queryFactory;

    public List<Question> findByMemberId(Long id) {
        QQuestion question = new QQuestion("question");
        return queryFactory
                .select(question)
                .from(question)
                .where(question.member.id.eq(id))
                .orderBy(question.id.desc())
                .limit(10)
                .fetch();
    }
}
