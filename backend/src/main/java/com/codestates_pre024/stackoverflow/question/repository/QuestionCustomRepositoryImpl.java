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
        QQuestion q = new QQuestion("q");
        return queryFactory
                .select(q)
                .from(q)
                .where(q.id.eq(id))
                .orderBy(q.id.desc())
                .limit(10)
                .fetch();
    }
}
