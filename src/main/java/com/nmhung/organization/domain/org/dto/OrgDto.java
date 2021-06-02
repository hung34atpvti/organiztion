package com.nmhung.organization.domain.org.dto;

import com.nmhung.organization.common.dto.BaseDto;
import lombok.Data;

@Data
public class OrgDto extends BaseDto {
    private String name;
    private String description;
    private String address;
}
