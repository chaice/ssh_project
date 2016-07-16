package com.ccit.pojo;

import java.sql.Timestamp;

public class SalesLog {
    public static final String TYPE_AUTO = "auto";
    public static final String TYPE_HAND = "hand";
    private Integer id;
    private Integer salesid;
    private Timestamp followtime;
    private String type;
    private String context;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSalesid() {
        return salesid;
    }

    public void setSalesid(Integer salesid) {
        this.salesid = salesid;
    }

    public Timestamp getFollowtime() {
        return followtime;
    }

    public void setFollowtime(Timestamp followtime) {
        this.followtime = followtime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
