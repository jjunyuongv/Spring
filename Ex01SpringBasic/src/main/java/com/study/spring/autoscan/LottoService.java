package com.study.spring.autoscan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//서비스객체 역할의 클래스로 정의한다. 
@Service
public class LottoService
{
	//생성자 메서드
	public LottoService()
	{
		System.out.println("LottoService 생성자 호출");
	}
	
	/*
	서비스 객체는 비즈니스로직을 수행하는 중 DB작업이 필요한 경우
	Model을 호출하므로 DAO 빈을 자동주입 받는다. 
	 */
	@Autowired
	LottoDAO lottoDAO = null;
	
	//첫번째 인수는 사용자가 선택한 숫자, 두번째 인수는 VO객체
	public LottoVO getLottoProcess(int lottoNum, LottoVO lottoVO)
	{
		//DAO를 호출하여 난수를 생성한다. 
		int randomNumber = lottoDAO.getLottoNumber();
		//생성된 난수를 VO객체에 저장한다.
		lottoVO.setRandomLottoNum(randomNumber);
		
		//DAO에서 생성한 난수와 사용자가 선택한 숫자를 콘솔에 출력한다.
		System.out.println("---난수 : " + randomNumber);
		System.out.println("---입력한 수 : " + lottoNum);
		
		//두 숫자를 비교한 결과를 VO객체에 저장한다.
		if (randomNumber == lottoNum)
		{
			lottoVO.setResult("추카추가...!!");
		} else
		{
			lottoVO.setResult("아쉽네요...다음기회를...!!");
		}
		return lottoVO;
	}
}
