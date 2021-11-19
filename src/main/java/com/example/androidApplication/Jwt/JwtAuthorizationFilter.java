package com.example.androidApplication.Jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.androidApplication.auth.PrincipalDetails;
import com.example.androidApplication.domain.entity.Member;
import com.example.androidApplication.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {


    private final MemberRepository memberRepository;
    long refreshTime = 100L * 60L;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwtHeader = request.getHeader("Authorization");
        if (jwtHeader == null || !jwtHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String jwtToken = request.getHeader("Authorization").replace("Bearer ", "");

        log.info("this is {}", jwtToken);

        DecodedJWT cos = JWT.require(Algorithm.HMAC512("cos")).build().verify(jwtToken);
        Long id = cos.getClaim("id").asLong();
        String username = cos.getClaim("username").asString();

        if (username != null) {
            Member findMember = memberRepository.findById(id).orElseThrow(() -> new NullPointerException("not exist find user"));
            PrincipalDetails principalDetails = new PrincipalDetails(findMember);

            Authentication authentication
                    = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
            response.addHeader("Authorization", jwtHeader);
            chain.doFilter(request, response);
        }

//        if( id != null) {
//            Date expiresAt = cos.getExpiresAt();
//            if(expiresAt.getTime()>0){
//                long remain = expiresAt.getTime() - System.currentTimeMillis();
//                if(remain < refreshTime){
//                    Member findMember = memberRepository.findById(id).orElseThrow(() -> new NullPointerException("null"));
//                    PrincipalDetails principalDetails = new PrincipalDetails(findMember);
//
//                    Authentication authentication
//                            = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
//
//                    String createJwt = JWT.create()
//                            .withSubject("cos")
//                            .withExpiresAt(new Date(System.currentTimeMillis()+(6000*10)))
//                            .withClaim("id", id)
//                            .withClaim("username",username )
//                            .sign(Algorithm.HMAC512("cos"));
//
//                    response.addHeader("Authorization", "Bearer "+createJwt);
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                    response.addHeader("Authorization", createJwt);
//                    chain.doFilter(request, response);
//                }
//            }
//            log.info("인증");
//            response.addHeader("Authorization", jwtHeader);
//            chain.doFilter(request, response);
//        }
//    }

    }
}
