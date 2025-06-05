package springboard.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;
import springboard.model.JDBCTemplateDAO;
import springboard.model.SpringBoardDTO;

public class ListExecute implements IBoardService
{

	@Override
	public void execute(Model model)
	{
		System.out.println("ListCommand > execute() 호출");
		
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest)paramMap.get("req");
		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		
		String addQueryString = "";
		String searchColumn = req.getParameter("searchColumn");
		String searchWord = req.getParameter("searchWord");
		if(searchWord!=null && searchWord!="") {
			addQueryString = String.format("searchColumn=%s"+"&searchWord=%s&", searchColumn, searchWord);
			paramMap.put("Column", searchColumn);
			paramMap.put("Word", searchWord);
		}
		
		int totalRecordCount = dao.getTotalCount(paramMap);
		ArrayList<SpringBoardDTO> listRows = dao.list(paramMap);
		
		int virtualNum = 0;
		int countNum = 0;
		for(SpringBoardDTO row : listRows) {
			virtualNum = totalRecordCount --;
			row.setVirtualNum(virtualNum);
		}
		
		model.addAttribute("listRows", listRows);
//		dao.close();
		
		
	}
	
}
