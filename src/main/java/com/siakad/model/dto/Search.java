package com.siakad.model.dto;

import com.siakad.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Search {
    private String value;
    private Boolean isDeleted;
    private int page = Constant.DEFAULT_PAGE;
    private int size = Constant.DEFAULT_SIZE;
}
