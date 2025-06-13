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
public class RefreshTokenRequest {

    @NotNull(message = Constant.REFRESH_TOKEN_REQUIRED)
    @NotEmpty(message = Constant.REFRESH_TOKEN_REQUIRED)
    private String refreshToken;
}
