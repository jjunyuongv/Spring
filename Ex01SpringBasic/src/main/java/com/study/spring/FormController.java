package com.study.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.spring.common.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;

/*
사용자의 요청을 제일 먼저 받는 DispatcherServlet은 기본패키지를 스캔하여
컨트롤러 클래스를 찾아낸다. 그리고 해당 요청명에 매핑된 메서드를 찾아 요청을
전달하고 비즈니스 로직을 수행한다. 

@Controller : 해당 클래스를 컨트롤러로 사용하고 싶을때 추가한다.
패키지를 선언할때 해당 어노테이션이 있는 클래스를 찾아서 요청을 전달한다. 

@RequestMapping : 요청명을 매핑한다. 요청명은 서블릿과 마찬가지로 
컨택스트루트를 제외한 나머지 경로명으로 정하게된다. 매핑정보를 통해 
메서드를 호출하게되므로 메서드명은 중복되지 않는 정도의 이름으로 설정하면된다.
*/
@Controller
public class FormController
{
	/*
	폼값받기1] 파라미터로 전달된 값을 HttpServletRequest 내장객체를
	통해 받는다. JSP나 Servlet에서 주로 사용하는 방식이다. 
	 */
	@RequestMapping("/form/servletRequest")
	public String loginMember(HttpServletRequest req, Model model)
	{
		//request 내장객체를 통해 폼값을 받아온다. 
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		//View로 전달할 데이터를 Model객체에 저장한다.
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		model.addAttribute("message", "로그인 정보가 전달되엇습니다.");
		
		/*View의 페이지 경로를 반환한다. 아래처럼 String으로 반환하면
		ViewResolver가 전체경로를 조립하여 웹브라우저에 출력한다. */
		return "01Form/servletRequest";
	}
	
	/*
	폼값받기2] @RequestParam 어노테이션으로 폼값받기
		파라미터 형식으로 아래와 같이 사용한다. 어노테이션으로 폼값을
		받은 후 우측의 변수에 즉시 대입한다. 매개변수이므로 메서드 내에서
		즉시 사용할 수 있다. 
	 */
	@RequestMapping("/form/requestParam.do")
	public String joinMember(Model model,
			@RequestParam("name") String name,
			@RequestParam("id") String id,
			@RequestParam("pw") String pw,
			@RequestParam("email") String email)
	{//getParameter()로 받는 대신 어노테이션으로 받은 후 변수에 저장한다.
		
		//저장된 폼값은 DTO객체에 저장한다. 
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(id);
		memberDTO.setPw(pw);
		memberDTO.setEmail(email);
		memberDTO.setName(name);
		
		//DTO객체를 Model객체에 저장한 후 View를 호출한다.
		model.addAttribute("memberDTO", memberDTO);
		return "01Form/requestParam";
	}
	
	/*
	폼값받기3] 커맨드 객체를 이용해서 폼값 한번에 받기
	조건1 : 쿼리스트링으로 전달되는 파라미터의 갯수와 폼값을 저장할
		객체(DTO or VO)의 멤버변수의 갯수가 동일한 것을 권장한다. 
	조건2 : 커맨드객체를 사용할때는 클래스명 앞글자를 소문자로 변경한
		형태의 매개변수를 사용해야한다. 
	조건3 : 커맨드객체를 만들때 파라미터의 갯수는 달라도 상관없으나
		파라미터명과 멤버변수명은 동일해야 되고 이에 해당하는 
		getter/setter가 반드시 생성되어야 한다. 
	 */
	@RequestMapping("/form/commandObject.do")
	public String commandObjGet(MemberDTO memberDTO)
	{
		/* 커맨드객체를 사용하면 폼값을 받는 즉시 Model객체에 저장까지
		 완료되어 저장하기 위한 별도의 코드가 필요하지않다. */
		return "01Form/commandObject";
	}
	
	/*
	@ModelAttribute : 뷰로 전달되는 커맨드객체의 이름을 변경하고 
		싶을때 사용한다. memberDTO를 dto로 변경하여 Model객체에
		저장한 후 뷰로 전달한다. 		
	 */
	@RequestMapping("/form/modelAttribute.do")
	public String studentInfo(@ModelAttribute("dto") MemberDTO memberDTO)
	{
		return "01Form/modelAttribute";
	}
	
	/*
	폼값받기4] @PathVariable 어노테이션 사용하기
	요청명뒤에 경로처럼 붙은값이 실제 사용가능한 파라미터가 된다. 
	파라미터를 마치 경로명처럼 사용하게되고, 이때 주의해야 할점은
	웹브라우저는 요청명을 경로로 인식하므로 정적리소스를 사용할때 
	경로에 주의해야한다. 또한 파라미터의 갯수가 틀려지면 404에러가
	발생하게된다. Django(장고)에서는 이런형태의 요청명을 엘레강스URL 
	이라고 한다. 
	 */
	@RequestMapping("/form/{memberId}/{memberName}")
	public String pathVariable(Model model,
			@PathVariable("memberId") String memberId,
			@PathVariable("memberName") String memberName)
	{
		model.addAttribute("memberId", memberId);
		model.addAttribute("memberName", memberName);
		
		return "01Form/pathVariable";
	}
}
