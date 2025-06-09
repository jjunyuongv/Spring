package springboard.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import springboard.model.SpringBoardDTO;
import springboard.service.DeleteActionExecute;
import springboard.service.EditActionExecute;
import springboard.service.EditExecute;
import springboard.service.IBoardService;
import springboard.service.ListExecute;
import springboard.service.PasswordActionExecute;
import springboard.service.ReplyActionExecute;
import springboard.service.ReplyExecute;
import springboard.service.ViewExecute;
import springboard.service.WriteActionExecute;

@Controller
public class BoardController
{	
	IBoardService service = null;
	
	/*
	기존 버전에서는 서비스객체 생성시 new를 통해 개발자가 직접 생성했지만
	Auto버전에서는 스프링 컨테이너가 시작할때 미리 생성된 빈을 자동주입
	받을수 있도록 @Autowired 어노테이션을 부착한다. 서비스 객체는 
	멤버변수로 선언한 후 각 메서드에서는 this를 통해 접근한다. 
	 */
	@Autowired
	public ListExecute listExecute;
	@Autowired
	public WriteActionExecute writeActionExecute;
	@Autowired
	public ViewExecute viewExecute;
	@Autowired
	public PasswordActionExecute passwordActionExecute;
	@Autowired
	public EditExecute editExecute;
	@Autowired
	public DeleteActionExecute deleteActionExecute;
	@Autowired
	public EditActionExecute editActionExecute;
	@Autowired
	public ReplyExecute replyExecute;
	@Autowired
	public ReplyActionExecute replyActionExecute;
	
	@RequestMapping("/board/list.do")
	public String list(Model model, HttpServletRequest req)
	{
		model.addAttribute("req", req);

		/*
			기존 버전에서는 해당 서비스 객체를 new를 통해 생성했지만, 
			Auto버전에서는 component-scan 되는 패키지를 확장하고 
			서비스클래스에 @Service 어노테이션을 부착하여 자동으로 빈을
			생성할 수 있게 설정하고, 컨트롤러에서는 @Autowired(자동주입)
			받아서 사용한다. 이렇게 new를 제거한다.  
		 */
//		service = new ListExecute();
		service = this.listExecute;
		//Service 객체로 Model을 전달한다. 
		service.execute(model);
		
//		return "07Board/list";
		return "BoardSkin/listT";
	}
	
	//글쓰기 페이지에 대한 매핑 처리
	@RequestMapping("/board/write.do")
	public String write(Model model)
	{
		return "BoardSkin/writeT";
	}
	
	@RequestMapping(value = "/board/writeAction.do", 
			method = RequestMethod.POST)
	public String writeAction(Model model, HttpServletRequest req,
			SpringBoardDTO SpringBoardDTO)
	{
		//request내장객체를 모델객체에 저장
		model.addAttribute("req", req);
		//DTO객체를 모델객체에 저장
		model.addAttribute("SpringBoardDTO", SpringBoardDTO);
		//Service객체를 생성한 후 Model객체를 인수로 전달한다.
		service = this.writeActionExecute;
		service.execute(model);
		
		//뷰를 반환하지 않고, 지정된 URL(경로)로 이동한다. 
		return "redirect:list.do?nowPage=1";
	}
	
	//내용보기
	@RequestMapping("/board/view.do")
	public String view(Model model, HttpServletRequest req)
	{
		model.addAttribute("req", req);
		service = this.viewExecute;
		service.execute(model);
		
		return "BoardSkin/viewT";
	}
	
	//패스워드 검증 페이지 매핑
	@RequestMapping("/board/password.do")
	public String password(Model model, HttpServletRequest req)
	{
		model.addAttribute("idx",req.getParameter("idx"));
		return "07Board/password";
	}
	
