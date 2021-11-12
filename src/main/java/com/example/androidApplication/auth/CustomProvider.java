package com.example.androidApplication.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;


@Slf4j
public class CustomProvider extends DaoAuthenticationProvider {
    //    UserDetail은 override 함수에 jpa 설정된 객체로 선언해주면 되고 가장 중요한 함수는
//    GrantedAuthority 함수이다. 이 함수에서 각 페이지에 관한 권한을 부여하게 된다.
//    결론적으로 service는 단순히 detail 객체를 콜하며,
//    detail은 Authority(권한)을 부여만 해주는 역할만 한다.
//    이런것들의 전체를 비교해주는 것은 Provider에서 이루어진다.
//    이제 login handler로 등록한 빈을 살펴보자.
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("compare username and password ");
        return super.authenticate(authentication);
    }
}
