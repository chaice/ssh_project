package com.ccit.utils;


public class ResultData {
    private String message;
    private Object data;

    public ResultData(Object data){
        this.message = "success";
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
