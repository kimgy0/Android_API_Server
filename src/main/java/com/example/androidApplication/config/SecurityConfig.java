package com.example.androidApplication.config;

import com.example.androidApplication.Jwt.JwtAuthenticationFilter;
import com.example.androidApplication.filter.LoginFilter;
import com.example.androidApplication.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor //자동 빈 주입
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    private final CorsFilter corsFilter;
    private final MemberRepository memberRepository;

    @Bean
    // Password Encode
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    //security config
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new LoginFilter(), SecurityContextPersistenceFilter.class);
        //필터중에 제일 먼저 실행되는 구조.

        http.csrf().disable();
        //시큐리티 로그인폼을 사용하지 않음.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsFilter)
                .formLogin().disable()
                .httpBasic().disable()
                /*
                 * basic 방식은 헤더에 id랑 pw를 다 들고 서버에 요청을 하는 방식이라서 보안에 취약함
                 * 대신에 id pw가 담긴 토큰을 암호화해서 로그인 요청을 하려면 https 프로토콜을 사용해야함
                 * 그럴떄 헤더에 토큰을 올려서 토큰에 시간도 걸어주고 노출되어도 상관x 서버가 토큰을 다시 만들어주기 때문.
                 * */

                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .authorizeRequests()
                .antMatchers("/api/user/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/manager/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();



    }
}
