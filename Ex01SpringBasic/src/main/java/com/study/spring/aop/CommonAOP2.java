package com.study.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

//공통기능으로 사용할 클래스임을 어노테이션을 통해 명시한다. 
@Aspect
public class CommonAOP2
{
	/*
	공통기능이 동작할 범위를 지정한다. 따라서 해당 메서드는 실행부가 
	없어도 상관없다. 또한 함수명이 id속성값이 된다. 
	 */
	@Pointcut("within(com.study.spring.aop.*)")
	private void pointcutMethod() {}
	
	//핵심기능 수행전 자동으로 호출되는 메서드로 정의
	@Before("within(com.study.spring.aop.*)")
	public void beforAdvice()
	{
		System.out.println("beforAdvice() 메소드 실행");
	}
	
	//핵심기능 수행후 자동으로 호출되는 메서드로 정의
	@After("within(com.study.spring.aop.*)")
	public void afterAdvice()
	{
		System.out.println("afterAdvice() 메소드 실행");
	}
	
	/* 공통기능으로 지정한 실제 메서드로 핵심기능 전/후/예외발생시 
	실행하겠다는 선언 */
	@Around("pointcutMethod()")
	public Object runTimeAOP(ProceedingJoinPoint jointPoint) throws Throwable
	{
		//현재 실행되는 메서드명을 얻어온다.
		String joinSignStr = jointPoint.getSignature().toShortString();
		Object obj=null;
		
		//핵심기능의 실행시간을 알아보기 위해 시작전/후의 시간을 얻어온다.
		System.out.println("핵심 기능2 "+joinSignStr + " 실행 전"); 
		long startTime = System.currentTimeMillis();
		
		try
		{
			//핵심기능을 실행한다.
			obj = jointPoint.proceed();
		} catch (Exception e)
		{
			System.out.println("핵심기능 실행중 예외발생");
			e.printStackTrace();
		} finally {
			//핵심기능 실행 후 실행시간을 계산하여 출력한다.
			long endTime = System.currentTimeMillis();
			System.out.println("핵심기능2 " + joinSignStr + " 실행 후");
			//핵심기능의 수행된 시간을 로그에 출력한다. 
			System.out.println(joinSignStr + " 실행시간 : " +(endTime-startTime));
			System.out.println();
		}
		
		return obj;
	}
}
