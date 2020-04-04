package com.itranswarp.web.support;

import com.itranswarp.web.filter.HttpContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequireRoleAspect {

	@Around("@annotation(roleWith)")
	public Object checkSignIn(ProceedingJoinPoint joinPoint, RoleWith roleWith) throws Throwable {
		HttpContext.checkRole(roleWith.value());
		return joinPoint.proceed();
	}
}
