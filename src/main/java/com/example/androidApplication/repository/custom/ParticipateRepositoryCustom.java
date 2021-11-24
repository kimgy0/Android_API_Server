package com.example.androidApplication.repository.custom;

import com.example.androidApplication.domain.dto.jpqldto.MyGroupInfoQueryDto;

import java.util.List;

public interface ParticipateRepositoryCustom {
    List<MyGroupInfoQueryDto> findMyGroup(String key);
}
