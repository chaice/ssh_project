package com.ccit.advice;


import org.aspectj.lang.ProceedingJoinPoint;

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
    public void aroundAdvice(ProceedingJoinPoint pjp){
        try {
            System.out.println("这是前置通知");
            Object o = pjp.proceed();
            System.out.println("这是后置通知");
        } catch (Throwable throwable) {
            System.out.println("这是异常通知");
            throwable.printStackTrace();
        } finally {
            System.out.println("这是最终通知");
        }
    }
}
