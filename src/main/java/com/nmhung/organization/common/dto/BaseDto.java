package com.nmhung.organization.common.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class BaseDto {
    protected Long id;
    protected Integer hashcode;
    protected Boolean activeInd;
    protected String createdBy;
    protected LocalDateTime createdDate;
    protected String updatedBy;
    protected LocalDateTime updatedDate;
    protected Long version;
}
