package springboard.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;
import springboard.model.JDBCTemplateDAO;
import springboard.model.SpringBoardDTO;

@Service
public class EditActionExecute implements IBoardService
{
	@Autowired
	JDBCTemplateDAO dao;

	@Override
	public void execute(Model model)
	{
		Map<String, Object> map = model.asMap();
		
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		SpringBoardDTO springBoardDTO = (SpringBoardDTO)map.get("SpringBoardDTO");
		
//		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		
		dao.edit(springBoardDTO);
	}
	
}
