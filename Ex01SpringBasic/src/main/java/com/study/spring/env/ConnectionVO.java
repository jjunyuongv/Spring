package com.study.spring.env;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

public class ConnectionVO implements EnvironmentAware, InitializingBean
{

	@Override
	public void afterPropertiesSet() throws Exception
	{
		System.out.println("afterPropertiesSet() 호출");
	}

	@Override
	public void setEnvironment(Environment environment)
	{
		System.out.println("setEnvironment() 호출");
	}
	
	private String userId;
	private String userPw;
	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getUserPw()
	{
		return userPw;
	}

	public void setUserPw(String userPw)
	{
		this.userPw = userPw;
	}
	
	
	
}
