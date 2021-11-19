package com.example.androidApplication.controller;


import com.example.androidApplication.domain.dto.FieldErrorDto;
import com.example.androidApplication.domain.dto.MemberManageDto;
import com.example.androidApplication.domain.entity.Member;
import com.example.androidApplication.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/all")
public class MemberController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/join")
    public Object login(@Valid @RequestBody MemberManageDto.MemberJoinDto memberJoin,
                        BindingResult bindingResult,
                        HttpServletResponse response) throws IOException {

        List<FieldErrorDto.ErrorDto> errorDtoList = new ArrayList<>();

        for (ObjectError allError : bindingResult.getAllErrors()) {
            FieldErrorDto.ErrorDto errorDto = new FieldErrorDto.ErrorDto(allError.getCode(), allError.getDefaultMessage());
                errorDtoList.add(errorDto);
        }

        if(bindingResult.hasErrors()){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return errorDtoList;
        }

        Member member = new Member(memberJoin.getEmail(), memberJoin.getUsername(),bCryptPasswordEncoder.encode(memberJoin.getPassword()));
        log.info("join member = {}",member);
        return memberService.saveMember(member);
    }
}
