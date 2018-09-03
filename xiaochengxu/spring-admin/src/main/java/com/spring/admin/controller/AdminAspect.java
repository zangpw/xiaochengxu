package com.spring.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.spring.model.Admin;

@Component
@Aspect
public class AdminAspect {
	// 定义切点
	@Pointcut("execution(* com.spring.admin.controller.*.*(..)) && !execution(* com.spring.admin.controller.*.login(..))")
	public void simplePointcut() {
	}

	@Around("simplePointcut()")
	public Object aroundAdvice(ProceedingJoinPoint pjp) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		Object retVal = null;
		JSONObject jsonObj = new JSONObject();
		if (admin != null) {
			try {
				retVal = pjp.proceed();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			jsonObj.put("result", -2);
			jsonObj.put("msg", "管理员未登录");
			jsonObj.put("data", "[]");
			return jsonObj;
		}
		return retVal;
	}
}
