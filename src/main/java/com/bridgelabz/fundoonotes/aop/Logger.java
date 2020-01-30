package com.bridgelabz.fundoonotes.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class Logger 
{
	  @Around("execution(* com.bridgelabz.fundoonotes.controller.*.*(*))") 
	  public Object commonMethod(ProceedingJoinPoint jointPoint) throws Throwable 
	  {
	  log.info("Before "+jointPoint.getSignature().getName()+" method of "+jointPoint.getSignature().getDeclaringType().getSimpleName());
	  Object retu=  jointPoint.proceed();
	  log.info("After "+jointPoint.getSignature().getName()+"  method of class"+jointPoint.getSignature().getDeclaringType().getSimpleName()); 
	  return retu;
	  }

}
