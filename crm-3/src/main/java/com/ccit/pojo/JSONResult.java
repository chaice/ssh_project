package com.ccit.pojo;


import java.util.List;

public class JSONResult<T> {
    public static final String MEG_SUCCESS = "success";
    public static final String MEG_FAIL = "fail";
    private String message;
    private T data;
    private List<T> list;
    public JSONResult(List list){
        this.message = MEG_SUCCESS;
        this.list = list;
    }
    public JSONResult(T data){
        this.message = MEG_SUCCESS;
        this.data = data;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
