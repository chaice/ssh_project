package com.ccit.pojo;


import java.io.Serializable;

public class Role implements Serializable {
    private static final long serialVersionUID = 265774450066941056L;
    private Integer id;
    private String rolename;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
