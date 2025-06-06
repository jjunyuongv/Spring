package springboard.service;

import java.util.Map;

import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;
import springboard.model.JDBCTemplateDAO;
import springboard.model.SpringBoardDTO;

public class WriteActionExecute implements IBoardService
{

	@Override
	public void execute(Model model)
	{
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req =
				(HttpServletRequest)paramMap.get("req");
		SpringBoardDTO SpringBoardDTO =
				(SpringBoardDTO)paramMap.get("SpringBoardDTO");
		System.out.println("SpringBoardDTO.title="+
				SpringBoardDTO.getTitle());
		
		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		int affected = dao.write(SpringBoardDTO);
		System.out.println("입력된결과:" + affected);
//		dao.close();
		
	}
	
}
