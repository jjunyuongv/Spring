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
	public ModelAndView mydi1(Model model)
	{
		//모델객체에 속성저장과 뷰경로를 동시에 설정하는 클래스
		ModelAndView mv = new ModelAndView();
		
		//XML 설정파일의 경로를 설정한다.(클래스패스는 차후 프로젝트를
		//배포했을때 classes하위로 이동된다.)
		String configLocation = "classpath:my_di1.xml";
		
		//스프링 컨테이너를 생성한다. 
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		
		//컨테이너에 생성된 빈을 getBean() 메서드를 통해 주입(Injection)받는다.
		Circle circle = ctx.getBean("circle", Circle.class);
		
		//뷰의 경로 및 모델객체에 속성을 저장한후 반환한다. 
		mv.setViewName("04DI/mydi1");
		mv.addObject("circle", circle);
		
		return mv;
	}
	
	@RequestMapping("/di/mydi2.do")
	public ModelAndView mydi2(Model model)
	{
		ModelAndView mv = new ModelAndView();
		
		//XML설정파일을 기반으로 스프링 컨테이너를 생성한다. 
		String configLocation = "classpath:my_di2.xml";
		
		//스프링 컨테이너를 생성한다. 
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		
		//스프링 컨테이너에서 자바빈을 주입받는다. 
		Person person = ctx.getBean("person", Person.class);
		
		//뷰설정 및 반환
		mv.setViewName("04DI/mydi2");
		mv.addObject("person", person.getInfo());
		
		return mv;
	}
	
	//어노테이션을 통한 빈생성 및 주입받기
	@RequestMapping("/di/mydi3.do")
	public ModelAndView mydi3(Model model)
	{
		ModelAndView mv = new ModelAndView();
		
		//어노테이션을 통해 생성된 빈을 주입받기 위해 스프링 컨테이너를
		//생성한다. 이때 어노테이션을 통해 작성한 클래스를 인수로 설정한다.
		AnnotationConfigApplicationContext aCtx = new AnnotationConfigApplicationContext(AnnotationBean.class);
		
		//메서드명을 통해 생성한 빈을 주입받는다. XML파일은 id속성, 
		//어노테이션을 사용할때는 메서드명을 사용한다. 
		Circle circle1 = aCtx.getBean("circleBean", Circle.class);
		Person person1 = aCtx.getBean("personBean", Person.class);
		
		//모델객체에 저장한 후 뷰를 반환한다. 
		mv.setViewName("04DI/mydi3");
		mv.addObject("person", person1.getInfo());
		mv.addObject("circle", circle1);
		
		return mv;
	}
}
