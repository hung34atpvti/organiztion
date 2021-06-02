package com.nmhung.organization.utils;

import com.nmhung.organization.common.paging.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PageableExecutionUtils {

    public <T> Page<T> getPage(List<T> items, Integer page, Integer limit, int totalItems) {
        int currentPage = (page == null) ? 0 : page;
        int noOfItemsPerPage = (limit == null) ? Integer.MAX_VALUE : limit;
        int totalPage = (int) Math.ceil((double) totalItems / noOfItemsPerPage);
        return new Page<T>(currentPage,totalPage,totalItems,items);
    }
}
