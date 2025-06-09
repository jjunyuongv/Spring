package springboard.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;
import springboard.model.JDBCTemplateDAO;

@Service
public class PasswordActionExecute implements IBoardService
{
	@Autowired
	JDBCTemplateDAO dao;
	
	@Override
	public void execute(Model model)
	{
		//모델객체를 맵컬렉션으로 변환한다. 
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest)paramMap.get("req");
		
		//전송된 폼값을 request 내장객체로 받아온다. 
		String mode = req.getParameter("mode");
		String idx = req.getParameter("idx");
		String nowPage = req.getParameter("nowPage");
		String pass = req.getParameter("pass");
		
		//DAO에서 일련번호와 패스워드를 통해 검증을 진행한다. 
//		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		int existIdx = dao.password(idx, pass);
		
		model.addAttribute("existIdx", existIdx);
	}
	
}
