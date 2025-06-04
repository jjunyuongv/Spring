package com.study.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CommonAOP2
{
	@Pointcut("within(com.study.spring.aop.*)")
	private void pointcutMethod() {}
	
	@Before("within(com.study.spring.aop.*)")
	public void beforeAdvice() {
		System.out.println("beforeAdvice() 메소드 실행");
	}
	
	@After("within(com.study.spring.aop.*)")
	public void afterAdvice() {
		System.out.println("afterAdvice() 메소드 실행");
	}
	
	@Around("pointcutMethod()")
	public Object runTimeAOP(ProceedingJoinPoint jointPoint) 
		throws Throwable{
		String joinSignStr = jointPoint.getSignature().toShortString();
		Object obj = null;
		
		System.out.println("핵심기능2 "+ joinSignStr +" 실행전");
		long startTime = System.currentTimeMillis();
		
		try
		{
			obj = jointPoint.proceed();
		} catch (Exception e)
		{
			System.out.println("핵심기능 실행중 예외발생");
			e.printStackTrace();
		}
		finally {
			long endTime = System.currentTimeMillis();
			System.out.println("핵심기능2 "+ joinSignStr +" 실행후");
			System.out.println(joinSignStr +" 실행기간 : "+
					(endTime-startTime));
			System.out.println();
		}
		return obj;
	}
}
