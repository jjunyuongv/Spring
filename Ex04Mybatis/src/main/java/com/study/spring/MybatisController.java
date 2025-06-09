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
	/*
	Mybatis를 사용하기 위해 자바빈을 자동으로 주입받는다. 해당 자바빈은
	servlet-context.xml에서 생성하고 있다.
	 */
	@Autowired
	private SqlSession sqlSession;
	
	//방명록 리스트 매핑
	@RequestMapping("/mybatis/list.do")
	public String list(Model model, HttpServletRequest req)
	{
		/*
		방명록 테이블의 게시물의 갯수를 카운트한다. 
		호출방식은 다음과 같다. 
		컨트롤러에서 서비스객체인 interface에 정의된 추상메서드를 호출한다.
		그러면 Mapper에 정의된 쿼리문이 실행되는 방식으로 동작한다. 
		순서] Controller에서 메서드 호출 => interface의 추상메서드
			호출 => 추상메서드명과 동일한 Mapper의 id속성을 찾음
			=> 해당 엘리먼트에 정의된 쿼리문 실행 => 결과반환
		 */
		int totalRecordCount = sqlSession.getMapper(ServiceMyBoard.class).getTotalCount();
		
		//게시판 페이징 처리를 위한 설정값(하드코딩으로 간단히 설정)
		int pageSize = 4;	//한 페이지당 게시물 수
		int blockPage = 2;	//한 블럭당 페이지번호 수
		
		//전체 페이지수 계산
		int totalPage = (int)Math.ceil((double)totalRecordCount/pageSize);
		
		/*현재 페이지 번호 설정. 목록에 처음으로 접속한 경우 파라미터가
		없으므로 1로 설정하고 그 외는 파라미터를 사용하여 설정한다.*/
		int nowPage = (req.getParameter("nowPage")==null || req.getParameter("nowPage").equals(""))
				? 1 : Integer.parseInt(req.getParameter("nowPage"));
		
		//해당 페이지에 출력할 게시물의 구간을 계산한다. 
		int start = (nowPage-1)* pageSize + 1;
		int end = nowPage * pageSize;
		
		/*
		현제 페이지에 출력할 게시물을 인출한다. 이때 앞에서 계산한 
		start, end를 인수로 전달한다. 해당 인수는 Mapper에서
		#{param1} 과 같이 사용된다. 
		 */
		ArrayList<MyBoardDTO> lists = sqlSession.getMapper(ServiceMyBoard.class).listPage(start, end);
		
		//페이지번호 처리(기존의 클래스를 그대로 사용한다.)
		String pagingImg = PagingUtil.pagingImg(totalRecordCount, pageSize, blockPage, nowPage, 
				req.getContextPath()+"/mybatis/list.do?");
		model.addAttribute("pagingImg", pagingImg);
		
		//내용에 대한 줄바꿈 처리(방명록은 내용보기가 별도로 없고, 목록에
		//내용이 바로 출력된다.)
		for (MyBoardDTO dto : lists)
		{
			String temp = dto.getContents().replace("\r\n",	"<br/>");
			dto.setContents(temp);
		}
		//출력할 내용을 모델객체에 저장한 후 뷰를 호출한다.
		model.addAttribute("lists", lists);
		return "07Mybatis/list";
	}
	
	//글쓰기 페이지 매핑
	@RequestMapping("/mybatis/write.do")
	public String write(Model model, HttpSession session,
			HttpServletRequest req)
	{
		/* 컨트롤러에서 session 내장객체를 사용하고 싶다면 위와같이
		매개변수로 선언해주기만 하면 메서드에서 즉시 사용할수 있다. */		
		//세션영역에 인증정보가 없다면 로그아웃 상태로 판단한다.
		if (session.getAttribute("siteUserInfo")==null)
		{
			/*
			현재상태는 글쓰기를 위해 버튼을 클릭했으므로, 로그인이
			된 후에는 글쓰기 페이지로 다시 이동하는것이 좋다. 따라서 
			backUrl이라는 속성값에 쓰기페이지에 대한 View경로를 
			저장하여 리다이렉트(이동) 시킨다. 
			 */
			model.addAttribute("backUrl", "07Mybatis/write");
			/*
			모델객체에 특정 속성을 저장한 후 redirect 시키면 URL뒤에
			쿼리스트링 형태로 추가된다. 즉 login.do?backUrl=값
			과 같이 리다이렉트 된다. 
			 */
			return "redirect:login.do";
		}
		
		//로그인이 완료된 상태라면 쓰기페이지로 진입한다.
		return "07Mybatis/write";
	}
	
	//로그인 페이지 매핑
	@RequestMapping("/mybatis/login.do")
	public String login(Model model)
	{
		return "07Mybatis/login";
	}
	
	//로그인 처리 : session 내장객체 사용
	@RequestMapping("/mybatis/loginAction.do")
	public ModelAndView loginAction(HttpServletRequest req, HttpSession session)
	{
		//폼값으로 전송된 id, pass를 login()메서드로 전달하여 Mapper를 호출한다. 
		MyMemberDTO dto = sqlSession.getMapper(ServiceMyMember.class)
				.login(req.getParameter("id"), req.getParameter("pass"));
		
		//Model객체에 속성저장과 View반환을 동시에 처리할 수 있는 객체
		ModelAndView mv = new ModelAndView();
		//Mapper에서 회원인증에 실패한 경우(로그인 실패) 
		if (dto==null)
		{
			//로그인 실패 메세지를 모델객체에 저장한다.
			mv.addObject("LoginNG", "아이디/패스워드가 틀렸습니다.");
			//로그인 페이지를 화면에 출력한다.
			mv.setViewName("07Mybatis/login");
			return mv;
		} else
		{
			//로그인에 성공한 경우에는 세션영역에 DTO객체를 저장한다.
			session.setAttribute("siteUserInfo", dto);
		}
		
		/*
		로그인 처리 후 backUrl이 있는 경우라면 해당 페이지로 이동시킨다.
		만약 값이 없다면 로그인 페이지로 이동시킨다.
		backUrl은 글쓰기 페이지로 진입시 로그인정보가 없는경우 파라미터로
		전달된 쓰기페이지의 View경로이다.  
		 */
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
