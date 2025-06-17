package com.siakad.model.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetDirectoryResponse {
    private String directoryName;
}
