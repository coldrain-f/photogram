package com.cos.controllerdemo.handler;

import com.cos.controllerdemo.handler.ex.CustomApiException;
import com.cos.controllerdemo.handler.ex.CustomException;
import com.cos.controllerdemo.handler.ex.CustomValidationApiException;
import com.cos.controllerdemo.handler.ex.CustomValidationException;
import com.cos.controllerdemo.util.Script;
import com.cos.controllerdemo.web.dto.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e) {
        if (e.getErrorMap() == null) {
            return Script.back(e.getMessage());
        }
        return Script.back(e.getErrorMap().toString());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomValidationApiException.class)
    public CMRespDto<Map<String, String>> validationApiException(CustomValidationApiException e) {
        return new CMRespDto<>(-1, e.getMessage(), e.getErrorMap());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomApiException.class)
    public CMRespDto<Map<String, String>> apiException(CustomApiException e) {
        return new CMRespDto<>(-1, e.getMessage(), null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomException.class)
    public String exception(CustomException e) {
        return Script.back(e.getMessage());
    }
}