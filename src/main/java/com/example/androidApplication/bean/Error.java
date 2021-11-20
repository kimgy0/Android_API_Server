package com.example.androidApplication.bean;


import com.example.androidApplication.domain.dto.FieldErrorDto;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class Error {
    List<FieldErrorDto.ErrorDto> errorDtoList;

    public List errorSave(BindingResult bindingResult,HttpServletResponse response){
        errorDtoList = new ArrayList<>();

        bindingResult.getAllErrors().stream().map(allError -> new FieldErrorDto.ErrorDto(allError.getCode(), allError.getDefaultMessage())).forEach(errorDto -> errorDtoList.add(errorDto));

        if(bindingResult.hasErrors()){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return errorDtoList;
        }
        return null;
    }
}
