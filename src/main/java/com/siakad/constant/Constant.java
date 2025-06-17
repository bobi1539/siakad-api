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
    public static final String FILE_NOT_VALID = "File Tidak Valid";
    public static final String FILE_NOT_ALLOWED = "File Tidak Diizinkan";
    public static final String UNAUTHORIZED = "Unauthorized";
    public static final String FORBIDDEN = "Forbidden";
    public static final String CANNOT_INSTANCE_FINAL_CLASS = "Cannot Instance Final Class";
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";

    public static final String EMAIL_NOT_VALID = "Email Tidak Valid";
    public static final String EMAIL_REQUIRED = "Email Tidak Boleh Kosong";
    public static final String PASSWORD_REQUIRED = "Password Tidak Boleh Kosong";
    public static final String REFRESH_TOKEN_REQUIRED = "Refresh Token Tidak Boleh Kosong";
    public static final String TEACHER_NAME_REQUIRED = "Nama Guru Tidak Boleh Kosong";
    public static final String CLAZZ_NAME_REQUIRED = "Nama Kelas Tidak Boleh Kosong";
    public static final String GRADE_LEVEL_REQUIRED = "Tingkat Kelas Tidak Boleh Kosong";
    public static final String TEACHER_ID_REQUIRED = "ID Guru Tidak Boleh Kosong";
    public static final String TEACHER_STATUS_REQUIRED = "Status Guru Tidak Boleh Kosong";
    public static final String TEACHER_PHOTO_REQUIRED = "Foto Guru Tidak Boleh Kosong";
    public static final String STUDENT_NAME_REQUIRED = "Nama Siswa Tidak Boleh Kosong";
    public static final String NISN_REQUIRED = "NISN Tidak Boleh Kosong";
    public static final String NIS_REQUIRED = "NIS Tidak Boleh Kosong";
    public static final String BIRTH_DATE_REQUIRED = "Tanggal Lahir Tidak Boleh Kosong";
    public static final String PHONE_NUMBER_REQUIRED = "No Handphone Tidak Boleh Kosong";
    public static final String PHONE_NUMBER_NOT_VALID = "No Handphone Tidak Valid";
    public static final String ADDRESS_REQUIRED = "Alamat Tidak Boleh Kosong";
    public static final String GENDER_REQUIRED = "Jenis Kelamin Tidak Boleh Kosong";
    public static final String STUDENT_STATUS_REQUIRED = "Status Siswa Tidak Boleh Kosong";
    public static final String CLAZZ_ID_REQUIRED = "ID Kelas Tidak Boleh Kosong";

    public static final String USER = "Data User";
    public static final String TEACHER = "Data Guru";
    public static final String CLAZZ = "Data Kelas";
    public static final String STUDENT = "Data Siswa";

    public static final String ERROR = "Error : {}";
    public static final String ID = "id";
    public static final String HEADER = "header";
    public static final String HEADER_INPUT_STREAM = "attachment; filename=";
    public static final String AUTHORIZATION = "Authorization";
    public static final String JWT = "JWT";
    public static final String BEARER = "Bearer";
    public static final String APPLICATION_JSON = "application/json";
    public static final String UTF_8 = "UTF-8";
    public static final int DEFAULT_PAGE = 0;
    public static final int DEFAULT_SIZE = 10;
    public static final String DOT = ".";
}
