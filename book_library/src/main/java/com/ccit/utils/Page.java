package com.ccit.utils;

import java.util.List;

public class Page<T> {
    private Integer page;//page: 页数
    private Integer size ;//size:每页显示的数量
    private Integer start;//从哪开始
    private List<T> items;//
    private Integer count;//count:总数量
    private Integer totalPage;
    /**
     *
     * @param page 显示哪一页
     * @param size  每页显示的数量
     * @param count  总共有多少个
     */
    public Page(Integer page, Integer size, Integer count) {
        this.page = page;
        this.size = size;
        this.count = count;
        totalPage = count/size;
        if(count % size != 0){
            totalPage ++;
        }
        if(this.page <= 0){
           this.page = 1;
        }
        if(this.page > totalPage){
            this.page = totalPage;
        }
        this.start = (this.page-1)*size;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getStart() {
        return start;
    }

    public List<T> getItems() {
        return items;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
