package com.ccit.utils;


import java.util.List;

public class Page<T> {
    private Integer pageNum;
    private Integer start;
    private Integer size;
    private Integer count;
    private List<T> items;
    public Page(Integer pageNum,Integer size,Integer count){
        this.pageNum = pageNum;
        this.size = size;
        Integer totalPage = count/size;
        if(count%size != 0){
            totalPage ++;
        }
        if(pageNum>totalPage){
           this.pageNum = totalPage;
        }
        if(pageNum <= 0){
            this.pageNum = 1;
        }
        this.start = (this.pageNum -1)*size;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getCount() {
        return count;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
