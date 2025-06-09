package springboard.service;

import java.util.Map;

import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;
import springboard.model.JDBCTemplateDAO;
import springboard.model.SpringBoardDTO;

public class EditActionExecute implements IBoardService
{
	@Override
	public void execute(Model model)
	{
		//모델객체에 저장된 객체를 변환 후 추출한다. 
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		SpringBoardDTO SpringBoardDTO = (SpringBoardDTO)map.get("SpringBoardDTO");
		
		//DAO 객체를 생성한다. 
		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		
		//폼값을 개별적으로 받음
//		String idx = req.getParameter("idx");				
//		String name = req.getParameter("name");
//		String title = req.getParameter("title");
//		String contents = req.getParameter("contents");
//		String pass = req.getParameter("pass");
//		dao.edit(idx, name, title, contents, pass);
		
		//커맨트객체인 DTO를 DAO로 전달한다. 이경우 개별적으로 받는
		//코드를 작성하지 않아도 되므로 훨씬 편리하게 개발할 수 있다.
		dao.edit(SpringBoardDTO);
	}
	
}
