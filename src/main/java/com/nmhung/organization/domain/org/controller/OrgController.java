package com.nmhung.organization.domain.org.controller;

import com.nmhung.organization.common.controller.BaseController;
import com.nmhung.organization.common.paging.PageDto;
import com.nmhung.organization.domain.org.dto.OrgDto;
import com.nmhung.organization.domain.org.dto.OrgDtoNew;
import com.nmhung.organization.domain.org.dto.OrgMapper;
import com.nmhung.organization.domain.org.model.Org;
import com.nmhung.organization.domain.org.service.OrgService;
import com.nmhung.organization.utils.PageableExecutionUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/orgs")
@CrossOrigin("*")
public class OrgController extends BaseController {
    @Autowired
    OrgService service;
    @Autowired
    PageableExecutionUtils pageableExecutionUtils;

    @GetMapping(value = "/")
    public ResponseEntity<PageDto<OrgDtoNew>> getAll(
            @QuerydslPredicate(root = Org.class) Predicate predicate,
            @RequestParam(value = PAGE, defaultValue = "0") Integer page,
            @RequestParam(value = LIMIT, defaultValue = "20") Integer limit,
            @RequestParam(value = SORT, defaultValue = "") List<String> sort
    ) {
        if (sort.isEmpty()) {
            sort.add("name,asc");
        }
        Pageable pageable = getPageable(page, limit, sort);
        Page<Org> pageOrg = service.findAll(predicate, pageable);
        int totalItems = service.countAll();
        return new ResponseEntity<>(pageableExecutionUtils.getPage(OrgMapper.toListOrgDtoNew(pageOrg.getContent()), pageable, totalItems), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Org> create(@RequestBody @Valid OrgDto orgDto) {
        return new ResponseEntity<>(service.create(OrgMapper.toEntity(orgDto)), HttpStatus.OK);
    }
}
