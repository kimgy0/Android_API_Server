package com.example.androidApplication.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class FieldErrorDto {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class ErrorDto{
        private String errorField;
        private String errorMessages;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ErrorListDto{
        List<ErrorDto> list = new ArrayList<>();
    }
}