	//패스워드 검증을 진행한 후 수정 혹은 삭제처리를 한다.  (Javascript alert() 처리 안됨)
//	@RequestMapping("/board/passwordAction.do")
//	public String passwordAction(Model model, HttpServletRequest req)
//	{
//		//폼값을 저장한 request객체를 모델객체에 저장한 후 서비스
//		//객체를 호출한다.
//		model.addAttribute("req", req);
//		service = new PasswordActionExecute();
//		service.execute(model);
//		
//		//모델객체를 Map컬렉션으로 변환한다. 
//		Map<String, Object> paramMap = model.asMap();
//		//idx는 정수이므로 Integer로 형변환 후 변수에 저장한다. 
//		int existIdx = (Integer)paramMap.get("existIdx");
//		//패스워드 검증이 완료된 게시물의 idx값을 로그에 출력한다.
//		System.out.println("existIdx="+ existIdx);
//		
//		//전송된 파라미터를 받아온다.
//		String mode = req.getParameter("mode");
//		String idx = req.getParameter("idx");
//		//패스워드 검증후 반환할 뷰의 경로를 저장할 변수 선언
//		String modePage = null;
//		if (existIdx<=0)
//		{
//			//패스워드 검증에 실패했다면, 메세지를 모델객체에 저장한다.
//			model.addAttribute("isCorrMsg", "패스워드가 일치하지 않습니다.");
//			model.addAttribute("idx", idx);
//			
//			//패스워드 페이지로 포워드한다.
//			modePage = "07Board/password";
//		} else
//		{
//			/* 패스워드 검증이 완료되었다면 수정의 경우 수정페이지로
//			포워드하고, 삭제의 경우에는 즉시 게시물을 삭제처리한다. 
//			 */
//			System.out.println("검증완료");
//			
//			if (mode.equals("edit"))
//			{
//				//수정인 경우 서비스객체로 모델객체를 전달하고..
//				model.addAttribute("req", req);
//				service = new EditExecute();
//				service.execute(model);
//				//수정페이지를 포워드한다. 
//				modePage = "07Board/edit";
//			} else if(mode.equals("delete"))
//			{
//				//삭제인 경우 게시물을 즉시 삭제한다. 
//				model.addAttribute("req", req);
//				service = new DeleteActionExecute();
//				service.execute(model);
//				
//				model.addAttribute("nowPage", req.getParameter("nowPage"));
//				modePage = "redirect:list.do";
//			}
//		}
//		return modePage;
//	}
	//패스워드 검증을 진행한 후 수정 혹은 삭제처리를 한다.(Javascript alert() 처리함^^)
	@RequestMapping("/board/passwordAction.do")
	public void passwordAction(Model model, HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		//폼값을 저장한 request객체를 모델객체에 저장한 후 서비스 객체를 호출한다. 
		model.addAttribute("req", req);
		service = this.passwordActionExecute;
		service.execute(model);
		
		//모델객체를 Map컬렉션으로 변환한다. 
		Map<String, Object> paramMap = model.asMap();
		//idx는 정수이므로 Integer로 형변환 후 변수에 저장한다. 
		int existIdx = (Integer)paramMap.get("existIdx");
		
		//전송된 파라미터를 받아온다.
		String mode = req.getParameter("mode");
		String idx = req.getParameter("idx");
		//패스워드 검증후 반환할 뷰의 경로를 저장할 변수 선언
		String modePage = null;
		if (existIdx<=0)
		{
			resp.sendRedirect("password.do?idx="+idx+"&mode=delete&nowPage=1");
		} else
		{
			/* 패스워드 검증이 완료되었다면 수정의 경우 수정페이지로
			포워드하고, 삭제의 경우에는 즉시 게시물을 삭제처리한다. 
			 */
//			System.out.println("검증완료");
			
			if (mode.equals("edit"))
			{
				//수정인 경우 서비스객체로 모델객체를 전달하고..
				model.addAttribute("req", req);
				service = this.editExecute;
				service.execute(model);

				resp.sendRedirect("edit.do?idx="+idx);
			} else if(mode.equals("delete"))
			{
				//삭제인 경우 게시물을 즉시 삭제한다. 
				model.addAttribute("req", req);
				service = this.deleteActionExecute;
				service.execute(model);
				
				String isDelete = model.getAttribute("isDelete").toString();
				if (isDelete.equals("X"))
				{
					resp.setContentType("text/html; charset=utf-8");
					PrintWriter pw = resp.getWriter();
					pw.print("<script>");
					pw.print("alert('삭제할수 없습니다.');");
					pw.print("history.go(-1)");
					pw.print("</script>");
					return;
				} else
				{
					resp.sendRedirect("list.do?nowPage="+req.getParameter("nowPagw"));
				}
			}
		}
	}
	
	//수정 처리
	@RequestMapping("/board/editAction.do")
	public String editAction(HttpServletRequest req, Model model,
			SpringBoardDTO dto)
	{
		//수정될 내용의 폼값은 커맨드객체를 통해 한번에 받는다. 
		model.addAttribute("req", req);
		model.addAttribute("SpringBoardDTO", dto);
		
		//서비스 객체 생성
		service = this.editActionExecute;
		service.execute(model);
		
		model.addAttribute("idx", req.getParameter("idx"));
		model.addAttribute("nowPage", req.getParameter("nowPage"));
		return "redirect:view.do?idx="+ dto.getIdx();
	}
	
	//답변글 쓰기폼에 대한 매핑
	@RequestMapping("/board/reply.do")
	public String reply(HttpServletRequest req, Model model)
	{
		//request내장객체를 모델객체에 저장한 후 서비스로 전달
		model.addAttribute("req", req);
		service = this.replyExecute;
		service.execute(model);
		
		//일련번호를 모델객체에 저장한 후 뷰 호출
		model.addAttribute("idx", req.getParameter("idx"));
		return "07Board/reply";
	}
	
	//답변글 쓰기 처리
	@RequestMapping("/board/replyAction.do")
	public String replyAction(HttpServletRequest req, Model model,
			SpringBoardDTO SpringBoardDTO)
	{
		//커맨드객체를 통해 폼값을 한꺼번에 받아서 처리한다.
		model.addAttribute("SpringBoardDTO", SpringBoardDTO);
		model.addAttribute("req", req);
		//서비스객체 생성 및 모델객체를 통해 요청을 전달한다.
		service = this.replyActionExecute;
		service.execute(model);
		
		//답변글을 작성한 후 리스트로 이동한다. 
		model.addAttribute("nowPage", req.getParameter("nowPage"));
		return "redirect:list.do";
	}
}
