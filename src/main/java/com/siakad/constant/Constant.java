package com.siakad.constant;

import com.siakad.exception.BusinessException;

public final class Constant {

    private Constant() {
        throw new BusinessException(GlobalMessage.CANNOT_INSTANCE_FINAL_CLASS);
    }

    public static final String SUCCESS = "Sukses";
    public static final String UNAUTHORIZED = "Unauthorized";
    public static final String FORBIDDEN = "Forbidden";
    public static final String CANNOT_INSTANCE_FINAL_CLASS = "Cannot Instance Final Class";
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
}
