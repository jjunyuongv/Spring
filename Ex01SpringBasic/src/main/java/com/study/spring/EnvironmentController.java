package com.study.spring;

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

import com.study.spring.env.BoardVO;
import com.study.spring.env.ConnectionVO;
import com.study.spring.env.EnvApplicationConfig;

/*
외부파일을 이용한 설정방법
: 프로젝트를 진행하면서 필요한 설정값이 있는 경우에 외부파일을 입력후
불러와서 사용할 수 있도록 해주는 방법
예) DB접속정보, 관리자정보, 페이지설정값 등
*/
@Controller
public class EnvironmentController
{
	/*
	외부파일참조1
	: Environment 객체를 통해 properties파일의 내용을 
	읽어서 프로그램내에서 사용한다. 
	 */
	@RequestMapping("/environment/main1.do")
	public String main1(Model model)
	{
		//1.스프링 컨테이너 생성한다.
		ConfigurableApplicationContext ctx = new GenericXmlApplicationContext();
		
		//2.Environment 객체를 생성한다.
		ConfigurableEnvironment env = ctx.getEnvironment();
		
		//3.PropertySources를 가져와서 외부파일을 읽을 준비를 한다.
		MutablePropertySources propertySources = env.getPropertySources();
		
		String adminIdStr = "";
		String adminPwStr = "";
		try
		{
			//4.프로퍼티 파일의 경로를 설정한다.
			String envPath = "classpath:EnvAdmin.properties";
			
			//5.addLast() 메서드를 통해 프로퍼티 소스를 추가한다. 
			propertySources.addLast(new ResourcePropertySource(envPath));
			
			//6.getProperty() 로 해당 데이터를 읽어서 변수에 저장한다.
			adminIdStr = env.getProperty("admin.id");
			adminPwStr = env.getProperty("admin.pw");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//모델 객체에 저장후 뷰로 반환한다.
		model.addAttribute("adminID", adminIdStr);
		model.addAttribute("adminPW", adminPwStr);
		
		return "05Environment/main1";
	}
	
	/*
	외부파일참조 2
	: Environment 객체를 사용하지 않고 XML설정파일에 프로퍼티
	파일을 명시한 후 자바빈을 생성하고 주입받아 사용하는 방식이다. 
	 */
	@RequestMapping("/environment/main2.do")
	public String main2(Model model)
	{
		//XML설정파일을 통해 스프링 컨테이너를 생성한다. 
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:AbsAppContext.xml");
		
		//컨테이너에 생성된 빈을 주입받아 사용한다.
		ConnectionVO  connectionVO =  ctx.getBean("connectionVO", ConnectionVO.class);
		
		//빈에 저장된 값을 getter를 통해 읽어온다.
		String userId = connectionVO.getUserId();
		String userPw = connectionVO.getUserPw();
				
		//모델 객체에 저장후 뷰로 반환한다.
		model.addAttribute("userId", userId);
		model.addAttribute("userPw", userPw);
		
		return "05Environment/main2";
	}
	
	/*
	외부파일참조3
	: 어노테이션을 이용한 외부파일 참조. XML설정파일 대신 Java클래스
	파일을 이용하여 외부파일을 참조하고 빈을 생성한다. 
	 */
	@RequestMapping("/environment/main3.do")
	public String main3(Model model)
	{
		//어노테이션 기반 스프링 컨테이너 생성
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(EnvApplicationConfig.class);
		
		//어노테이션이 적용된 Java파일에서 생성된 빈을 주입받는다.
		BoardVO  boardVO =  ctx.getBean("boardVOFunc", BoardVO.class);
		
		//모델 객체에 저장후 뷰로 반환한다.
		model.addAttribute("pageSize", boardVO.getPageSize());
		model.addAttribute("blockSize", boardVO.getBlockSize());
		
		return "05Environment/main3";
	}
}
