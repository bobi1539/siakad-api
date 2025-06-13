package com.siakad.helper;

import com.siakad.constant.Constant;
import com.siakad.constant.GlobalMessage;
import com.siakad.exception.BusinessException;
import org.springframework.data.jpa.domain.Specification;

public final class SpecificationHelper {

    private SpecificationHelper() {
        throw new BusinessException(GlobalMessage.CANNOT_INSTANCE_FINAL_CLASS);
    }

    public static <T> Specification<T> stringLike(String attribute, String value) {
        if (StringHelper.isEmpty(value)) return null;
        return (root, query, cb) -> cb.like(
                cb.lower(root.get(attribute)), StringHelper.queryLike(value.toLowerCase())
        );
    }

    public static <T> Specification<T> objectEquals(String attribute, Object value) {
        if (value == null) return null;
        return (root, query, cb) -> cb.equal(root.get(attribute), value);
    }

    public static <T> Specification<T> entityIdEquals(String attribute, Long value) {
        if (value == null) return null;
        return (root, query, cb) -> cb.equal(
                root.get(attribute).get(Constant.ID), value
        );
    }
}
