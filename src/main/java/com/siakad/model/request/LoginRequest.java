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
public class LoginRequest {

    @NotNull(message = Constant.EMAIL_REQUIRED)
    @NotEmpty(message = Constant.EMAIL_REQUIRED)
    private String email;

    @NotNull(message = Constant.PASSWORD_REQUIRED)
    @NotEmpty(message = Constant.PASSWORD_REQUIRED)
    private String password;
}
