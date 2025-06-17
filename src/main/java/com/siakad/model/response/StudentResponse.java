package com.siakad.model.response;

import com.siakad.entity.MStudent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class StudentResponse extends AbstractMasterEntityResponse {
    private Long id;
    private String fullName;
    private String nisn;
    private String nis;
    private LocalDate birthDate;
    private String email;
    private String phoneNumber;
    private String address;
    private String gender;
    private String status;
    private ClazzResponse currentClazz;

    public static StudentResponse build(MStudent student) {
        if (student == null) return null;

        StudentResponse response = StudentResponse.builder()
                .id(student.getId())
                .fullName(student.getFullName())
                .nisn(student.getNisn())
                .nis(student.getNis())
                .birthDate(student.getBirthDate())
                .email(student.getEmail())
                .phoneNumber(student.getPhoneNumber())
                .address(student.getAddress())
                .gender(student.getGender().label)
                .status(student.getStatus().label)
                .currentClazz(ClazzResponse.build(student.getCurrentClazz()))
                .build();
        setMasterEntity(response, student);
        return response;
    }
}
