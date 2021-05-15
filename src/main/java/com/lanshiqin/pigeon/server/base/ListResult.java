package com.lanshiqin.pigeon.server.base;

import java.util.List;

/**
 * 列表响应结果
 *
 * @author 蓝士钦
 */
public class ListResult<T> {
    private Long totalItem;
    private Long totalPage;
    private List<T> records;

    public ListResult(List<T> records){
        this.records=records;
    }

    public ListResult(){
    }

    public Long getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(final Long totalItem) {
        this.totalItem = totalItem;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(final Long totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(final List<T> records) {
        this.records = records;
    }
}
