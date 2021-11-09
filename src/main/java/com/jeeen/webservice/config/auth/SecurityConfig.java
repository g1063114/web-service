package com.jeeen.webservice.config.auth;

import com.jeeen.webservice.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()  // URL별 권환 관리 설정 시작
                    .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll() // 전체 열람 권환
                    .antMatchers("/posts/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()   // 인증된 사용자에게 허용
                .and()
                    .logout()
                        .logoutSuccessUrl("/")  // 로그아웃 성공시 / 로 이동
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);

    }
}
