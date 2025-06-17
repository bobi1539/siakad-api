package com.siakad.controller;

import com.siakad.constant.Constant;
import com.siakad.constant.GlobalMessage;
import com.siakad.model.dto.Header;
import com.siakad.model.response.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@Slf4j
public abstract class AbstractBaseController {

    protected <T> BaseResponse<T> buildSuccessResponse(T data) {
        return BaseResponse.<T>builder()
                .code(GlobalMessage.SUCCESS.httpStatus.value())
                .message(GlobalMessage.SUCCESS.message)
                .data(data)
                .build();
    }

    @ModelAttribute(name = Constant.HEADER)
    public Header buildHeader(HttpServletRequest request) {
        log.info("== Incoming request. method : {}, endpoint : {} ==", request.getMethod(), request.getRequestURI());
        log.info("== Query param : {} ==", request.getQueryString());

        return (Header) request.getAttribute(Constant.HEADER);
    }
}
