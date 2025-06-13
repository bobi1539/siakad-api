package com.siakad.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@MappedSuperclass
public abstract class AbstractBaseEntity {

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    protected Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    protected Timestamp updatedAt;

    @Column(name = "created_by", updatable = false)
    protected Long createdBy;

    @Column(name = "updated_by")
    protected Long updatedBy;

    @Column(name = "created_by_name", updatable = false)
    protected String createdByName;

    @Column(name = "updated_by_name")
    protected String updatedByName;

    public static final String FIELD_CREATED_AT = "createdAt";
    public static final String FIELD_UPDATED_AT = "updatedAt";
    public static final String FIELD_CREATED_BY = "createdBy";
    public static final String FIELD_UPDATED_BY = "updatedBy";
    public static final String FIELD_CREATED_BY_NAME = "createdByName";
    public static final String FIELD_UPDATED_BY_NAME = "updatedByName";
}
