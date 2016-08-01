package com.ccit.action;


import com.ccit.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeAction {
    Logger logger = LoggerFactory.getLogger(HomeAction.class);
    private User user;

    public String home(){
        return "success";
    }
    public String input(){
        logger.debug("username:{}",user.getUsername());
        return "success";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
