package com.spring.front.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class LogAspect {
	
	private final static Logger logger = LoggerFactory.getLogger(LogAspect.class);
	
	// 定义切点
	@Pointcut("execution(* com.spring.front.controller.*.*(..))")
	public void simplePointcut() {
	}
	@Around("simplePointcut()")
	public Object aroundAdvice(ProceedingJoinPoint pjp){
	    String declaringTypeName = pjp.getSignature().getDeclaringTypeName();
        String method = pjp.getSignature().getName();
        try {
            System.out.println("开始执行： "+declaringTypeName+"."+method);
            Object retVal = pjp.proceed();
            HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session =request.getSession();
            System.out.println(session.getAttribute("admin"));
            System.out.println("成功执行： "+declaringTypeName+"."+method);
            System.out.println(retVal);
            return retVal;
        } catch (Throwable e) {
        	System.out.println("执行失败： "+declaringTypeName+"."+method);
        }
		return method;
	}
}
