package com.codestates_pre024.stackoverflow.member.repository;

import com.codestates_pre024.stackoverflow.member.entity.Member;
import com.codestates_pre024.stackoverflow.member.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public Member findByEmail(String email) {
        QMember m = new QMember("m");
        return queryFactory
                .select(m)
                .from(m)
                .where(m.email.eq(email))
                .fetchOne();
    }
}
