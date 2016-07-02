package com.ccit.advice;

import com.ccit.exception.ServiceException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import javax.inject.Named;

@Aspect
@Named
public class MyAdvice {
    @Pointcut("execution(* com.ccit.mappers.*.*(..))")//切入点表达式
    public void xx(){

    }
    @Before("xx()")
    public void before(){
        System.out.println("这是前置通知！");
    }
    @AfterReturning(value = "xx()", returning = "o")
    public void afterReturning(Object o){
        System.out.println("这是后置通知！"+o);
    }
    @AfterThrowing(value = "xx()",throwing = "ex")
    public void afterThrowing(Exception ex){
        System.out.println("这是异常通知！"+ex);
    }
    @After("xx()")
    public void after(){
        System.out.println("这是最终通知！");
    }

    //环绕通知
    //@Around("xx()")
    public void around(ProceedingJoinPoint joinPoint){
        try{
            System.out.println("这是前置通知！！！");
            Object o = joinPoint.proceed();
            System.out.println("这是后置通知！！！" +o);
        }catch (Throwable e) {
            System.out.println("这是异常通知！！！");
            throw new ServiceException("异常通知"+e);
        }finally {
            System.out.println("这是最终通知！！！");
        }
    }
}
