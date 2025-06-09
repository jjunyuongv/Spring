package springboard.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;
import springboard.model.JDBCTemplateDAO;

@Service
public class DeleteActionExecute implements IBoardService
{
	@Autowired
	JDBCTemplateDAO dao;
	

	@Override
	public void execute(Model model)
	{
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("req");
		
		String idx = req.getParameter("idx");
		String pass = req.getParameter("pass");
		
//		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		boolean isDelet = dao.deleteConfirm(idx);
		if (isDelet==true)
		{
			dao.delete(idx, pass);
		} else
		{
			System.out.println("삭제 할수 없습니다.");
			model.addAttribute("isDelete", "X");
		}
		
	}
	
}
