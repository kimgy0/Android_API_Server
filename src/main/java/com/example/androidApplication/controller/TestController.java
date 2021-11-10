package com.example.androidApplication.controller;

import com.example.androidApplication.domain.entity.Member;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TestController {

    @GetMapping("/api-android/test")
    public Member testAPI(){
        return new Member(1L,"username","password");
    }
}
