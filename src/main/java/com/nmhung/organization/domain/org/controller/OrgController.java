package com.nmhung.organization.domain.org.controller;

import com.nmhung.organization.common.paging.Page;
import com.nmhung.organization.domain.org.dto.OrgDto;
import com.nmhung.organization.domain.org.dto.OrgDtoNew;
import com.nmhung.organization.domain.org.dto.OrgMapper;
import com.nmhung.organization.domain.org.model.Org;
import com.nmhung.organization.domain.org.service.OrgService;
import com.nmhung.organization.utils.PageableExecutionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/orgs")
@CrossOrigin("*")
public class OrgController {
    @Autowired
    OrgService service;
    @Autowired
    PageableExecutionUtils pageableExecutionUtils;
    @Autowired
    OrgMapper orgMapper;

    @GetMapping(value = "/")
    public ResponseEntity<Page<OrgDtoNew>> getAll(
            @RequestParam Integer page,
            @RequestParam Integer limit
    ) {
        List<Org> lstOrg = service.findAll();
        int totalItems = service.countAll();
        return new ResponseEntity<>(pageableExecutionUtils.getPage(orgMapper.toListOrgDtoNew(lstOrg), page, limit, totalItems), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Org> create(@RequestBody @Valid OrgDto orgDto) {
        return new ResponseEntity<>(service.create(orgMapper.toEntity(orgDto)), HttpStatus.OK);
    }
}
