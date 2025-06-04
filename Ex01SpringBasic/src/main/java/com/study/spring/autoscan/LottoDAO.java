package com.study.spring.autoscan;

import java.util.Random;

import org.springframework.stereotype.Repository;

@Repository
public class LottoDAO
{

	public LottoDAO()
	{
		System.out.println("LottoDAO생성자호출");
	}
	
	public int getLottoNumber() {
		Random rand = new Random();
		return rand.nextInt(6)+1;
	}
	
	
}
