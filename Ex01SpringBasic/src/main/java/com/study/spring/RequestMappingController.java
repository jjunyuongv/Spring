package com.study.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class RequestMappingController
{
	//get/post방식의 요청을 받기위한 매핑. value와 method는 생략함. 
	@RequestMapping("/requestMapping/index.do")
	public String rmIndex() {
		//뷰의 경로만 반환한다. 슬러쉬 앞 부분은 폴더명이 된다.  
		return "02RequestMapping/index";
	}
	
	/*
	params 속성은 파라미터의 유무 혹은 특정값이 넘어왔을때를 판단하여
	매핑할 수 있다. 
	형식]
		params={"p1"} => p1이라는 파라미터가 있으면 매핑된다. 
						!를 붙이면 없을때 매핑된다. 
		params={"p1=Spring"} => p1이 Spring이면 매핑된다. 
		 				!=이 되면 아닐때 매핑된다. 
	 */
	//매핑1 : 전송방식이 GET이고 파라미터에 category가 없을때 매핑됨
	@RequestMapping(value="/requestMapping/getSearch.do", 
			params={"!category"}, method=RequestMethod.GET)
	public String getSearch1(HttpServletRequest req, 
			Model model) {
		System.out.println("GET 방식으로 폼값전송");
		
		//request 내장객체를 통해 폼값을 받는다. 
		String sColumn = req.getParameter("searchColumn");
		String sWord = req.getParameter("searchWord");
						
		//모델객체에 데이터를 저장한다 .
		model.addAttribute("sColumn", sColumn);
		model.addAttribute("sWord", sWord);
		
		//View경로 반환하기
		return "02RequestMapping/getSearch";		
	}
	//매핑2 : 전송방식이 POST이고 파라미터에 category가 없을때 매핑됨.
	@RequestMapping(value="/requestMapping/getSearch.do", 
			params={"!category"}, method=RequestMethod.POST)
	public ModelAndView getSearch2(
			@RequestParam("searchColumn") String sColumn,
			@RequestParam("searchWord") String sWord) {		
		//@RequestParam 어노테이션으로 폼값을 받아 변수에 저장한다. 
		
		System.out.println("POST 방식으로 폼값전송");
		 
		//모델객체에 데이터 저장과 뷰를 호출하는 2가지 기능을 가진 클래스
		ModelAndView mv = new ModelAndView();
		//모델객체에 데이터 저장
		mv.addObject("sColumn", sColumn);
		mv.addObject("sWord", sWord);
		//뷰의 경로를 설정
		mv.setViewName("02RequestMapping/getSearch");
		
		return mv;	 
	}
	
	//매핑3 : 전송방식은 GET/POST 둘다 허용되고, 파라미터에 category가
	//있는 경우에 매핑된다.
	/*
	@ResponseBody : Spring은 일반적으로 모델객체에 데이터를 저장한 후
		뷰로 전달하지만, 만약 컨트롤러에서 즉시 내용을 출력해야 한다면
		해당 어노테이션을 사용하면 된다. 반환타입인 String은 뷰의 경로가
		아니라 단순한 문자열 출력형식이 된다. 
	produces속성 : 요청명 매핑시 해당 문서의 타입을 지정한다. 컨트롤러에서
		즉시 출력해야 하는 경우에 주로 사용한다. 서블릿에서는
		response내장객체의 setContentType()메서드를 통해 설정한다.
	 */
	@RequestMapping(value="/requestMapping/getSearch.do", 
			params={"category"}, 
			produces="text/html; charset=utf-8")
	@ResponseBody
	public String getSearch3(HttpServletRequest req) {
		//문자열의 연결을 위해 주로 사용한다. 
		StringBuffer sb = new StringBuffer();
		sb.append("<h2>@RequestMapping 어노테이션</h2>");
		sb.append("getSearch3() 호출됨");
		//단일 폼값인 경우 getParameter()를 통해 받는다. 
		sb.append("검색어="+req.getParameter("searchWord"));
		//복수의 폼값인 경우 getParameterValues()를 통해 받는다.
		for(String s : req.getParameterValues("category")) {
			sb.append("<br>체크박스="+s);
		}
		//StringBuffer객체를 String타입으로 변환 후 반환한다. 
		return sb.toString();
	}
}
