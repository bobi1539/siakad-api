package com.siakad.model.request;

import com.siakad.constant.Constant;
import com.siakad.constant.TeacherStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TeacherRequest {

    @NotNull(message = Constant.TEACHER_NAME_REQUIRED)
    @NotBlank(message = Constant.TEACHER_NAME_REQUIRED)
    private String name;

    @NotNull(message = Constant.TEACHER_STATUS_REQUIRED)
    private TeacherStatus status;

    private MultipartFile photo;
}
