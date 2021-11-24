package com.example.androidApplication.domain.dto.jpqldto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalTime;
import java.util.List;

@Data
@EqualsAndHashCode(of = "groupName")
@AllArgsConstructor
public class MyGroupInfoDto {
    private String comment;
    private String groupName;
}
