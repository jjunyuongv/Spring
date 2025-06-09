package com.study.spring;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.study.spring.mybatis.MyBoardDTO;
import com.study.spring.mybatis.MyMemberDTO;
import com.study.spring.mybatis.ServiceMyBoard;
import com.study.spring.mybatis.ServiceMyMember;
import com.study.spring.util.PagingUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
	
	@RequestMapping("/mybatis/write.do")
	public String write(Model model, HttpSession session,
			HttpServletRequest req) {
		if(session.getAttribute("siteUserInfo")==null)
		{
			model.addAttribute("backUrl", "07Mybatis/write");
			return "redirect:login.do";
		}
		
		return "07Mybatis/write";
	}
	
	@RequestMapping("/mybatis/login.do")
	public String login(Model model) {
		return "07Mybatis/login";
	}
	
	@RequestMapping("/mybatis/loginAction.do")
	public ModelAndView loginAction(HttpServletRequest req,
		HttpSession session) {
		
		MyMemberDTO dto = sqlSession
				.getMapper(ServiceMyMember.class)
				.login(req.getParameter("id"),
						req.getParameter("pass"));
		
		ModelAndView mv = new ModelAndView();
		if(dto==null) {
			mv.addObject("LoginNG", "아이디/패스워드가 틀렸습니다.");
			mv.setViewName("07Mybatis/login");
			return mv;
		}
		else {
			session.setAttribute("siteUserInfo", dto);
		}
		
		String backUrl = req.getParameter("backUrl");
		if (backUrl==null || backUrl.equals(""))
		{
			mv.setViewName("07Mybatis/login");
		} else
		{
			mv.setViewName(backUrl);
		}
		return mv;
	}
	
	
}
