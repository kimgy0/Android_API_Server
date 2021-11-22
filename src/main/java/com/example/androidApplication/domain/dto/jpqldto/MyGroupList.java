package com.example.androidApplication.domain.dto.jpqldto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyGroupList {
    private String inviteKey;
    private String groupName;
    private int absent;
    private int tardy;
    private boolean master;
}
