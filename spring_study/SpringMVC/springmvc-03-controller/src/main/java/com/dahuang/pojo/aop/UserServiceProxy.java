package com.dahuang.pojo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @author dahuang
 * @date 2021/10/2 12:29
 */
@Aspect
public class UserServiceProxy {

    @Before("execution(* com.dahuang.pojo.aop.UserService.userInfo())")
    public void before() throws Throwable {
        System.out.println("前置通知");
    }

    @AfterReturning("execution(* com.dahuang.pojo.aop.UserService.userInfo())")
    public void afterReturning() {
        System.out.println("后置通知");
    }

    @After("execution(* com.dahuang.pojo.aop.UserService.userInfo())")
    public void after() {
        System.out.println("最终通知");
    }

    @Around("execution(* com.dahuang.pojo.aop.UserService.userInfo())")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕之前.........");
        proceedingJoinPoint.proceed();
        System.out.println("环绕之后.........");
    }

    @AfterThrowing("execution(* com.dahuang.pojo.aop.UserService.userInfo())")
    public void afterThrowing() {
        System.out.println("异常通知");
    }

}
