package com.example.androidApplication.service;

import com.example.androidApplication.domain.entity.Member;
import com.example.androidApplication.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long saveMember(Member member){
        Member saveMember = memberRepository.save(member);
        return saveMember.getId();
    }

}
