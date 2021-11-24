package com.example.androidApplication.domain.dto.jpqldto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class GroupUserDto {

    public GroupUserDto(String groupName, int absent, int tardy, boolean master, Long userId, String username, Long principalId) {
        this.groupName = groupName;
        this.absent = absent;
        this.tardy = tardy;
        this.master = master;
        this.userId = userId;
        this.username = username;
        if (principalId == userId){
            this.checkMe=true;
        }else{
            this.checkMe=false;
        }
    }

    @JsonIgnore
    private String groupName;


    private int absent;
    private int tardy;
    private boolean master;
    private Long userId;
    private String username;

    private boolean checkMe;


}
