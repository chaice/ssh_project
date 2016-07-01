package com.ccit.pojo;

import com.ccit.interface_dao.DAO;

public class User {
    private DAO dao;
    private DAO dao2;
    public String bookName;


    public User(DAO dao) {
        this.dao = dao;
        System.out.println("这是构造方法！");
    }

//    public void setBookName(String bookName) {
//        this.bookName = bookName;
//    }
//
    public void setUserDAO(DAO dao) {
        this.dao = dao;
    }
    public void getUser(){
        dao.save();
    }
}
