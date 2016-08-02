package com.ccit.utils;

import java.util.List;


public class Page<T> {

    private Integer pageNum;
    private Integer start;
    private List<T> items;
    private Integer totalPage;
    private Integer size;

    /**
     *
     * @param p  页数
     * @param count 总数量
     * @param size  每页显示多少个
     */
    public Page(Integer p , Integer count , Integer size ){
        this.totalPage = count/size;
        this.size = size;

        this.pageNum = p;

        if(count%size != 0){
            this.totalPage ++;
        }
        if(p <= 0){
            this.pageNum = 1;
        }
        if(p > this.totalPage){
            this.pageNum = this.totalPage;
        }

        this.start = (this.pageNum - 1)*size;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
