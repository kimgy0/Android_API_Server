package com.example.androidApplication.bean;

import com.example.androidApplication.domain.entity.UploadFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class FileStore {
    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String fileName,HttpServletRequest request){
        return fileDir + fileName;
    }
    public UploadFile storeImage(MultipartFile multipartFile,HttpServletRequest request) {

        if(!multipartFile.isEmpty()){
            String originalFilename = multipartFile.getOriginalFilename();
            String storeFileName = createStoreFileName(originalFilename);

            try {
                multipartFile.transferTo(new File(getFullPath(storeFileName,request)));
            } catch (IOException e) {
                e.printStackTrace();
                log.info("error");
            }

            return new UploadFile(originalFilename,storeFileName);
        }else{
            return new UploadFile(null,null);
        }
    }

    private String createStoreFileName(String originalFilename) {
        String uuid = UUID.randomUUID().toString();
        String ext = extracted(originalFilename);
        return uuid + "." +ext;
    }

    private String extracted(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
