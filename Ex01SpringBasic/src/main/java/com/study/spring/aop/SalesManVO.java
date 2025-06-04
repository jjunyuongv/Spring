package com.study.spring.aop;

public class SalesManVO
{
	private String name;
	private int age;
	private String job;
	private int salary;
	
	public void getSalesManView() {
		System.out.println("이름:"+getName());
		System.out.println("나이:"+getAge());
		System.out.println("직업:"+getJob());
		System.out.println("급여:"+getSalary());
	}

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
