package com.siakad.controller;

import com.siakad.constant.Constant;
import com.siakad.constant.GlobalMessage;
import com.siakad.model.dto.Header;
import com.siakad.model.dto.Search;
import com.siakad.model.response.BaseResponse;
import com.siakad.model.response.FileResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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

    protected ResponseEntity<InputStreamResource> buildResourceResponse(FileResponse response) {
        String contentDispositionValue = Constant.HEADER_INPUT_STREAM + response.getFileName();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDispositionValue)
                .contentType(response.getMediaType())
                .body(response.getResource());
    }

    protected Search buildSearch(String value, Boolean isDeleted, int page, int size) {
        return Search.builder()
                .value(value)
                .isDeleted(isDeleted)
                .page(page)
                .size(size)
                .build();
    }

    @ModelAttribute(name = Constant.HEADER)
    public Header buildHeader(HttpServletRequest request) {
        log.info("== Incoming request. method : {}, endpoint : {} ==", request.getMethod(), request.getRequestURI());
        log.info("== Query param : {} ==", request.getQueryString());

        return (Header) request.getAttribute(Constant.HEADER);
    }
}
