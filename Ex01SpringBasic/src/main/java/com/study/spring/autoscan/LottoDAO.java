package com.study.spring.autoscan;

import java.util.Random;

import org.springframework.stereotype.Repository;

//저장소(DAO)역할의 클래스 선언 
@Repository
public class LottoDAO
{
	//생성자
	public LottoDAO()
	{
		System.out.println("LottoDAO생상자 호출");
	}
	
	//멤버메서드
	public int getLottoNumber() {
		//1~6사이의 난수를 생성하여 서비스객체로 반환한다.
		Random rand = new Random();
		//난수생성을 DB에서의 CRUD 작업이라 가정한다. 
		return rand.nextInt(6) + 1;
	}
}
