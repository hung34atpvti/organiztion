package com.nmhung.organization.common.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

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
            String sortField = order.split(splitRegex)[0];
            String orderBy = order.split(splitRegex)[1];
            if (orderBy == null || orderBy.isBlank() || orderBy.isEmpty()) {
                orders.add(new Sort.Order(Sort.Direction.ASC, sortField));
            } else {
                orders.add(new Sort.Order(orderBy.toUpperCase().contains("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField));

            }
        }
        return PageRequest.of(page, limit, Sort.by(orders));
    }
}
