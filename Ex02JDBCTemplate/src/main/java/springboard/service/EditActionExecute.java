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
		Map<String, Object> map = model.asMap();
		
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		SpringBoardDTO springBoardDTO = (SpringBoardDTO)map.get("SpringBoardDTO");
		
		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		
		dao.edit(springBoardDTO);
	}
	
}
