package com.codestates_pre024.stackoverflow.member.service;

import com.codestates_pre024.stackoverflow.global.auth.dto.PrincipalDto;
import com.codestates_pre024.stackoverflow.global.auth.utils.CustomAuthorityUtils;
import com.codestates_pre024.stackoverflow.exception.BusinessLogicException;
import com.codestates_pre024.stackoverflow.exception.ExceptionCode;
import com.codestates_pre024.stackoverflow.member.entity.Member;
import com.codestates_pre024.stackoverflow.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;

    @Transactional
    public Member createMember(Member member){
        //이메일 존재 확인
        verifyExistEmail(member.getEmail());

        //Role 저장
        List<String> roles = authorityUtils.createRoles(member.getEmail());
        member.setRoles(roles);

        //패스워드 암호화
        String originalPassword = member.getPassword();
        String encryptedPassword = passwordEncoder.encode(originalPassword);

        member.setPassword(encryptedPassword);

        //암호화 이후 저장
        Member created = memberRepository.save(member);
        return created;
    }

    @Transactional
    public Member getMember(Long id){
        Member member = checkMemberExistById(id);
        return member;
    }

    @Transactional
    public List<Member> getMembers(){ return memberRepository.findAll();}

    @Transactional
    public Member updateMemberMyPage(Member updateMember){

        Long id = updateMember.getId();
        compareIdAndLoginId(id);

        Member member = checkMemberExistById(id);

        Optional.ofNullable(updateMember.getName()).ifPresent(name -> member.setName(name));
        Optional.ofNullable(updateMember.getAboutMe()).ifPresent(aboutMe -> member.setAboutMe(aboutMe));

        return member;
    }

    @Transactional
    public void deleteMember(Long id){

        compareIdAndLoginId(id);

        Member member = checkMemberExistById(id);
        memberRepository.delete(member);
    }
    public Member checkMemberExistById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow( () -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    private void verifyExistEmail(String email) {
        Member findEmailMember = null;
        findEmailMember = memberRepository.findByEmail(email);
        if (findEmailMember != null)
            throw new BusinessLogicException(ExceptionCode.EMAIL_ALREADY_EXIST);
    }

    public void compareIdAndLoginId(Long id) {
        if (!id.equals(getLoginUserId()))
            throw new BusinessLogicException(ExceptionCode.NOT_RESOURCE_OWNER);
    }
    public Long getLoginUserId() {
        Long id = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof PrincipalDto) {
            PrincipalDto principal = (PrincipalDto) authentication.getPrincipal();
            id = principal.getId();
        }

        return id;
    }
}
