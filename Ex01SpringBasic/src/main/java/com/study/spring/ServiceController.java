package com.study.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.study.spring.autoscan.LottoService;
import com.study.spring.autoscan.LottoVO;
import com.study.spring.autoscan.MyService;

@Controller
public class ServiceController
{

    private final LottoService lottoService;
	MyService myService;

    ServiceController(LottoService lottoService) {
        this.lottoService = lottoService;
    }
	@Autowired
	public void setMyService(MyService myService) {
		this.myService = myService;
		System.out.println("setMyService()호출-ServiceController");
	}
	
	@RequestMapping("/service/myService.do")
	public String myService() {
		myService.execute();
		return "07Service/myService";
	}
	
	@RequestMapping("/service/myLotto.do")
	public String myLotto(LottoVO lottoVO, Model model) {
		lottoVO = lottoService.getLottoProcess(lottoVO.getUserLottoNum(), lottoVO);
		
		return "07Service/myLotto";
	}
}
