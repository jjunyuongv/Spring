package com.study.spring.di;

import java.util.ArrayList;

public class Person
{
	private String name;
	private int age;
	private ArrayList<String> hobbys;
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
	public ArrayList<String> getHobbys()
	{
		return hobbys;
	}
	public void setHobbys(ArrayList<String> hobbys)
	{
		this.hobbys = hobbys;
	}
	
	
	public String getInfo() {
		return String.format("이름:%s<br/>"
				+ "나이:%s<br/>"
				+ "취미:%s<br/>", name, age, hobbys);
	}
}
