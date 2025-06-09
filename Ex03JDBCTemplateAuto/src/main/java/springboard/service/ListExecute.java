package springboard.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;
import springboard.model.JDBCTemplateDAO;
import springboard.model.SpringBoardDTO;
import springboard.util.EnvFileReader;
import springboard.util.PagingUtil;

@Service
public class ListExecute implements IBoardService
{
	
	@Autowired
	JDBCTemplateDAO dao;

	@Override
	public void execute(Model model)
	{
		System.out.println("ListCommand > execute() 호출");
		
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest)paramMap.get("req");
//		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		
		String addQueryString = "";
		String searchColumn = req.getParameter("searchColumn");
		String searchWord = req.getParameter("searchWord");
		if(searchWord!=null && searchWord!="") {
			addQueryString = String.format("searchColumn=%s"+"&searchWord=%s&", searchColumn, searchWord);
			paramMap.put("Column", searchColumn);
			paramMap.put("Word", searchWord);
		}
		
		int totalRecordCount = dao.getTotalCount(paramMap);
		
		int pageSize = Integer.parseInt(
				EnvFileReader.getValue("SpringBbsInit.properties",
						"springBoard.blockPage"));
		int blockPage = Integer.parseInt(
				EnvFileReader.getValue("SpringBbsInit.properties",
						"springBoard.blockPage"));
		
		int totalPage = (int)Math.ceil((double)totalRecordCount/pageSize);
		
		int nowPage = req.getParameter("nowPage")==null ? 1 :
			Integer.parseInt(req.getParameter("nowPage"));
		
		int start = (nowPage-1) * pageSize + 1;
		int end = nowPage * pageSize;
		
		paramMap.put("start", start);
		paramMap.put("end", end);
		
//		ArrayList<SpringBoardDTO> listRows = dao.list(paramMap);
		ArrayList<SpringBoardDTO> listRows = dao.listPage(paramMap);
		
		int virtualNum = 0;
		int countNum = 0;
		for(SpringBoardDTO row : listRows) {
//			virtualNum = totalRecordCount --;
//			row.setVirtualNum(virtualNum);
			
			virtualNum = totalRecordCount
					- (((nowPage-1)*pageSize) + countNum++);
			row.setVirtualNum(virtualNum);
			
			String reSpace = "";
			if(row.getBindent() > 0) {
				for(int i = 0 ; i < row.getBindent() ; i++) {
					reSpace += "&nbsp;&nbsp;";
				}
				row.setTitle(reSpace
						+ "<img src='../images/re3.gif'>"
						+ row.getTitle());
			}
		}
		
		String pagingImg = PagingUtil.pagingImg(totalRecordCount,
				pageSize, blockPage, nowPage,
				req.getContextPath()+"/board/list.do?"+addQueryString);
		
		model.addAttribute("pagingImg", pagingImg);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("listRows", listRows);
		
//		dao.close();
		
		
	}
	
}
