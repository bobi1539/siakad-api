package com.siakad.model.response;

import com.siakad.entity.AbstractBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class AbstractBaseEntityResponse {
    protected Timestamp createdAt;
    protected Timestamp updatedAt;
    protected Long createdBy;
    protected Long updatedBy;
    protected String createdByName;
    protected String updatedByName;

    public static void setBaseEntity(AbstractBaseEntityResponse response, AbstractBaseEntity entity) {
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        response.setCreatedBy(entity.getCreatedBy());
        response.setUpdatedBy(entity.getUpdatedBy());
        response.setCreatedByName(entity.getCreatedByName());
        response.setUpdatedByName(entity.getUpdatedByName());
    }
}
