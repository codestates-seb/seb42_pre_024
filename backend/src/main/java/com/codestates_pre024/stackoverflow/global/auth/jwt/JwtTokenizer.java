package com.codestates_pre024.stackoverflow.global.auth.jwt;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
@Component
public class JwtTokenizer {

    @Getter
    @Value("${jwt.key}")
    private String secretKey;

    @Getter
    @Value("${jwt.access-token-expiration-minutes}")
    private int accessTokenExpirationMinutes;

    @Getter
    @Value("${jwt.refresh-token-expiration-minutes}")
    private int refreshTokenExpirationMinutes;

    //Generate Access Token
    public String generateAccessToken (Map<String,Object> claims, String subject, Date expirateDate, String encodedBase64SecretKey) {

        Key key = getKeyFromEncodedBase64SecretKey(encodedBase64SecretKey);

        String accessToken = Jwts.builder()
                .setSubject(subject)
                .setClaims(claims)
                .setExpiration(expirateDate)
                .setIssuedAt(Calendar.getInstance().getTime())
                .signWith(key)
                .compact();         // JWT 생성 후 직렬화

        return accessToken;
    }

    //Generate Refresh Token
    public String generateRefreshToken (String subject, Date expirateDate, String encodedBase64SecretKey) {
        Key key = getKeyFromEncodedBase64SecretKey(encodedBase64SecretKey);
        String refreshToken = Jwts.builder()
                .setSubject(subject)
                .setExpiration(expirateDate)
                .setIssuedAt(Calendar.getInstance().getTime())
                .signWith(key)
                .compact();

        return refreshToken;
    }

    //base 64 encoding
    public String encodeBase64SecretKey(String secretKey){
        return Encoders.BASE64.encode(secretKey.getBytes(Charset.defaultCharset())); // //StandardCharsets.UTF_8 -> sun.nio.cs.UTF_8.INSTANCE
    }

    //base64로 인코딩된 키를 디코딩 하여 <JWT의 서명에 사용할 Secret Key>를 얻어냄
    public Key getKeyFromEncodedBase64SecretKey(String encodedBase64SecretKey) {
        byte [] byteKey = Decoders.BASE64.decode(encodedBase64SecretKey);
        Key key = Keys.hmacShaKeyFor(byteKey);
        return key;
    }


    //verification : 유저가 리소스 접근할 때마다 request header에 포함된 JWT를 검증

    // verification Signature - 서명 key 변조 확인
    public void verificateJWTSignature (String jwtWithSignature, String encodedBase64SecretKey) {
        Key key = getKeyFromEncodedBase64SecretKey(encodedBase64SecretKey);
        JwtParser parser = Jwts.parserBuilder().setSigningKey(key).build();
        parser.parseClaimsJws(jwtWithSignature);
    }

    // JWT 만료일시 지정 메서드
    public Date getTokenExpiration(int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minutes);

        Date expiration = calendar.getTime(); //return Date
        return expiration;
    }

}
