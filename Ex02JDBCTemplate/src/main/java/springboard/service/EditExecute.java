package springboard.service;

import java.util.Map;

import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;
import springboard.model.JDBCTemplateDAO;
import springboard.model.SpringBoardDTO;

public class EditExecute implements IBoardService
{

	@Override
	public void execute(Model model)
	{
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest)paramMap.get("req");
		
		String idx = req.getParameter("idx");
		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		
		SpringBoardDTO dto = dao.view(idx);
		model.addAttribute("viewRow", dto);
//		dao.close();
	}
	
}
