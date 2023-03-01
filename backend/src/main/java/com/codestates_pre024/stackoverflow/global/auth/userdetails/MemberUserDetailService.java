package com.codestates_pre024.stackoverflow.global.auth.userdetails;

import com.codestates_pre024.stackoverflow.global.auth.utils.CustomAuthorityUtils;
import com.codestates_pre024.stackoverflow.member.entity.Member;
import com.codestates_pre024.stackoverflow.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final CustomAuthorityUtils authorityUtils;

    /** @param username 사용자 식별하는 String -- email **/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member findEmailMember = null;
        findEmailMember = memberRepository.findByEmail(username);
        if (findEmailMember == null)
            throw new UsernameNotFoundException("No sign-up information found for email");

        return new CustomUserDetails(findEmailMember);
    }

    private class CustomUserDetails extends Member implements UserDetails {

        CustomUserDetails(Member member) {
            setId(member.getId());
            setEmail(member.getEmail());
            setPassword(member.getPassword());
            setRoles(member.getRoles());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> authorities =  authorityUtils.createAuthorities(getRoles());
            return authorities;
        }

        @Override
        public String getUsername() {
            return getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }

}
