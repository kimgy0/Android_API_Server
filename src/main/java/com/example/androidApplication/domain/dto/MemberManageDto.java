package com.example.androidApplication.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class MemberManageDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class MemberJoinDto{

        @NotNull(message = "not valid password")
        @Size(min = 8 , max = 20, message = "password is over than 8 and lower than 20")
        private String password;

        @NotNull(message = "not valid username")
        @Size(min=7, max=20, message = "username is over than 7 and lower than 20")
        private String username;

        @Email(message = "not valid email")
        private String email;
    }
}
