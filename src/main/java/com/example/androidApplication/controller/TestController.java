package com.example.androidApplication.controller;

import com.example.androidApplication.domain.entity.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api-android/test")
    public Member testAPI(){
        return new Member(1L,"username","password");
    }
}
