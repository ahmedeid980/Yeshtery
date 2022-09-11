package com.ahmedeid.yeshtery.Yeshtery.ExceptionHandling;

import com.ahmedeid.yeshtery.Yeshtery.dto.CommonResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ManageExceptionHandler {
    @ExceptionHandler(YeshteryExceptionHandler.class)
    public ResponseEntity<CommonResponseDto> handMissingOrBadParameterException(YeshteryExceptionHandler ex) {

        CommonResponseDto commonResponse = new CommonResponseDto();
        commonResponse.setCode(ex.getCode());
        commonResponse.setMessage(ex.getMessage());
        commonResponse.setSuccess(false);
        commonResponse.setData(null);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }
}
