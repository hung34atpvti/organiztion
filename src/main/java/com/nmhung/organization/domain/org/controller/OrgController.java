package com.nmhung.organization.domain.org.controller;

import com.nmhung.organization.common.controller.BaseController;
import com.nmhung.organization.domain.org.dto.OrgDto;
import com.nmhung.organization.domain.org.dto.OrgDtoNew;
import com.nmhung.organization.domain.org.dto.OrgMapper;
import com.nmhung.organization.domain.org.model.Org;
import com.nmhung.organization.domain.org.service.OrgService;
import com.nmhung.organization.exceptions.ErrorMessage;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/orgs")
@CrossOrigin("*")
public class OrgController extends BaseController {
    @Autowired
    OrgService service;

    @GetMapping(value = "/")
    public ResponseEntity<Page<OrgDtoNew>> getAll(
            @QuerydslPredicate(root = Org.class) Predicate predicate,
            @RequestParam(value = PAGE, defaultValue = "0") Integer page,
            @RequestParam(value = LIMIT, defaultValue = "20") Integer limit,
            @RequestParam(value = SORT, defaultValue = "") List<String> sort
    ) {
        if (sort.isEmpty()) {
            sort.add("name,asc");
        }
        var pageable = getPageable(page, limit, sort);
        Page<Org> pageOrg = service.findAll(predicate, pageable);
        return new ResponseEntity<>(new PageImpl<>(OrgMapper.toListOrgDtoNew(pageOrg.getContent()), pageable, pageOrg.getTotalElements()), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Org> create(@RequestBody @Valid OrgDto orgDto) {
        return new ResponseEntity<>(service.create(OrgMapper.toEntity(orgDto)), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrgDtoNew> getById(@PathVariable @NotNull Long id) {
        return new ResponseEntity<>(OrgMapper.toOrgDtoNew(service.findById(id)), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OrgDtoNew> update(
            @PathVariable @NotNull Long id,
            @RequestBody @Valid OrgDto orgDto
    ) {
        return new ResponseEntity<>(OrgMapper.toOrgDtoNew(service.update(id, OrgMapper.toEntity(orgDto))), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/{type}")
    public ResponseEntity<Boolean> delete(
            @PathVariable @NotNull Long id,
            @PathVariable @NotNull String type
    ) {
        if (type.equalsIgnoreCase("soft")) {
            return new ResponseEntity<>(service.softDelete(id), HttpStatus.OK);
        }
        if (type.equalsIgnoreCase("hard")){
            return new ResponseEntity<>(service.hardDelete(id), HttpStatus.OK);
        }
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, ErrorMessage.WRONG_TYPE_DELETE);
    }

}
