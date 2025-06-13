package com.siakad.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Header {
    private Long userId;
    private String userFullName;
}
