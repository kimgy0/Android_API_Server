package com.example.androidApplication.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class GroupManageDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class GroupRegDto{
        @NotNull(message = "invalid subject")
        private String subject;

        @NotNull(message = "invalid certifyNumber")
        private Long certifyNumber;

        @NotNull(message = "invalid comment")
        private String comment;

        @NotNull(message = "invalid localDate")
        private ArrayList<LocalDate> localDate = new ArrayList<>();
    }
}
