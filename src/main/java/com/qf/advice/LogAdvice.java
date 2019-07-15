package com.qf.advice;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;


/**
 * author:zxq
 * date:2019/6/29
 */
@Component
public class LogAdvice {

    Logger logger = Logger.getLogger(LogAdvice.class);

    public void before(JoinPoint joinPoint){
        String info = getInfo(joinPoint);

        logger.warn("方法进入：--》"+info);

    }

    public void after(JoinPoint joinPoint){
        String info = getInfo(joinPoint);

        logger.warn("方法结束：--》"+info);
    }

    public void afterThrowing(JoinPoint joinPoint, Throwable throwable){
        String message = throwable.getMessage();

        String info = getInfo(joinPoint);

        logger.error("程序错误:"+info+"-->"+message);

    }
    private String getInfo(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();

        String methodName = joinPoint.getSignature().getName();

        long time = System.currentTimeMillis();

        String info = "类名："+className + " -- 方法名："+methodName + " --时间："+time;

        return info;
    }
}
