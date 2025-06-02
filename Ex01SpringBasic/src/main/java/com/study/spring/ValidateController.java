package com.study.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.spring.common.MemberDTO;
import com.study.spring.common.MemberValidator;

@Controller
public class ValidateController
{

    private final RequestMappingController requestMappingController;

    ValidateController(RequestMappingController requestMappingController) {
        this.requestMappingController = requestMappingController;
    }
	@RequestMapping("/validate/memberRegist.do")
	public String memberRegist() {
		return "03Validate/memberRegist";
	}
	
	@RequestMapping("/validate/registProc.do")
	public String registProc(
			@ModelAttribute("mInfo") MemberDTO memberDTO,
			BindingResult result,
			Model model){
		String viewPage = "03Validate/memberDone";
		
		MemberValidator validator = new MemberValidator();
		validator.validate(memberDTO, result);
		
		if(result.hasErrors()) {
			System.out.println("유호성 체크 실패:"+ result.toString());
			model.addAttribute("formError", "폼값 유호성체크에 실패하였습니다.");
			viewPage = "03Validate/memberRegist";
		}
		return viewPage;
	}
}
