package com.codestates_pre024.stackoverflow.global.auth.filter;

import com.codestates_pre024.stackoverflow.global.auth.jwt.JwtTokenizer;
import com.codestates_pre024.stackoverflow.global.auth.utils.CustomAuthorityUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
public class JwtVerificationFilter extends OncePerRequestFilter {
   private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;

    /*이 필터의 역할
    1) Jwt토큰의 검증 - 유효기간이 지났는가?
    2) 역할에 따른 권한 부여
    */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            //검증 요청이 들어오면. claim을 확인한다.
            Map<String, Object> claims = verifyJWS(request);

            //claims (권한정보) 를 넘겨서, Authentication 객체 생성 후 Security Context에 저장
            setAuthenticationToContext(claims);

        } catch (SignatureException e) {
            request.setAttribute("exception", e);
        } catch (ExpiredJwtException e) {
            request.setAttribute("exception", e);
        } catch (Exception e) {
            request.setAttribute("exception", e);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String authorization = request.getHeader("Authorization");
        return authorization == null || !authorization.startsWith("Bearer");
    }

    private Map<String, Object> verifyJWS(HttpServletRequest request) {

        String jws = request.getHeader("Authorization").replace("Bearer ","");

        //jws는 base64로 인코딩되어있기 떄문에 디코딩 해준다.
        String key = jwtTokenizer.getSecretKey();
        String encodedKey = jwtTokenizer.encodeBase64SecretKey(key);

        //JWT에서 Claims를 파싱할 수 있다 == 내부적으로 서명(Signature) 검증에 성공했다
        Jws<Claims> claimsJws = jwtTokenizer.getClaimsFromJws(jws,encodedKey);
        Map<String, Object> claims = claimsJws.getBody();

        return claims;
    }

    //Authentication 객체를 SecurityContext에 저장하기 위한 private 메서드
    private void setAuthenticationToContext(Map<String, Object> claims) {
        String username = (String) claims.get("username"); // claims : username, id,  roles 존재.
        List<GrantedAuthority> authorities = authorityUtils.createAuthorities((List<String>) claims.get("roles"));

        //Parameter :  principal, credential, collection<GrantedAuthority>
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
