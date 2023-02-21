package com.codestates_pre024.stackoverflow.member.repository;

import com.codestates_pre024.stackoverflow.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository {
}
