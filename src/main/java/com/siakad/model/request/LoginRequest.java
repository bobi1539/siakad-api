package com.siakad.model.request;

import com.siakad.constant.Constant;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginRequest {

    @Email(message = Constant.EMAIL_NOT_VALID)
    @NotNull(message = Constant.EMAIL_REQUIRED)
    @NotBlank(message = Constant.EMAIL_REQUIRED)
    private String email;

    @NotNull(message = Constant.PASSWORD_REQUIRED)
    @NotBlank(message = Constant.PASSWORD_REQUIRED)
    private String password;
}
