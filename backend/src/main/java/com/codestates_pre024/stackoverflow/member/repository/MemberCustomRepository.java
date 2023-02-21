package com.codestates_pre024.stackoverflow.member.repository;

import com.codestates_pre024.stackoverflow.member.entity.Member;

public interface MemberCustomRepository {

    Member findByEmail(String email);

}
