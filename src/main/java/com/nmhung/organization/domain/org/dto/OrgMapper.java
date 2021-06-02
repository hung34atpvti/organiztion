package com.nmhung.organization.domain.org.dto;

import com.nmhung.organization.common.dto.BaseDtoNew;
import com.nmhung.organization.domain.org.model.Org;

import java.util.List;
import java.util.stream.Collectors;

public final class OrgMapper {
    private OrgMapper(){}

    public static Org toEntity(OrgDto dto) {
        var entity = new Org();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setAddress(dto.getAddress());
        return entity;
    }

    public static OrgDtoNew toOrgDtoNew(Org org) {
        var dto = new OrgDtoNew();
        dto.setId(org.getId());
        dto.setName(org.getName());
        dto.setAddress(org.getAddress());
        dto.setDescription(org.getDescription());
        dto.setBase(new BaseDtoNew());
        dto.getBase().setHashcode(org.getHashcode());
        dto.getBase().setCreatedBy(org.getCreatedBy());
        dto.getBase().setCreatedDate(org.getCreatedDate());
        dto.getBase().setUpdatedBy(org.getUpdatedBy());
        dto.getBase().setUpdatedDate(org.getUpdatedDate());
        dto.getBase().setActiveInd(org.getActiveInd());
        dto.getBase().setVersion(org.getVersion());
        return dto;
    }

    public static List<OrgDtoNew> toListOrgDtoNew(List<Org> orgs) {
        return orgs.stream().map(OrgMapper::toOrgDtoNew).collect(Collectors.toList());
    }
}
