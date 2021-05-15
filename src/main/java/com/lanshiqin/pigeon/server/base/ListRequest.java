package com.lanshiqin.pigeon.server.base;

/**
 * 列表请求
 *
 * @author 蓝士钦
 */
public class ListRequest {
    private Long pageNo;
    private Long pageSize;
    private String sortField;
    private String sort;

    public Long getPageNo() {
        return pageNo;
    }

    public void setPageNo(final Long pageNo) {
        this.pageNo = pageNo;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(final Long pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(final String sortField) {
        this.sortField = sortField;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(final String sort) {
        this.sort = sort;
    }
}
