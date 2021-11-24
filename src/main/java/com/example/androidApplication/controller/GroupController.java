package com.example.androidApplication.controller;

import com.example.androidApplication.auth.PrincipalDetails;
import com.example.androidApplication.bean.Error;
import com.example.androidApplication.bean.FileStore;
import com.example.androidApplication.domain.dto.GroupManageDto;
import com.example.androidApplication.domain.dto.StudyDto;
import com.example.androidApplication.domain.dto.common.ReturnDto;
import com.example.androidApplication.domain.dto.jpqldto.MyGroupListQueryDto;
import com.example.androidApplication.domain.entity.UploadFile;
import com.example.androidApplication.service.GroupService;
import com.example.androidApplication.service.ParticipateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class GroupController {

    private final FileStore fileStore;
    private final GroupService groupService;
    private final ParticipateService participateService;
    private final Error error;

    //그룹 생성
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

    //그룹 참가
    @PostMapping("/participateGroup")
    public Object participateGroup(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                   @Valid @RequestBody GroupManageDto.ParticipateDto participateDto,
                                   BindingResult bindingResult,
                                   HttpServletResponse response){

        List list = error.errorSave(bindingResult, response);
        if(list != null) {
            return list;
        }

        return groupService.joinGroup(participateDto.getKey(),principalDetails.getId());
    }

    //인증 사진
    @PostMapping("/pictureSend")
    public void authPicture(@Valid @RequestBody StudyDto.ImageForm imageForm, BindingResult bindingResult,
                              @AuthenticationPrincipal PrincipalDetails principalDetails,
                              HttpServletRequest request){

        MultipartFile imageFile = imageForm.getImageFile();
        try {
            UploadFile uploadFile = fileStore.storeImage(imageFile, request);
            participateService.uploadImage(principalDetails.getId(), imageForm.getGroupId(),uploadFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //메인화면 : 메인에 보이는 내가 가입한 그룹과 내가 생성한 그룹을 출력
    @GetMapping("/printGroups")
    public ReturnDto<List<MyGroupListQueryDto>> printAllMyGroup(@AuthenticationPrincipal PrincipalDetails principalDetails){
        List<MyGroupListQueryDto> myGroupListQueryDto = groupService.findMyGroupList(principalDetails.getId());
        ReturnDto<List<MyGroupListQueryDto>> returnDto = new ReturnDto<>();
        returnDto.setData(myGroupListQueryDto);
        return returnDto;
    }

    @GetMapping("/printInGroup/{inviteKey}")
    public Object printAllInGroup(@PathVariable("inviteKey") String inviteKey,
                                  @AuthenticationPrincipal PrincipalDetails principalDetails){
        return participateService.findInGroup(inviteKey,principalDetails.getId());
        
    }

}
