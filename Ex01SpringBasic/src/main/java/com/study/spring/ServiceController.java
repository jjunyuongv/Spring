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
	/*
	서비스객체를 자동으로 주입받는다. @Autowired는 멤버변수, 생성자,
	setter에서 사용할 수 있다. 또한 스프링 컨테이너에 미리 생성되어 
	있어야하고 그렇지 않으면 예외가 발생하여 서버가 실행되지 않는다.
	 */
	//멤버변수
	MyService myService;
	//setter() 메서드
	@Autowired
	public void setMyService(MyService myService)
	{
		this.myService = myService;
		//빈이 정상적으로 주입되었는지 콘솔에서 확인
		System.out.println("setMyService()호출-ServiceController");
	}
	
	/*
	요청명에 대한 매핑처리. 컨트롤러는 요청을 분석한 후 적절한 서비스 객체를
	호출한다. 이때 컨트롤러가 받은 모든 요청을 전달한다. 
	 */
	@RequestMapping("/service/myService.do")
	public String myService()
	{
		myService.execute();
		return "07Service/myService";
	}
	
	//////////////////////////////////////////////////////
		
	/*
	서비스 객체의 호출을 위해 빈을 자동주입받는다. 
	일반적으로 setter메서드 없이 멤버변수에 직접 어노테이션을 부착한다. 
	*/
	@Autowired
	private LottoService lottoService;
	
	/*
	사용자가 HTML페이지에서 각 링크를 클릭할때 파라미터로 전달하는
	userLottoNum을 커맨드객체인 LottoVO객체를 통해 받는다. 
	*/
	@RequestMapping("/service/myLotto.do")
	public String myLotto(LottoVO lottoVO, Model model)
	{
		/*
		커맨드객체를 통해 폼값을 받으므로 getParameter()가 필요없다. 
		또한 객체의 사용을 위해 new를 사용하지 않아도된다. 
		첫번째 인수는 사용자가 선택한 숫자이고
		두번째 인수는 VO객체 자체를 전달한다. 
		*/
		lottoVO = lottoService.getLottoProcess(lottoVO.getUserLottoNum(), lottoVO);
		/*
		커맨드객체를 통해 폼값을 받으면 파라미터를 한꺼번에 받는 동시에
		모델객체에 저장까지 하게된다. 따라서 View로 전달하기 위해 
		모델객체에 별도로 저장하지 않아도된다. 
		*/
		return "07Service/myLotto";
	}
}
