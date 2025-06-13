package com.siakad.controller;

import com.siakad.constant.Constant;
import com.siakad.constant.GlobalMessage;
import com.siakad.exception.BusinessException;
import com.siakad.model.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorHandlerController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Object>> exception(Exception e) {
        log.error(Constant.ERROR, e);
        GlobalMessage globalMessage = GlobalMessage.INTERNAL_SERVER_ERROR;
        return buildResponse(globalMessage.httpStatus, globalMessage.message);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BaseResponse<Object>> exception(BusinessException e) {
        log.error(Constant.ERROR, e);
        return buildResponse(e.getHttpStatus(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Object>> exception(MethodArgumentNotValidException e) {
        log.error(Constant.ERROR, e);
        return buildResponse(HttpStatus.BAD_REQUEST, getMethodArgumentMessage(e));
    }

    private String getMethodArgumentMessage(MethodArgumentNotValidException e) {
        if (e.getFieldErrors().isEmpty()) return "";
        return e.getFieldErrors().get(0).getDefaultMessage();
    }

    private ResponseEntity<BaseResponse<Object>> buildResponse(HttpStatus httpStatus, String message) {
        BaseResponse<Object> response = BaseResponse.builder()
                .code(httpStatus.value())
                .message(message)
                .build();
        return new ResponseEntity<>(response, httpStatus);
    }
}
