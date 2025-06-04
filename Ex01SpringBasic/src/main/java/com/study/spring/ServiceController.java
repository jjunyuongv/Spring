package com.study.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.spring.autoscan.MyService;

@Controller
public class ServiceController
{
	MyService myService;
	@Autowired
	public void setMyService(MyService myService) {
		this.myService = myService;
		System.out.println("setMyService()호출-ServiceController");
	}
	
	@RequestMapping("/service/myService.do")
	public String myService() {
		myService.execute();
		return "07Service/myService";
	}
}
