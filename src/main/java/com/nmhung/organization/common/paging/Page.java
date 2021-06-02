package com.nmhung.organization.common.paging;

import lombok.Data;
import org.springframework.stereotype.Component;


import java.util.List;

@Data
public class Page<T> {
    int currentPage;
    int totalPage;
    int totalItems;
    List<T> items;

    public Page(int currentPage, int totalPage, int totalItems, List<T> items) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.totalItems = totalItems;
        this.items = items;
    }
}
