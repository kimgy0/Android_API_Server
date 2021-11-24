package com.example.androidApplication.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StudyDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public class ImageForm {

        private String groupId;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        private LocalDateTime localDateTime = LocalDateTime.now();
        @NotEmpty(message = "imageFile error")
        private MultipartFile imageFile;
    }
}
