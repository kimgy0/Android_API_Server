package com.example.androidApplication.controller;

import com.example.androidApplication.auth.PrincipalDetails;
import com.example.androidApplication.bean.FileStore;
import com.example.androidApplication.domain.dto.GroupManageDto;
import com.example.androidApplication.domain.entity.UploadFile;
import com.example.androidApplication.service.ParticipateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;


@Controller
@RequestMapping("/api/user/")
@Slf4j
@RequiredArgsConstructor
public class ImageController {

    private final FileStore fileStore;
    private final ParticipateService participateService;

    //인증 사진
    @PostMapping(value = "/pictureSend")
    public void authPicture(@RequestParam String groupId,
                            @RequestParam MultipartFile imageFile,
                            @AuthenticationPrincipal PrincipalDetails principalDetails,
                            HttpServletRequest request){

        UploadFile uploadFile = fileStore.storeImage(imageFile, request);
        participateService.uploadImage(principalDetails.getId(), groupId,uploadFile);


    }
}
