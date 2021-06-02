package com.nmhung.organization.common.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseDtoNew {
    private Integer hashcode;
    private Boolean activeInd = Boolean.TRUE;
    private String createdBy;
    private LocalDateTime createdDate;
    private String updatedBy;
    private LocalDateTime updatedDate;
    private Long version;
}
