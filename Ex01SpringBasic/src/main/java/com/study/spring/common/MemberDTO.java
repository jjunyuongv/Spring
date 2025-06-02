package com.study.spring.common;

public class MemberDTO
{
	//DTO객체 생성시 정보은닉을 위해 private으로 선언한다.
	private String id;
	private String pw;
	private String name;
	private String email;
	
	// 꼭 필요한 경우가 아니라면 생성자는 생략한다. 
	// public으로 선언된 getter/setter를 선언한다.
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getPw()
	{
		return pw;
	}
	public void setPw(String pw)
	{
		this.pw = pw;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
}
