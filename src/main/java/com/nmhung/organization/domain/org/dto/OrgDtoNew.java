package com.nmhung.organization.domain.org.dto;

import com.nmhung.organization.common.dto.BaseDtoNew;
import lombok.Data;

@Data
public class OrgDtoNew {
    private Long id;
    private String name;
    private String description;
    private String address;
    private BaseDtoNew base;
}
