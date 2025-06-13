package com.siakad.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
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
@MappedSuperclass
public abstract class AbstractBaseMasterEntity extends AbstractBaseEntity {

    @Column(name = "is_deleted")
    protected boolean isDeleted = false;

    public static final String FIELD_IS_DELETED = "isDeleted";
}
