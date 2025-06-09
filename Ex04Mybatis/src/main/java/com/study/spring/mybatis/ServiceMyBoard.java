package com.study.spring.mybatis;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public interface ServiceMyBoard
{
	public int getTotalCount();
	public ArrayList<MyBoardDTO> listPage(int s, int e);
}
