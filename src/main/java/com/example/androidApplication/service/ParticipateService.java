package com.example.androidApplication.service;

import com.example.androidApplication.domain.entity.Member;
import com.example.androidApplication.domain.entity.Participate;
import com.example.androidApplication.domain.entity.UploadFile;
import com.example.androidApplication.repository.GroupRepository;
import com.example.androidApplication.repository.MemberRepository;
import com.example.androidApplication.repository.ParticipateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
