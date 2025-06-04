package com.study.spring.autoscan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService
{
	//생성자
	public MyService() 
	{
		System.out.println("MyService 생성자 호출");
	}
	
	/*
	서비스 객체는 클라이언트의 요청을 처리하기 위해 주로 Model(DAO)를
	호출하여 비즈니스 로직을 처리한다. 작업을 위해 DAO객체를 자동주입
	받는다. 
	 */
	//멤버변수
	MyDAO myDAO;
	//setter() 메서드
	@Autowired
	public void setMyDAO(MyDAO myDAO)
	{
		this.myDAO = myDAO;
		System.out.println("setMyDAO()호출 - MyService");
	}
	
	//요청처리를 위한 메서드라 가정한다.
	public void execute()
	{
		//DB처리를 위해 DAO객체의 메서드를 호출한다. 
		myDAO.selectList();
	}
}
