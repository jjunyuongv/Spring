package com.study.spring;

import com.study.spring.di.Circle;
import com.study.spring.env.BoardVO;
import com.study.spring.env.ConnectionVO;
import com.study.spring.env.EnvApplicationConfig;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EnvironmentController
{

    private final Circle circleBean;

    EnvironmentController(Circle circleBean) {
        this.circleBean = circleBean;
    }
	@RequestMapping("/environment/main1.do")
	public String main1(Model model) {
		ConfigurableApplicationContext ctx = new GenericXmlApplicationContext();
		ConfigurableEnvironment env = ctx.getEnvironment();
		MutablePropertySources propertySources = env.getPropertySources();
		
		String adminIdStr = "";
		String adminPwStr = "";
		try
		{
			String envPath = "classpath:EnvAdmin.properties";
			propertySources.addLast(new ResourcePropertySource(envPath));
			adminIdStr = env.getProperty("admin.id");
			adminPwStr = env.getProperty("admin.pw");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		model.addAttribute("adminID", adminIdStr);
		model.addAttribute("adminPW", adminPwStr);
		
		return "05Environment/main1";
	}
	
	@RequestMapping("/environment/main2.do")
	public String main2(Model model) {
		
		AbstractApplicationContext ctx = 
				new GenericXmlApplicationContext("classpath:AbsAppContext.xml");
		
		ConnectionVO connectionVO = 
				ctx.getBean("connectionVO", ConnectionVO.class);
		
		String userId = connectionVO.getUserId();
		String userPw = connectionVO.getUserPw();
		
		model.addAttribute("userId", userId);
		model.addAttribute("userPw", userPw);
		
		return "05Environment/main2";
	}
	
	@RequestMapping("/environment/main3.do")
	public String main3(Model model) {
		
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(EnvApplicationConfig.class);
		
		BoardVO boardVO = 
				ctx.getBean("boardVOFunc", BoardVO.class);
		
		
		model.addAttribute("pageSize", boardVO.getBlockSize());
		model.addAttribute("blockSize", boardVO.getBlockSize());
		
		return "05Environment/main3";
	}
}
