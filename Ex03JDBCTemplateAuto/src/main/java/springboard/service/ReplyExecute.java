package springboard.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;
import springboard.model.JDBCTemplateDAO;
import springboard.model.SpringBoardDTO;

@Service
public class ReplyExecute implements IBoardService
{
	
	@Autowired
	JDBCTemplateDAO dao;

	@Override
	public void execute(Model model)
	{
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		
		String idx = req.getParameter("idx");
		
//		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		SpringBoardDTO dto = dao.view(idx);
		
		dto.setTitle("[RE]"+ dto.getTitle());
		dto.setContents("\n\r\n\r---[원본글]---\n\r"+dto.getContents());
		
		model.addAttribute("replyRow", dto);
//		dao.close();
		
	}
	
}
