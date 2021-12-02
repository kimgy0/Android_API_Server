package com.example.androidApplication.bean.aws;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ConfigurationProperties(prefix = "cloud.aws.s3")
//https://docs.spring.io/spring-boot/docs/2.5.6/reference/html/configuration-metadata.html#configuration-metadata.annotation-processor
//@ConfigurationProperties
//properties 파일의 key 값이 아래와 같이 같은 값으로 시작할 때, 그것을 묶어서 Bean으로 등록할 수 있다.
@Component
public class S3Component {
    private String bucket;
}
