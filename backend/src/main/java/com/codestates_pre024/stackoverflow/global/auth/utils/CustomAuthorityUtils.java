package com.codestates_pre024.stackoverflow.global.auth.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomAuthorityUtils {

    private final List<String> STRING_USER_ROLES = List.of("USER");

    //DB에서 Role가져와서 Authority로 변환
    public List<GrantedAuthority> createAuthorities(List<String> roles){
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

        return authorities;
    }

    //DB 반영
    public List<String> createRoles(String email) {
        return STRING_USER_ROLES;
    }
}
