package com.example.androidApplication.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;
import org.springframework.data.annotation.TypeAlias;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "fileuploadtable")
public class UploadFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uploadfile_id")
    private String id;
    private String uploadFileName;
    private String storeFileName;
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "participate_id")
    private Participate participate;

    public void addUploadFile(Participate participate){
        this.participate = participate;
        participate.getUploadFile().add(this);
    }

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
