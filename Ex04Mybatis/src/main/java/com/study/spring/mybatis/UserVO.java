package com.study.spring.mybatis;

import java.util.ArrayList;

import lombok.Data;

@Data
public class UserVO
{
	private String name;
	private int age;
	private ArrayList<String> hobbys;
}
