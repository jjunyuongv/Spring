package com.study.spring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.spring.aop.SalesManVO;

@Controller
public class AopController
{
	@RequestMapping("/aop/main1.do")
	public String main1()
	{
		//xml설정파일의 위치를 선언
		/*
		classpath의 의미는 웹애플리케이션을 WAR로 배포했을때 classes
		폴더 하위로 패키지와 클래스, XML설정파일들이 위치하게 되는데 
		해당 디렉토리를 가리키는 예약어이다. */
		String xmlLocation = "classpath:AOPAppCtx1.xml";
		
		//xml설정파일을 기반으로 스프링 컨테이너 생성
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(xmlLocation);
		
		//컨테이너에서 생성된 빈을 주입받는다. 
		SalesManVO salesMan = ctx.getBean("salesMan", SalesManVO.class);
		//주입받은 빈의 정보를 출력한다.(여기서의 핵심기능(Core concern)) 
		salesMan.getSalesManView();
		
		//컨테이너 자원해제
		ctx.close();
		//뷰를 반환
		return "09AOP/main1";
	}
	
	@RequestMapping("/aop/main2.do")
	public String main2()
	{
		//xml설정파일을 기반으로 스프링 컨테이너 생성
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:AOPAppCtx2.xml");
		
		//컨테이너에서 생성한 빈을 주입받음
		SalesManVO salesMan = ctx.getBean("salesMan", SalesManVO.class);
		salesMan.getSalesManView();
		
		//컨테이너 자원해제 및 뷰 호출
		ctx.close();
		return "09AOP/main2";
	}
}
