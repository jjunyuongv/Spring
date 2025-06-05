package springboard.service;

import java.util.Map;

import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;
import springboard.model.JDBCTemplateDAO;
import springboard.model.SpringBoardDTO;

public class ViewExecute implements IBoardService
{

	@Override
	public void execute(Model model)
	{
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req =
				(HttpServletRequest)paramMap.get("req");
		
		String idx = req.getParameter("idx");
		String nowPage = req.getParameter("nowPage");
		
		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		SpringBoardDTO dto = new SpringBoardDTO();
		dto = dao.view(idx);
		
		dto.setContents(dto.getContents()
				.replace("\r\n", "<br/>"));
		model.addAttribute("viewRow", dto);
		model.addAttribute("nowPage", nowPage);
	}
	
}
