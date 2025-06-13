package com.siakad.helper;

import com.siakad.constant.GlobalMessage;
import com.siakad.exception.BusinessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public final class PageHelper {

    private PageHelper() {
        throw new BusinessException(GlobalMessage.CANNOT_INSTANCE_FINAL_CLASS);
    }

    public static Sort sortByColumnAsc(String columnName) {
        return Sort.by(columnName).ascending();
    }

    public static PageRequest buildPageRequest(int pageNumber, int pageSize, Sort sort) {
        return PageRequest.of(getPageNumber(pageNumber), pageSize, sort);
    }

    public static int getPageNumber(int pageNumber) {
        if (pageNumber < 0) {
            throw new BusinessException(GlobalMessage.PAGE_NUMBER_NOT_VALID);
        }
        return pageNumber;
    }
}
