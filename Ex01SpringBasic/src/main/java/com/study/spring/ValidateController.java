package com.study.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ValidateController
{
	@RequestMapping("/validate/memberRegist.do")
	public String memberRegist() {
		return "03Validate/memberRegist";
	}
}
