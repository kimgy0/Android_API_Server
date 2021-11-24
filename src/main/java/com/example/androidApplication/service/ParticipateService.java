package com.example.androidApplication.service;

import com.example.androidApplication.domain.dto.GroupManageDto;
import com.example.androidApplication.domain.dto.jpqldto.*;
import com.example.androidApplication.domain.entity.Member;
import com.example.androidApplication.domain.entity.Participate;
import com.example.androidApplication.domain.entity.UploadFile;
import com.example.androidApplication.repository.GroupRepository;
import com.example.androidApplication.repository.MemberRepository;
import com.example.androidApplication.repository.ParticipateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ParticipateService {

    private final MemberRepository memberRepository;
    private final GroupRepository groupRepository;
    private final ParticipateRepository participateRepository;

    @Transactional
    public void uploadImage(Long id, String inviteKey, UploadFile uploadFile){
        Participate participate = participateRepository.findParticipateInfo(inviteKey, id).orElseThrow(() -> new NullPointerException());
        uploadFile.addUploadFile(participate);
    }


    public Object findInGroup(String key, Long id) {
        List<MyGroupInfoQueryDto> myGroup = participateRepository.findMyGroup(key);

        List<GroupManageDto.GroupInInfoDto> result = myGroup.stream().collect(groupingBy(g -> new MyGroupInfoDto(g.getComment(), g.getGroupName())
                        , mapping(g -> new GroupUserDto(g.getGroupName(), g.getAbsent(), g.getTardy(), g.isMaster(), g.getUserId(), g.getUsername(), id), toList())))
                .entrySet().stream()
                .map(e -> new GroupManageDto.GroupInInfoDto(e.getKey().getGroupName(), e.getKey().getComment(), e.getValue())).collect(toList());



       /* <MyGroupInfoDto> collect = myGroup.stream()
                .collect(groupingBy(g ->
                                new MyGroupInfoDto(g.getComment()
                                        , g.getGroupName()
                                        , g.getUsername()
                                        , g.getAbsent()
                                        , g.getTardy()
                                        , g.isMaster()
                                        , g.getUserId()),
                        mapping(g -> new GroupAlarmTimeDto(g.getLocalTime()), toList())))
                .entrySet().stream()
                .map(e -> new MyGroupInfoDto(e.getKey().getComment(), e.getKey().getGroupName(), e.getKey().getUsername(), e.getKey().getAbsent(), e.getKey().getTardy(), e.getKey().isMaster(), e.getKey().getUserId(), e.getValue()))
                .collect(toList());
*/

        return result;
    }
}