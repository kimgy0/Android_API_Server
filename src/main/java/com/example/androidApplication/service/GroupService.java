package com.example.androidApplication.service;

import com.example.androidApplication.domain.dto.AlarmDto;
import com.example.androidApplication.domain.dto.GroupManageDto;
import com.example.androidApplication.domain.dto.jpqldto.MyGroupListQueryDto;
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

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GroupService {

    private final MemberRepository memberRepository;
    private final GroupRepository groupRepository;
    private final ParticipateRepository participateRepository;

    public Long saveGroup(Group group){
        Group save = groupRepository.save(group);
        return save.getId();
    }

    @Transactional
    public void addGroup(Long id, GroupManageDto.GroupRegDto groupRegDto) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new NullPointerException("not exist find user"));
        Group group = Group.createGroup(groupRegDto.getSubject(), groupRegDto.getCertifyNumber(), groupRegDto.getComment());
        List<TimeList> timeLists = new ArrayList<>();

        List<GroupManageDto.LocalTimeJson> localTimeList = groupRegDto.getLocalTimeList();
        for (GroupManageDto.LocalTimeJson localTimeJson : localTimeList) {
            LocalTime localTime = localTimeJson.getLocalTime();
            TimeList timeList = new TimeList(localTime);
            timeLists.add(timeList);
        }


        TimeList.createTimeList(group,(ArrayList<TimeList>) timeLists);
        Participate participate = Participate.CreateParticipate(true, member, group);
        groupRepository.save(group);
        participateRepository.save(participate);

    }


    // 그룹 추가
    @Transactional
    public String joinGroup(String key, Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new NumberFormatException());
        Group group = groupRepository.findByInviteKey(key).orElseThrow(() -> new NumberFormatException());

        Participate participate = Participate.CreateParticipate(false, member, group);
        participateRepository.save(participate);
        return group.getInviteKey();
    }


    public List<MyGroupListQueryDto> findMyGroupList(Long id) {
        return groupRepository.findMyAllGroup(id);
    }

    public ArrayList<AlarmDto.AlarmTime> alarm(String inviteKey){
        List<AlarmDto> byKey = groupRepository.findMyTimeListBy(inviteKey);
        ArrayList<AlarmDto.AlarmTime> objects = new ArrayList<>();
        byKey.forEach(i->{
            int hour = i.getLocalTime().getHour();
            int minute = i.getLocalTime().getMinute();
            AlarmDto.AlarmTime alarmTime = new AlarmDto.AlarmTime(hour, minute);
            objects.add(alarmTime);
        });

        return objects;
    }


}
