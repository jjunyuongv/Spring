package com.study.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;

//공통기능을 수행할 클래스 정의 
public class CommonAOP1
{
	public Object runTimeAOP(ProceedingJoinPoint jointPoint) throws Throwable
	{
		/*
		현재 호출되는 메서드명을 문자열로 변환후 반환해준다. 즉 실행되는
		메서드명을 알수있다. 
		 */
		String joinSignStr = jointPoint.getSignature().toShortString();
		Object obj=null;
		
		//advice를 around로 지정시 공통기능 수행부분
		//핵심기능 수정전 출력됨
		System.out.println("핵심 기능1 "+joinSignStr + " 실행 전");
		
		//시스템에서 현재 시간을 밀리세컨즈 단위로 가져온다. 
		long startTime = System.currentTimeMillis();
		
		try
		{
			/*
			매개변수를 통해서 핵심기능을 수행한다. proceed()메서드 호출을
			통해 수행할수 있고, 이와같이 핵심기능을 수행하는 것을 proxy
			라고 한다. 
			 */
			obj = jointPoint.proceed();
		} catch (Exception e)
		{
			System.out.println("핵심기능 실행중 예외발생");
			e.printStackTrace();
		} finally {
			//핵심기능 수행 후 시스템 시간을 가져온다. 
			long endTime = System.currentTimeMillis();
			System.out.println("핵심기능1 " + joinSignStr + " 실행 후");
			//핵심기능의 수행된 시간을 로그에 출력한다. 
			System.out.println(joinSignStr + " 실행시간 : " +(endTime-startTime));
			System.out.println();
		}
		
		return obj;
	}
}
