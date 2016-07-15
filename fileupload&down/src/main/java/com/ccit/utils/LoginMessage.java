package com.ccit.utils;


public class LoginMessage {
    private final String SUCCESS = "success";
    private String state;
    private String message;

    public LoginMessage(String message) {
        this.state = SUCCESS;
        this.message = message;
    }

    public LoginMessage(String error,String message) {
        this.state = error;
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LoginMessage{" +
                "state='" + state + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
