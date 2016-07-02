package com.ccit.exception;

/**
 * Created by cc on 2016/7/2.
 */
public class LoginException extends RuntimeException {
    public  LoginException(){

    }
    public  LoginException(String message){
         super(message);
    }
}
