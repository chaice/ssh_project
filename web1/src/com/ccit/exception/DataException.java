package com.ccit.exception;


public class DataException extends RuntimeException{
    public DataException(){

    }
    public DataException(String mes){
        super(mes);
    }
    public DataException(String mes , Exception e){
        super(mes , e);
    }
}
