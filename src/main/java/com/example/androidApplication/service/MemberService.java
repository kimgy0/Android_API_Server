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

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final GroupRepository groupRepository;
    private final ParticipateRepository participateRepository;

    public Long saveMember(Member member){
        Member saveMember = memberRepository.save(member);
        return saveMember.getId();
    }

    public Member findMember(Long id){
        return memberRepository.findById(id).orElseThrow(() -> new NullPointerException("not exist find user"));
    }
//    Member member = memberService.findMember(principalDetails.getId());
//    Group group = Group.createGroup(groupRegDto.getSubject(), groupRegDto.getCertifyNumber(), groupRegDto.getComment());
//
//        for (LocalDate date : groupRegDto.getLocalDate()) {
//        TimeList time = new TimeList(date);
//    }
//
//    Participate participate = Participate.CreateParticipate(true, member, group);
    public void addGroup(Long id, GroupManageDto.GroupRegDto groupRegDto) {
        Member member = findMember(id);
        Group group = Group.createGroup(groupRegDto.getSubject(), groupRegDto.getCertifyNumber(), groupRegDto.getComment());
        List<TimeList> timeLists = new ArrayList<>();
        for (LocalDate date : groupRegDto.getLocalDate()) {
            TimeList time = new TimeList(date);
            timeLists.add(time);
        }
        TimeList.createTimeList(group,(ArrayList<TimeList>) timeLists);
        Participate participate = Participate.CreateParticipate(true, member, group);
        participateRepository.save(participate);
    }
}
