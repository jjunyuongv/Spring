package com.study.spring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
}
