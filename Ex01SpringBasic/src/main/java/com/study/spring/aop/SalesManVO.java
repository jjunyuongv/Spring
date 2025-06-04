package com.study.spring.aop;

public class SalesManVO
{
	//직장인을 표현하는 클래스
	private String name;	//이름
	private int age;		//나이
	private String job;		//직업
	private int salary;		//급여
	
	public void getSalesManView()
	{
		System.out.println("이름 : " + getName());
		System.out.println("나이 : " + getAge());
		System.out.println("직업 : " + getJob());
		System.out.println("급여 : " + getSalary());
	}
	
	//getter/setter
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public String getJob()
	{
		return job;
	}

	public void setJob(String job)
	{
		this.job = job;
	}

	public int getSalary()
	{
		return salary;
	}

	public void setSalary(int salary)
	{
		this.salary = salary;
	}
}
