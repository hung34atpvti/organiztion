package com.nmhung.organization.common.paging;

import lombok.Data;

import java.util.List;

@Data
public class PageDto<T> {
    int currentPage;
    int totalPage;
    int totalItems;
    List<T> items;

    public PageDto(int currentPage, int totalPage, int totalItems, List<T> items) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.totalItems = totalItems;
        this.items = items;
    }
}
