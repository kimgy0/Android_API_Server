package com.example.androidApplication.controller;

import com.example.androidApplication.auth.PrincipalDetails;
import com.example.androidApplication.domain.dto.FieldErrorDto;
import com.example.androidApplication.domain.dto.GroupManageDto;
import com.example.androidApplication.domain.dto.MemberManageDto;
import com.example.androidApplication.domain.entity.Group;
import com.example.androidApplication.domain.entity.Member;
import com.example.androidApplication.domain.entity.Participate;
import com.example.androidApplication.domain.entity.TimeList;
import com.example.androidApplication.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class GroupController {

    private final MemberService memberService;

    @PostMapping("/createGroup")
    public Object createGroup(@Valid @RequestBody GroupManageDto.GroupRegDto groupRegDto, BindingResult bindingResult,
                           @AuthenticationPrincipal PrincipalDetails principalDetails,
                           HttpServletResponse response){

        List<FieldErrorDto.ErrorDto> errorDtoList = new ArrayList<>();

        for (ObjectError allError : bindingResult.getAllErrors()) {
            FieldErrorDto.ErrorDto errorDto = new FieldErrorDto.ErrorDto(allError.getCode(), allError.getDefaultMessage());
            errorDtoList.add(errorDto);
        }

        if(bindingResult.hasErrors()){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return errorDtoList;
        }

        memberService.addGroup(principalDetails.getId(),groupRegDto);
        return null;
    }

}
