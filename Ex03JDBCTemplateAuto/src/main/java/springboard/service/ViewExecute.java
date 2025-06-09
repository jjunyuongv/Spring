package springboard.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;
import springboard.model.JDBCTemplateDAO;
import springboard.model.SpringBoardDTO;

@Service
public class ViewExecute implements IBoardService
{
	@Autowired
	JDBCTemplateDAO dao;
	
	@Override
	public void execute(Model model)
	{
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest)paramMap.get("req");
		
		String idx = req.getParameter("idx");
		String nowPage = req.getParameter("nowPage");
		
//		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		SpringBoardDTO dto = new SpringBoardDTO();
		//게시물의 일련번호를 인수로 전달하여 하나의 레코드를 인출한다.
		dto = dao.view(idx);
		
		//내용은 줄바꿈처리를 위해 replace()를 호출한다.
		dto.setContents(dto.getContents().replace("\r\n", "<br/>"));
		model.addAttribute("viewRow", dto);
		model.addAttribute("nowPage", nowPage);
	}
	
}
