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
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest)paramMap.get("req");
		
		String mode = req.getParameter("mode");
		String idx = req.getParameter("idx");
		String nowPage = req.getParameter("nowPage");
		String pass = req.getParameter("pass");
		
//		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		int existIdx = dao.password(idx, pass);
		
		model.addAttribute("existIdx", existIdx);
	}
	
}
