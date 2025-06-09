package springboard.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;
import springboard.model.JDBCTemplateDAO;
import springboard.model.SpringBoardDTO;

@Service
public class WriteActionExecute implements IBoardService
{
	//폼값을 테이블에 입력하기 위해 DAO객체를 생성한다.
	@Autowired
	JDBCTemplateDAO dao;
	
	//추상메서드를 필수 오버라이딩한다. 
	@Override
	public void execute(Model model)
	{
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest)paramMap.get("req");
		SpringBoardDTO SpringBoardDTO = (SpringBoardDTO)paramMap.get("SpringBoardDTO");
		//제목 정도만 콘솔에서 확인해본다.(디버깅용)
//		System.out.println("SpringBoardDTO.title=" +SpringBoardDTO.getTitle());
		
		//폼값을 테이블에 입력하기 위해 DAO객체를 생성한다.
//		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		//insert쿼리 실행을 위해 write()메서드를 호출한다. 
		int affected = dao.write(SpringBoardDTO); 
		System.out.println("입력된 결과:" + affected);
		//Spring-JDBC에서는 자원해제를 하지 않는다.
//		dao.close();
	}
	
}
