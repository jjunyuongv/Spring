package com.study.spring.autoscan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService
{
	public MyService() {
		System.out.println("MyService 생성자 호출");
	}
	MyDAO myDAO;
	@Autowired
	public void setMyDAO(MyDAO myDAO) {
		this.myDAO = myDAO;
		System.out.println("setMyDAO()호출-MyService");
	}
	
	public void execute() {
		myDAO.selectList();
	}
	
}
