package com.study.spring.autoscan;

import org.springframework.stereotype.Repository;

@Repository
public class MyDAO
{
	public MyDAO() {
		System.out.println("MyDAO 생성자 호출");
	}
	public void selectList() {
		System.out.println("계시판의 리스트를 출력합니다.");
	}
}
