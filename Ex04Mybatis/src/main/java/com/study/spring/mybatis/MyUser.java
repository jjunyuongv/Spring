package com.study.spring.mybatis;

public class MyUser
{
	public void myMethod1()
	{
		UserVO userVO = new UserVO();
		userVO.setAge(10);
		userVO.setName("길동");
		userVO.setHobbys(null);
		System.out.println(userVO);
	}
}
