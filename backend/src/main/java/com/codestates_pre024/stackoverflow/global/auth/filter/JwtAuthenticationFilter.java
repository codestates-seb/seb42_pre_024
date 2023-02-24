package com.codestates_pre024.stackoverflow.global.auth.filter;

import com.codestates_pre024.stackoverflow.global.auth.dto.LoginDto;
import com.codestates_pre024.stackoverflow.global.auth.jwt.JwtTokenizer;
import com.codestates_pre024.stackoverflow.member.entity.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /*이 필터의 역할
    1) Authentication Manager에게 인증 객체 전달
    2) 인증 객체를 돌려받아서 Security Context내부에 토큰 저장
    */

    private final AuthenticationManager authenticationManager;
    private final JwtTokenizer jwtTokenizer;


    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("지원되지 않는 메서드입니다. " + request.getMethod());
        }
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login","POST"));

        ObjectMapper objectMapper = new ObjectMapper();
        LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword());

        Authentication result = authenticationManager.authenticate(authenticationToken); //Authentication을 전달해야하는데, token이 Authentication의 구현쳋
        return result;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Member member = (Member) authResult.getPrincipal(); //Principal : 인증된 멤버 객체

        String accessToken = delegateAccessToken(member);
        String refreshToken = delegateRefreshToken(member);

        //AccessToken - Header에
        String responseTokenString = "Bearer " + accessToken;
        response.setHeader("Authorization", responseTokenString ); // Authorization 헤더에 Bearer 사용 - jwt access token 임을 구분

        //RefreshToken - Cookie에
        Cookie cookie = new Cookie("Refresh", refreshToken);
        response.addCookie(cookie);

    }

    private String delegateAccessToken(Member member){

        //access token은 토큰내부정보(claims), subject-
        Map<String, Object> claims = new HashMap<>(); // claims : 토큰 내부에 보내줄 정보

        claims.put("username",member.getEmail());
        claims.put("id",member.getId());
        claims.put("roles", member.getRoles());

        String subject = member.getEmail();

        // get expiration date
        int expirationMinutes = jwtTokenizer.getAccessTokenExpirationMinutes();
        Date expiration = jwtTokenizer.getTokenExpiration(expirationMinutes);

        // use plain secret key -> get encoded key
        String plainKey = jwtTokenizer.getSecretKey();
        String base64EncodedKey = jwtTokenizer.encodeBase64SecretKey(plainKey);

        // make access token
        String accessToken = jwtTokenizer.generateAccessToken(claims,subject,expiration,base64EncodedKey);

        return accessToken;
    }

    String delegateRefreshToken(Member member){

        //refresh token은 만료일만 생성하면 됨.
        String subject = member.getEmail();
        int expirationMinutes = jwtTokenizer.getRefreshTokenExpirationMinutes();
        Date expiration = jwtTokenizer.getTokenExpiration(expirationMinutes);

        String plainKey = jwtTokenizer.getSecretKey();
        String base64EncodedKey = jwtTokenizer.encodeBase64SecretKey(plainKey);

        // make refresh token
        String refreshToken = jwtTokenizer.generateRefreshToken(subject,expiration,base64EncodedKey);
        return refreshToken;
    }

}
