package com.example.androidApplication.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("packet come");
        chain.doFilter(request,response);
        //        HttpServletResponse res = (HttpServletResponse) response;
//        HttpServletRequest req = (HttpServletRequest) request;
//
//        String jwtHeader = req.getHeader("Authorization");
//        if(jwtHeader == null || !jwtHeader.startsWith("Bearer ")){
//            String requestURI = req.getRequestURI();
//            log.info("request uri = {}",requestURI);
//            if(requestURI != "/join"){
//                throw new NullPointerException("jwt 유효성 x first case");
//            }
//        }
//
//        String jwtToken = req.getHeader("Authorization").replace("Bearer ", "");
//        String protection = JWT.require(Algorithm.HMAC512("cos")).build().verify(jwtToken).getClaim("cHJvdGVjdGlvbktleQ0KDQo=").asString();
//
//        if(protection != "MuyInO2ZmOuhnDExMDYNCg=="){
//            throw new NullPointerException("jwt 유효성 x second case");
//        }else{
//            chain.doFilter(request,response);
//        }
//
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
