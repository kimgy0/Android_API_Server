package com.example.androidApplication.domain.dto;

import com.example.androidApplication.domain.entity.TimeList;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GroupManageDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GroupRegDto{
        @NotNull(message = "invalid subject")
        private String subject;

        @NotNull(message = "invalid certifyNumber")
        private Long certifyNumber;

        @NotNull(message = "invalid comment")
        private String comment;

        @NotNull(message = "invalid localDate")
        private List<LocalTimeJson> localTimeList;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LocalTimeJson {
        @JsonFormat(pattern = "HH:mm")
        private LocalTime localTime;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ParticipateDto {
        @NotNull(message = "invalid uuid")
        private String key;
    }
}
