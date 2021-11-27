package com.example.androidApplication.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class AlarmDto {
    private LocalTime localTime;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class AlarmTime{
        private int hour;
        private int minute;
    }

}
