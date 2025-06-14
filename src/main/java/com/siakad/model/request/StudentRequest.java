package com.siakad.model.request;

import com.siakad.constant.Constant;
import com.siakad.constant.Gender;
import com.siakad.constant.StudentStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StudentRequest {

    @NotNull(message = Constant.STUDENT_NAME_REQUIRED)
    @NotBlank(message = Constant.STUDENT_NAME_REQUIRED)
    private String fullName;

    @NotNull(message = Constant.NISN_REQUIRED)
    @NotBlank(message = Constant.NISN_REQUIRED)
    private String nisn;

    @NotNull(message = Constant.NIS_REQUIRED)
    @NotBlank(message = Constant.NIS_REQUIRED)
    private String nis;

    @NotNull(message = Constant.BIRTH_DATE_REQUIRED)
    private LocalDate birthDate;

    @Email(message = Constant.EMAIL_NOT_VALID)
    @NotNull(message = Constant.EMAIL_REQUIRED)
    @NotBlank(message = Constant.EMAIL_REQUIRED)
    private String email;

    @Pattern(message = Constant.PHONE_NUMBER_NOT_VALID, regexp = "^\\+?\\d{9,15}$")
    @NotNull(message = Constant.PHONE_NUMBER_REQUIRED)
    @NotBlank(message = Constant.PHONE_NUMBER_REQUIRED)
    private String phoneNumber;

    @NotNull(message = Constant.ADDRESS_REQUIRED)
    @NotBlank(message = Constant.ADDRESS_REQUIRED)
    private String address;

    @NotNull(message = Constant.GENDER_REQUIRED)
    private Gender gender;

    @NotNull(message = Constant.STUDENT_STATUS_REQUIRED)
    private StudentStatus status;

    @NotNull(message = Constant.CLAZZ_ID_REQUIRED)
    private Long currentClazzId;
}
