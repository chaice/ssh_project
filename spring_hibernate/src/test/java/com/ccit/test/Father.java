package com.ccit.test;


import java.lang.reflect.ParameterizedType;

public class Father<T,pk> {

    public Father(){
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        System.out.println(type.getActualTypeArguments()[0]);
    }
}
