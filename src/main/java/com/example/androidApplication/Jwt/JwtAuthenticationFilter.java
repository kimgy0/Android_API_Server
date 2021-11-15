package com.example.androidApplication.Jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.androidApplication.auth.PrincipalDetails;
import com.example.androidApplication.domain.entity.Member;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        return super.attemptAuthentication(request, response);
        log.info("try login");

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            Member member = objectMapper.readValue(request.getInputStream(), Member.class);
            log.info("read login json = {}",member);


            /* 로그인 시도 */
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());

            /* 토큰 만들기 */
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            //이게 실행되면 principalDetailsService의 loadUserByUsername 가 실행이 됨.

            /*
             * 매니저가 대신 로그인을 해주며 매니저에 토큰을 넣어서 실행하게 되면 인증정보를 받음.
             * 여기 객체에는 로그인 정보가 담겨있고, 성공적으로 담겼다는 거는 로그인이 정상적 이라는 뜻.
             */

            PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
            log.info("success login = {}",principal.getMember());
            // security session 영역에 저장.

            return authentication;
            //return 으로 세션에 저장
            //리턴하는 이유는 시큐리티가 권한관리를 대신 해주기 때문.

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; //login fail
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        PrincipalDetails principal = (PrincipalDetails) authResult.getPrincipal();

        //hash
        String jwtToken = JWT.create()
                .withSubject("cos")
                .withExpiresAt(new Date(System.currentTimeMillis()+(60000*10)))
                .withClaim("id", principal.getMember().getId())
                .withClaim("username", principal.getMember().getUsername())
                .sign(Algorithm.HMAC512("cos"));

        response.addHeader("Authorization", "Bearer "+jwtToken);
    }
}
