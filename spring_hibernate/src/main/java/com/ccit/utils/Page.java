package com.ccit.utils;


import java.util.List;

public class Page<T> {
    private Integer start;
    private List<T> items;
    private Integer totalPage;
    private Integer size;
    private Integer pageNum;

    public Page(Integer pageNum,Integer count,Integer size){
        this.size = size;
        this.totalPage = count/size;


        if(count%size != 0){
            this.totalPage ++;
        }

        if(pageNum <= 0){
            this.pageNum = 1;
        }
        else if(pageNum > this.totalPage){
            this.pageNum = this.totalPage;
        }
        else{
            this.pageNum = pageNum;
        }

        this.start = (this.pageNum-1)*5;
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

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
