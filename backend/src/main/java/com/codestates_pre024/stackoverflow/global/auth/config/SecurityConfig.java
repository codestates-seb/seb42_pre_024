package com.codestates_pre024.stackoverflow.global.auth.config;


import com.codestates_pre024.stackoverflow.global.auth.filter.JwtAuthenticationFilter;
import com.codestates_pre024.stackoverflow.global.auth.filter.JwtVerificationFilter;
import com.codestates_pre024.stackoverflow.global.auth.handler.MemberAccessDeniedHandler;
import com.codestates_pre024.stackoverflow.global.auth.handler.MemberAuthenticationEntryPoint;
import com.codestates_pre024.stackoverflow.global.auth.jwt.JwtTokenizer;
import com.codestates_pre024.stackoverflow.global.auth.utils.CustomAuthorityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {

        return  http
                .headers().frameOptions().sameOrigin()
                .and()

//                .authorizeHttpRequests()
//                .requestMatchers(request -> CorsUtils.isPreFlightRequest(request)).permitAll()
//                .and()

                .csrf().disable()
                .cors(Customizer.withDefaults())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //stateless??? ????????????????????? ???????????? ?????? ?????? ?????? ????????? ?????? ?????? ???????????? ??????(?????? ?????????) ?????? ??????
                .and()
                .formLogin().disable()
                .httpBasic().disable()

                .exceptionHandling()
                .authenticationEntryPoint(new MemberAuthenticationEntryPoint())
                .accessDeniedHandler(new MemberAccessDeniedHandler())
                .and()

                .apply(new CustomFilterConfigurer())
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)        // ?????? ?????????
                .deleteCookies("Refresh")   // "Refresh" ?????? ??????
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.getWriter().append("Logged out successfully");     // ???????????? ?????? ????????? ??????
                        response.setStatus(HttpServletResponse.SC_OK);              // HTTP ?????? ?????? ??????
                    }
                })
                .and()

                .authorizeHttpRequests(authorize -> authorize
                                .antMatchers(HttpMethod.POST,"/members").permitAll()
                                .antMatchers(HttpMethod.PATCH,"/members/**").hasRole("USER")
                                .antMatchers(HttpMethod.GET,"/members").denyAll()
                                .antMatchers(HttpMethod.GET,"/members/**").permitAll()
                                .antMatchers(HttpMethod.DELETE,"/members/**").hasRole("USER")

                                .antMatchers(HttpMethod.POST,"/questions").hasRole("USER")
                                .antMatchers(HttpMethod.PATCH,"/questions/**").hasRole("USER")
                                .antMatchers(HttpMethod.GET,"/questions").permitAll()
                                .antMatchers(HttpMethod.GET,"/questions/{id}").permitAll()
                                .antMatchers(HttpMethod.DELETE,"/questions/{id}").hasRole("USER")

                                .antMatchers(HttpMethod.POST,"/questions/{id}/answers").hasRole("USER")
                                .antMatchers(HttpMethod.PATCH,"/answers/**").hasRole("USER")
                                .antMatchers(HttpMethod.GET,"/questions/{id}/answers").denyAll()
                                .antMatchers(HttpMethod.GET,"/questions/{id}/answers/{answerId}").denyAll()
                                .antMatchers(HttpMethod.DELETE,"/answers/**").hasRole("USER")

                                .antMatchers(HttpMethod.POST,"/logout").hasRole("USER")
                                .anyRequest().permitAll()
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean //CORS ?????? ??????
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();


        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.addExposedHeader("*");
        configuration.addExposedHeader("Authorization");
        configuration.addExposedHeader("Access-Control-Allow-Credentials");

//        configuration.addAllowedOriginPattern("http://localhost:3000");
//        configuration.setAllowedMethods(Arrays.asList("POST","GET","PATCH","DELETE","OPTIONS"));
//        configuration.addExposedHeader("Authorization");


        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); //application??? ?????? endpoint??? ??????

        return source;
    }

    //JwtAuthenticationFilter ??????
    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);
            builder.addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }

    }
}
