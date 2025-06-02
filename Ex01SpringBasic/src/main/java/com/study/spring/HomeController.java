package com.study.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		
		System.out.println("Spring MVC Project 완성");
		
		return "home";
	}
	
	/*
	컨트롤러 만드는 순서
	1.요청명을 먼저 결정한다. 
	2.요청명을 통해 컨트롤러를 찾아 매핑된 메서드를 호출한다. 컨트롤러에서
	요청을 처리할 메서드의 이름은 해당 클래스내부에서 중복되지 않는 정도의
	이름을 사용하면 된다. 
	3.매핑시에는 @RequestMapping 어노테이션을 사용한다. 
	 */
	@RequestMapping("/home/helloSpring")
	public String helloSpring(Model model) {
		/*
		4.요청을 처리한다. Spring에서는 4가지 영역과 비슷한 Model
		객체를 사용해서 속성을 저장하고 View로 전달한다. 사용법은 거의
		동일하다. 
		 */
		String firstMessage = "My First Spring MVC 컨트롤러";
		model.addAttribute("firstMessage", firstMessage);
		
		/*
		5.뷰의 이름을 반환한다. 서브릿에서의 포워드와 비슷한 개념이다. 
		해당뷰의 이름을 ViewResolver가 조립하여 최종적으로 뷰를 
		웹브라우저에 출력한다. 설정은 servlet-context.xml에서 
		할 수 있다. 
		 */
		return "HelloSpring";
	}
}

