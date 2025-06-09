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
		//모델객체를 Map컬렉션으로 변환한 후 request내장객체를 가져온다.
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		
		//일련번호를 파라미터로 받은 후
		String idx = req.getParameter("idx");
		
		//DAO의 view()메서드를 통해 게시물을 인출한다.  
//		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		SpringBoardDTO dto = dao.view(idx);
		
		dto.setTitle("[RE]" + dto.getTitle());
		dto.setContents("\n\r\n\r---[원본글]---\n\r" + dto.getContents());
		
		//모델객체에 저장한다. 
		model.addAttribute("replyRow", dto);
//		dao.close();
	}
}
