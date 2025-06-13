package com.siakad.service;

import com.siakad.entity.AbstractBaseMasterEntity;
import com.siakad.helper.SpecificationHelper;
import com.siakad.model.dto.Header;
import org.springframework.data.jpa.domain.Specification;

public abstract class AbstractMasterService extends AbstractBaseService {

    protected void setDeleted(AbstractBaseMasterEntity entity, Header header) {
        setCreatedBy(entity, header);
        setUpdatedBy(entity, header);
        entity.setDeleted(false);
    }

    protected <T> Specification<T> getSpecificationIsDeleted(Boolean isDeleted) {
        return SpecificationHelper.objectEquals(AbstractBaseMasterEntity.FIELD_IS_DELETED, isDeleted);
    }
}
