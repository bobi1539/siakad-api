package com.siakad.model.request;

import com.siakad.constant.Constant;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TeacherRequest {

    @NotNull(message = Constant.TEACHER_NAMES_REQUIRED)
    @NotEmpty(message = Constant.TEACHER_NAMES_REQUIRED)
    private String name;
}
