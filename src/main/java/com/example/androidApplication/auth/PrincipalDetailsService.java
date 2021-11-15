package com.example.androidApplication.auth;

import com.example.androidApplication.domain.entity.Member;
import com.example.androidApplication.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;


    @Override
//    memberRepository.findByUsername(username).orElseThrow(()->new NullPointerException("error"));
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> byUsername = memberRepository.findByUsername(username);
        Member member = byUsername.orElseThrow(() -> new NullPointerException("not exist user"));
        return new PrincipalDetails(member);
    }
}
