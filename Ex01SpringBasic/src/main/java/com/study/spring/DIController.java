package com.study.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.study.spring.di.AnnotationBean;
import com.study.spring.di.Circle;
import com.study.spring.di.Person;

@Controller
public class DIController
{
	@RequestMapping("/di/mydi1.do")
	public ModelAndView mydi1(Model model) {
		ModelAndView mv = new ModelAndView();
		
		String configLocation = "classpath:my_di1.xml";
		AbstractApplicationContext ctx =
				new GenericXmlApplicationContext(configLocation);
		Circle circle = ctx.getBean("circle", Circle.class);
		
		mv.setViewName("04DI/mydi1");
		mv.addObject("circle", circle);
		
		return mv;
	}
	
	@RequestMapping("/di/mydi2.do")
	public ModelAndView mydi2(Model model) {
		ModelAndView mv = new ModelAndView();
		
		String configLocation = "classpath:my_di2.xml";
		AbstractApplicationContext ctx = 
				new GenericXmlApplicationContext(configLocation);
		Person person = ctx.getBean("person", Person.class);
		
		mv.setViewName("04DI/mydi2");
		mv.addObject("person", person.getInfo());
		
		return mv;
	}
	
	@RequestMapping("/di/mydi3.do")
	public ModelAndView mydi3(Model model) {
		ModelAndView mv = new ModelAndView();
		
		AbstractApplicationContext aCtx = 
				new AnnotationConfigApplicationContext(
						AnnotationBean.class);
		Circle circle1 = aCtx.getBean("circleBean", Circle.class);
		Person person1 = aCtx.getBean("personBean", Person.class);
		
		mv.setViewName("04DI/mydi3");
		mv.addObject("person", person1.getInfo());
		mv.addObject("circle", circle1);
		
		return mv;
	}
}
