package com.study.spring;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.spring.mybatis.MyBoardDTO;
import com.study.spring.mybatis.ServiceMyBoard;
import com.study.spring.util.PagingUtil;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MybatisController
{
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/mybatis/list.do")
	public String list(Model model, HttpServletRequest req) {
		
		int totalRecordCount = 
			sqlSession.getMapper(ServiceMyBoard.class).getTotalCount();
		
		int pageSize = 4;
		int blockPage = 2;
		int totalPage = (int)Math.ceil(
				(double)totalRecordCount/pageSize);
		int nowPage = (req.getParameter("nowPage")==null
			|| req.getParameter("nowPage").equals(""))
				? 1 : Integer.parseInt(req.getParameter("nowPage"));
		
		int start = (nowPage-1) * pageSize + 1;
		int end = nowPage * pageSize;
		
		ArrayList<MyBoardDTO> lists =
				sqlSession.getMapper(ServiceMyBoard.class).listPage(start, end);
		
		String pagingImg =
			PagingUtil.pagingImg(totalRecordCount, pageSize,
				blockPage,nowPage,
				req.getContextPath()+"/mybatis/list.do?");
		model.addAttribute("pagingImg", pagingImg);
		
		for(MyBoardDTO dto : lists) {
			String temp = dto.getContents().replace("\r\n", "<br/>");
			dto.setContents(temp);
		}
		
		model.addAttribute("lists", lists);
		
		return "07Mybatis/list";
	}
	
	
}
