package com.example.androidApplication.domain.dto.jpqldto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MyGroupInfoQueryDto {
    private String comment;
    private String groupName;

    private int absent;
    private int tardy;
    private boolean master;
    private Long userId;
    private String username;

    public MyGroupInfoQueryDto(String comment,
                               String groupName,
                               int absent,
                               int tardy,
                               boolean master,
                               Long userId,
                               String username) {
        this.comment = comment;
        this.groupName = groupName;
        this.absent = absent;
        this.userId = userId;
        this.tardy = tardy;
        this.master = master;
        this.username = username;
    }
}
