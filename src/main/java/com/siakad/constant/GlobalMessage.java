package com.siakad.constant;

import org.springframework.http.HttpStatus;

public enum GlobalMessage {

    SUCCESS(HttpStatus.OK, Constant.SUCCESS),

    PAGE_NUMBER_NOT_VALID(HttpStatus.BAD_REQUEST, Constant.PAGE_NUMBER_NOT_VALID),

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, Constant.UNAUTHORIZED),

    FORBIDDEN(HttpStatus.FORBIDDEN, Constant.FORBIDDEN),

    CANNOT_INSTANCE_FINAL_CLASS(HttpStatus.INTERNAL_SERVER_ERROR, Constant.CANNOT_INSTANCE_FINAL_CLASS),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, Constant.INTERNAL_SERVER_ERROR);

    public final HttpStatus httpStatus;
    public final String message;

    GlobalMessage(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
