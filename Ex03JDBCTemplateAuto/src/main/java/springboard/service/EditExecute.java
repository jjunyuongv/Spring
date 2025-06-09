package springboard.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;
import springboard.model.JDBCTemplateDAO;
import springboard.model.SpringBoardDTO;

@Service
public class EditExecute implements IBoardService
{

	@Autowired
	JDBCTemplateDAO dao;
	
	@Override
	public void execute(Model model)
	{
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest)paramMap.get("req");
		
		String idx = req.getParameter("idx");
//		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		
		SpringBoardDTO dto = dao.view(idx);
		model.addAttribute("viewRow", dto);
//		dao.close();
	}
	
}
