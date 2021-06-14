package com.nmhung.organization.common.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class BaseController {

    public static final String PAGE = "PAGE";
    public static final String LIMIT = "LIMIT";
    public static final String SORT = "SORT";

    protected Pageable getPageable(int page, int limit, List<String> sort) {
        var splitRegex = ",";
        page = Math.max(page, 0);
        limit = limit <= 0 ? 20 : limit;

        List<Sort.Order> orders = new ArrayList<>();
        for (String order : sort) {
            String[] sortFieldAndOrderBy = order.split(splitRegex);
            String sortField = sortFieldAndOrderBy[0];
            if (Objects.isNull(sortField) || sortField.isEmpty() || sortField.isBlank() || !Objects.equals(2, sortFieldAndOrderBy.length)) {
                break;
            }
            String orderBy = sortFieldAndOrderBy[1];
            if (orderBy.isBlank() || orderBy.isEmpty()) {
                orders.add(new Sort.Order(Sort.Direction.ASC, sortField));
            } else {
                orders.add(new Sort.Order(orderBy.toUpperCase().contains("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField));
            }
        }
        return PageRequest.of(page, limit, Sort.by(orders));
    }
}
