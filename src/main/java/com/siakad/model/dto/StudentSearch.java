package com.siakad.model.dto;

import com.siakad.constant.Gender;
import com.siakad.constant.StudentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class StudentSearch extends Search {
    private Gender gender;
    private StudentStatus status;
}
