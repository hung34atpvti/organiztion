package com.nmhung.organization.utils;

import com.nmhung.organization.common.paging.PageDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PageableExecutionUtils {

    public <T> PageDto<T> getPage(List<T> items, Pageable pageable, int totalItems) {
        if (pageable == null){
            return new PageDto<T>(0,(int) Math.ceil((double) totalItems / Integer.MAX_VALUE),totalItems,items);
        }
        int currentPage = pageable.getPageNumber();
        int totalPage = (int) Math.ceil((double) totalItems / pageable.getPageSize());
        return new PageDto<T>(currentPage,totalPage,totalItems,items);
    }
}
