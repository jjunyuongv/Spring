package com.study.spring.mybatis;

import org.springframework.stereotype.Service;

@Service
public interface ServiceMyMember
{
	public MyMemberDTO login(String id, String pass);
}
