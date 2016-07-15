package com.ccit.pojo;


import java.io.Serializable;

public class UserLog implements Serializable{
    private static final long serialVersionUID = -8082427306247412048L;
    private Integer id;
    private Integer userid;
    private String logintime;
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String  getLogintime() {
        return logintime;
    }

    public void setLogintime(String  logintime) {
        this.logintime = logintime;
    }

    @Override
    public String toString() {
        return "UserLog{" +
                "id=" + id +
                ", userid=" + userid +
                ", logintime=" + logintime +
                ", ip='" + ip + '\'' +
                '}';
    }
}
