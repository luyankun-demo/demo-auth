package com.demo.auth.common.bean;

import lombok.ToString;

import java.util.List;

@ToString
public class PageBean {

    private int currentPage; // 当前页
    private int offset; // 起始条数
    private int pageSize; // 页面显示条数
    private long count; // 总数据条数
    private int firstPage; // 首页
    private int pageCount; // 总页数
    private int backPage; // 上一页
    private int nextPage; // 下一页
    private List<?> list; // 数据结果集

    public PageBean(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        // 默认起始页
        this.firstPage = 1;
        // 初始化起始条数
        this.offset = (this.currentPage - 1) * this.pageSize;
        // 设置上一页
        this.backPage = this.currentPage - 1 < this.firstPage ? this.currentPage : this.currentPage - 1;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getCount() {
        return this.count;
    }

    public <T> void setList(List<T> list) {
        this.list = list;
    }

    public  List<?> getList() {
        return this.list;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getOffset() {
        return this.offset;
    }

    public int getBackPage() {
        return this.backPage;
    }

    public int getPageCount() {
        return (int)(this.count - 1)/this.pageSize + 1;
    }

    public int getFirstPage() {
        return this.firstPage;
    }

    public int getNextPage() {
        return this.currentPage + 1 > getPageCount() ? this.currentPage : this.currentPage + 1;
    }
}
