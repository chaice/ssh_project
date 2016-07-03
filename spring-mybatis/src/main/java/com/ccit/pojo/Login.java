package com.ccit.pojo;

public class Login {
    private Integer id;
    private String ip;
    private Integer userid;

    public Login() {
    }

    public Login(String ip, Integer userid) {
        this.ip = ip;
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
