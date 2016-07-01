package com.ccit.advice;


public class MyAspect {
    public void beforeAdvice(){
        System.out.println("这是前置通知");
    }
    public void afterReturningAdvice(){
        System.out.println("这是后置通知");
    }
    public void afterThrowingAdvice(){
        System.out.println("这是异常通知");
    }
    public void afterAdvice(){
        System.out.println("这是最终通知");
    }
}
