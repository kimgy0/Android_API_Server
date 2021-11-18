package com.example.androidApplication.service;

import com.example.androidApplication.domain.entity.Group;
import com.example.androidApplication.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public Long saveGroup(Group group){
        Group save = groupRepository.save(group);
        return save.getId();
    }
}
