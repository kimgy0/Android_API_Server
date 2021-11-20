package com.example.androidApplication.controller;

import com.example.androidApplication.auth.PrincipalDetails;
import com.example.androidApplication.bean.Error;
import com.example.androidApplication.domain.dto.GroupManageDto;
import com.example.androidApplication.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class GroupController {

    private final GroupService groupService;
    private final Error error;

    @PostMapping("/createGroup")
    public Object createGroup(@Valid @RequestBody GroupManageDto.GroupRegDto groupRegDto, BindingResult bindingResult,
                           @AuthenticationPrincipal PrincipalDetails principalDetails,
                           HttpServletResponse response){

        log.info("create group 진입");
        List list = error.errorSave(bindingResult, response);
        if(list != null){
            return list;
        }
        groupService.addGroup(principalDetails.getId(),groupRegDto);
        return null;
    }

    @PostMapping("/participateGroup")
    public Object participateGroup(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                   @Valid @RequestBody GroupManageDto.ParticipateDto participateDto,
                                   BindingResult bindingResult,
                                   HttpServletResponse response){

        List list = error.errorSave(bindingResult, response);
        if(list != null) {
            return list;
        }
        groupService.joinGroup(participateDto.getKey(),principalDetails.getId());
        return null;
    }

}
