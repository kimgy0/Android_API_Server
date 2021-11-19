package com.example.androidApplication.service;

import com.example.androidApplication.domain.dto.GroupManageDto;
import com.example.androidApplication.domain.entity.Group;
import com.example.androidApplication.domain.entity.Member;
import com.example.androidApplication.domain.entity.Participate;
import com.example.androidApplication.domain.entity.TimeList;
import com.example.androidApplication.repository.GroupRepository;
import com.example.androidApplication.repository.MemberRepository;
import com.example.androidApplication.repository.ParticipateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final GroupRepository groupRepository;
    private final ParticipateRepository participateRepository;

    @Transactional
    public Long saveMember(Member member){
        Member saveMember = memberRepository.save(member);
        return saveMember.getId();
    }

    @Transactional
    public Member findMember(Long id){
        return memberRepository.findById(id).orElseThrow(() -> new NullPointerException("not exist find user"));
    }

}
