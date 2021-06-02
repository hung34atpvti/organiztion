package com.nmhung.organization.domain.org.dto;

import com.nmhung.organization.common.dto.BaseDtoNew;
import com.nmhung.organization.domain.org.model.Org;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class OrgMapper {

    public Org toEntity (OrgDto dto) {
        var entity = new Org();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setAddress(dto.getAddress());
        return entity;
    }

    public OrgDtoNew toOrgDtoNew(Org org) {
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

    public List<OrgDtoNew> toListOrgDtoNew(List<Org> orgs){
        return orgs.stream().map(this::toOrgDtoNew).collect(Collectors.toList());
    }
}
