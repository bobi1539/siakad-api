package com.siakad.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JwtComponent {
    private String userId;
    private String userEmail;
    private String userFullName;
}
