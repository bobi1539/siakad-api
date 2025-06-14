package com.siakad.model.request;

import com.siakad.constant.Constant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TeacherRequest {

    @NotNull(message = Constant.TEACHER_NAME_REQUIRED)
    @NotBlank(message = Constant.TEACHER_NAME_REQUIRED)
    private String name;
}
