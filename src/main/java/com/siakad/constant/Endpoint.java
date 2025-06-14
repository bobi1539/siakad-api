package com.siakad.constant;

import com.siakad.exception.BusinessException;

public final class Endpoint {

    private Endpoint() {
        throw new BusinessException(GlobalMessage.CANNOT_INSTANCE_FINAL_CLASS);
    }

    public static final String BASE = "/api/v1";
    public static final String AUTH = BASE + "/auths";
    public static final String TEACHER = BASE + "/teachers";
    public static final String CLAZZ = BASE + "/classes";
}
