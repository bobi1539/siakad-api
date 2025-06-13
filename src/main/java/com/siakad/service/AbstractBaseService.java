package com.siakad.service;

import com.siakad.constant.GlobalMessage;
import com.siakad.entity.AbstractBaseMasterEntity;
import com.siakad.exception.BusinessException;
import com.siakad.helper.PageHelper;
import com.siakad.helper.StringHelper;
import com.siakad.model.dto.Header;
import com.siakad.model.dto.Search;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import java.util.Objects;
import java.util.function.Supplier;

public abstract class AbstractBaseService {

    protected static final String FIELD_ID = "id";
    protected static final String FIELD_USER = "user";

    protected Sort sortByIdAsc() {
        return PageHelper.sortByColumnAsc(FIELD_ID);
    }

    protected Sort sortByIsDeletedAndIdAsc() {
        Sort sortIsDeleted = PageHelper.sortByColumnAsc(AbstractBaseMasterEntity.FIELD_IS_DELETED);
        Sort sortId = PageHelper.sortByColumnAsc(FIELD_ID);
        return sortIsDeleted.and(sortId);
    }

    protected Pageable pageableSortByIdAsc(Search search) {
        return PageHelper.buildPageRequest(search.getPage(), search.getSize(), sortByIdAsc());
    }

    protected Pageable pageableSortByIsDeletedAndIdAsc(Search search) {
        return PageHelper.buildPageRequest(search.getPage(), search.getSize(), sortByIsDeletedAndIdAsc());
    }

    protected void setCreatedBy(AbstractBaseMasterEntity entity, Header header) {
        entity.setCreatedBy(header.getUserId());
        entity.setCreatedByName(header.getUserFullName());
    }

    protected void setUpdatedBy(AbstractBaseMasterEntity baseEntity, Header header) {
        baseEntity.setUpdatedBy(header.getUserId());
        baseEntity.setUpdatedByName(header.getUserFullName());
    }

    protected Supplier<BusinessException> notFoundException(String data) {
        return () -> new BusinessException(HttpStatus.BAD_REQUEST, StringHelper.notFoundFormat(data));
    }

    protected <T, ID> void hardDelete(JpaRepository<T, ID> repository, T entity) {
        try {
            repository.delete(entity);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException(GlobalMessage.CANNOT_DELETE_THIS_DATA);
        }
    }

    protected void validateUserId(Long userId, Header header) {
        if (!Objects.equals(userId, header.getUserId())) {
            throw new BusinessException(GlobalMessage.FORBIDDEN);
        }
    }
}
