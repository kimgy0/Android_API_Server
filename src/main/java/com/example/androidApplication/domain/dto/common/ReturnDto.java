package com.example.androidApplication.domain.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.GeneratedValue;

@Data
public class ReturnDto<T> {
    T data;
}
