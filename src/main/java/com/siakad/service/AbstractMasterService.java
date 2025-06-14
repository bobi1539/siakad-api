package com.siakad.service;

import com.siakad.entity.AbstractMasterEntity;
import com.siakad.helper.SpecificationHelper;
import com.siakad.model.dto.Header;
import com.siakad.repository.MasterRepository;
import org.springframework.data.jpa.domain.Specification;

public abstract class AbstractMasterService extends AbstractBaseService {

    protected <T extends AbstractMasterEntity, ID> void softDelete(
            MasterRepository<T, ID> repository,
            T entity,
            Header header
    ) {
        entity.setDeleted(true);
        setUpdatedBy(entity, header);
        repository.save(entity);
    }

    protected <T extends AbstractMasterEntity, ID> void restoreData(
            MasterRepository<T, ID> repository,
            T entity,
            Header header
    ) {
        entity.setDeleted(false);
        setUpdatedBy(entity, header);
        repository.save(entity);
    }

    protected void setDeleted(AbstractMasterEntity entity, Header header) {
        setCreatedBy(entity, header);
        setUpdatedBy(entity, header);
        entity.setDeleted(false);
    }

    protected <T> Specification<T> getSpecificationIsDeleted(Boolean isDeleted) {
        return SpecificationHelper.objectEquals(AbstractMasterEntity.FIELD_IS_DELETED, isDeleted);
    }
}
