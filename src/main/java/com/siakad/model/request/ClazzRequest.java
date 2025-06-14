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
public class ClazzRequest {

    @NotNull(message = Constant.CLAZZ_NAME_REQUIRED)
    @NotEmpty(message = Constant.CLAZZ_NAME_REQUIRED)
    private String name;

    @NotNull(message = Constant.GRADE_LEVEL_REQUIRED)
    private Integer gradeLevel;

    @NotNull(message = Constant.TEACHER_ID_REQUIRED)
    private Long homeroomTeacherId;
}
