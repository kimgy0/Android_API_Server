package com.example.androidApplication.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.lang.model.type.ArrayType;
import java.util.Arrays;

@Configuration
@ComponentScan
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter(){
        /*
         * This can be achieved by adding @CrossOrigin annotation to the Controller (or RestController) methods.
         */
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); //javascript 처리 여부를 허용합니다.
        config.setAllowedOrigins(Arrays.asList("*"));
//        Arrays.asList("http://15.165.219.73:8080/","http://localhost:8080/")
        //모든 ip에 대한 응답 허용
        config.setAllowedMethods(Arrays.asList("GET","POST","PUT","PATCH","OPTIONS","DELETE"));
        config.addAllowedHeader("*");
        config.setExposedHeaders(Arrays.asList("Authorization"));
        source.registerCorsConfiguration("/**",config.applyPermitDefaultValues());
        return new CorsFilter(source);

    }
    // 2. 필터등록 해주기.

}
