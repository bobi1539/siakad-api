package com.siakad.constant;

import com.siakad.exception.BusinessException;

public final class Constant {

    private Constant() {
        throw new BusinessException(GlobalMessage.CANNOT_INSTANCE_FINAL_CLASS);
    }

    public static final String SUCCESS = "Sukses";
    public static final String PAGE_NUMBER_NOT_VALID = "Page Number Not Valid";
    public static final String CANNOT_DELETE_THIS_DATA = "Tidak Bisa Menghapus Data Ini Karena Sudah Digunakan Oleh Entitas Lain";
    public static final String WRONG_EMAIL_OR_PASSWORD = "Email Atau Password Salah";
    public static final String REFRESH_TOKEN_NOT_VALID = "Refresh Token Tidak Valid";
    public static final String UNAUTHORIZED = "Unauthorized";
    public static final String FORBIDDEN = "Forbidden";
    public static final String CANNOT_INSTANCE_FINAL_CLASS = "Cannot Instance Final Class";
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";

    public static final String EMAIL_REQUIRED = "Email Is Required";
    public static final String PASSWORD_REQUIRED = "Password Is Required";
    public static final String REFRESH_TOKEN_REQUIRED = "Refresh Token Is Required";

    public static final String ERROR = "Error : {}";
    public static final String ID = "id";
    public static final String HEADER = "header";
    public static final String HEADER_INPUT_STREAM = "attachment; filename=";
    public static final String AUTHORIZATION = "Authorization";
    public static final String JWT = "JWT";
    public static final String BEARER = "Bearer";
    public static final String APPLICATION_JSON = "application/json";
    public static final String UTF_8 = "UTF-8";
    public static final String USER = "User";
}
