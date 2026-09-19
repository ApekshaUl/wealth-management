package com.wealthgame.backend.ioc;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.wealthgame.backend.ioc.UserService.createUser(..))")
    public void createName(){}

    @Pointcut("execution(* com.wealthgame.backend.ioc.UserService.deleteUser(..))")
    public void deleteName(){}
    @Around("createName() || deleteName()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable
    {

        System.out.println("Before Around "+joinPoint.getSignature().getName());
        long start = System.nanoTime();
        Object result = joinPoint.proceed();
        long end = System.nanoTime();
        System.out.println("After method "+joinPoint.getSignature().getName());
        System.out.println("Execution " +(end-start));
        return result;
    }
//    @Before("execution(* com.wealthgame.backend.ioc.UserService.*(..))")
//    public void logBefore()
//    {
//        System.out.println("Before UserService");
//    }
//    @After("execution(* com.wealthgame.backend.ioc.UserService.*(..))")
//    public void logAfter()
//    {
//        System.out.println("After UserService");
//    }
//    @AfterReturning(pointcut = "execution(* com.wealthgame.backend.ioc.UserService.*(..))",
//    returning="result")
//    public void returnAfter()
//    {
//        System.out.println("After returning Service ");
//    }
//    @AfterThrowing(pointcut = "execution(* com.wealthgame.backend.ioc.UserService.*(..))",
//            throwing="ex")
//    public void throwAfter(Exception ex)
//    {
//        System.out.println("After throwing Service "+ ex.getMessage());
//    }


}
