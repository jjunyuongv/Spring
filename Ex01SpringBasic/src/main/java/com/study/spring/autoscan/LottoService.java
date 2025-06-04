package com.study.spring.autoscan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LottoService
{
	public LottoService() {
		System.out.println("LottoService생성자 호출");
	}
	
	@Autowired
	LottoDAO lottoDAO = null;
	
	public LottoVO getLottoProcess(int lottoNum, LottoVO lottoVO) {
		int randomNumber = lottoDAO.getLottoNumber();
		lottoVO.setRandomLottoNum(randomNumber);
		
		System.out.println("--- 난수: " + randomNumber);
		System.out.println("--- 입력한 수: " + lottoNum);
		
		if(randomNumber == lottoNum)
			lottoVO.setResult("추카추카..!!");
		else
			lottoVO.setResult("아쉽네요...다음기회를..!!");
		
		return lottoVO;
	}
	
}
