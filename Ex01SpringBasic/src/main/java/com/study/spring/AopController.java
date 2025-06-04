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
	public String main1() {
		String xmlLocation = "classpath:AOPAppCtx1.xml";
		AbstractApplicationContext ctx =
			new GenericXmlApplicationContext(xmlLocation);
		
		SalesManVO salesMan = ctx.getBean("salesMan", SalesManVO.class);
		salesMan.getSalesManView();
		
		ctx.close();
		return "09AOP/main1";
	}
	
	@RequestMapping("/aop/main2.do")
	public String main2() {
		AbstractApplicationContext ctx =
				new GenericXmlApplicationContext("classpath:AOPAppCtx2.xml");
		
		SalesManVO salesMan = ctx.getBean("salesMan", SalesManVO.class);
		salesMan.getSalesManView();
		
		ctx.close();
		return "09AOP/main2";
	}
	
	
}
