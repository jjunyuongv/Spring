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
	public String list(Model model, HttpServletRequest req) {
		
		model.addAttribute("req", req);
//		service = new ListExecute();
		service = this.listExecute;
		service.execute(model);
		
//		return "07Board/list";
		return "BoardSkin/listT";
	}
	
	@RequestMapping("/board/write.do")
	public String write(Model model) {
		
		return "BoardSkin/writeT";
	}
	
	@RequestMapping(value="/board/writeAction.do",
			method=RequestMethod.POST)
	public String writeAction(Model model, HttpServletRequest req,
			SpringBoardDTO SpringBoardDTO) {
		
		model.addAttribute("req", req);
		model.addAttribute("SpringBoardDTO", SpringBoardDTO);
		
		service = this.writeActionExecute;
		service.execute(model);
		
		return "redirect:list.do?nowPage=1";
	}
	
	@RequestMapping("/board/view.do")
	public String view(Model model, HttpServletRequest req) {
		
		model.addAttribute("req", req);
		service = this.viewExecute;
		service.execute(model);
		
		return "BoardSkin/viewT";
	}
	
	@RequestMapping("/board/password.do")
	public String password(Model model, HttpServletRequest req) {
		model.addAttribute("idx", req.getParameter("idx"));
		return "07Board/password";
	}
	
//	@RequestMapping("/board/passwordAction.do")
//	public String passwordAction(Model model, HttpServletRequest req) {
//		model.addAttribute("req", req);
//		service = new PasswordActionExecute();
//		service.execute(model);
//		
//		Map<String, Object> paramMap = model.asMap();
//		int existIdx = (Integer)paramMap.get("existIdx");
//		System.out.println("existIdx="+ existIdx);
//		
//		String mode = req.getParameter("mode");
//		String idx = req.getParameter("idx");
//		String modePage = null;
//		if(existIdx<=0) {
//			model.addAttribute("isCorrMsg", "패스워드가 일치하지 않습니다.");
//			model.addAttribute("idx", idx);
//			
//			modePage = "07Board/password";
//		}
//		else {
//			System.out.println("검증완료");
//			
//			if(mode.equals("edit")) {
//				model.addAttribute("req", req);
//				service = new EditExecute();
//				service.execute(model);
//				
//				modePage = "07Board/edit";
//			}
//			else if (mode.equals("delete")) {
//				model.addAttribute("req", req);
//				service = new DeleteActionExecute();
//				service.execute(model);
//				
//				model.addAttribute("nowPage", req.getParameter("nowPage"));
//				modePage = "redirect:list.do";
//			}
//		}
//		
//		return modePage;
//	}
	@RequestMapping("/board/passwordAction.do")
	public void passwordAction(Model model, HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		model.addAttribute("req", req);
		service = this.passwordActionExecute;
		service.execute(model);
		
		Map<String, Object> paramMap = model.asMap();
		int existIdx = (Integer)paramMap.get("existIdx");
		System.out.println("existIdx="+ existIdx);
		
		String mode = req.getParameter("mode");
		String idx = req.getParameter("idx");
		String modePage = null;
		if(existIdx<=0) {
			resp.sendRedirect("password.do?idx="+idx+"&mode=delete&nowPage=1");
		}
		else {
//			System.out.println("검증완료");
			
			if(mode.equals("edit")) {
				model.addAttribute("req", req);
				service = this.editExecute;
				service.execute(model);
				
				resp.sendRedirect("edit.do?idx="+idx);
			}
			else if (mode.equals("delete")) {
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
					resp.sendRedirect("list.do?nowPage="+req.getParameter("nowPage"));
				}
			}
		}
	}
	
	@RequestMapping("/board/editAction.do")
	public String editAction(HttpServletRequest req, Model model, SpringBoardDTO dto) {
		model.addAttribute("req", req);
		model.addAttribute("SpringBoardDTO", dto);
		
		service = this.editActionExecute;
		service.execute(model);
		
		model.addAttribute("idx", req.getParameter("idx"));
		model.addAttribute("nowPage", req.getParameter("nowPage"));
		return "redirect:view.do?idx="+ dto.getIdx();
	}
	
	@RequestMapping("/board/reply.do")
	public String reply(HttpServletRequest req, Model model) {
		
		model.addAttribute("req", req);
		service = this.replyExecute;
		service.execute(model);
		
		model.addAttribute("idx", req.getParameter("idx"));
		return "07Board/reply";
	}
	
	@RequestMapping("/board/replyAction.do")
	public String replyAction(HttpServletRequest req, Model model,
			SpringBoardDTO SpringBoardDTO) {
		model.addAttribute("SpringBoardDTO", SpringBoardDTO);
		model.addAttribute("req", req);
		service = this.replyActionExecute;
		service.execute(model);
		
		model.addAttribute("nowPage", req.getParameter("nowPage"));
		return "redirect:list.do";
		
	}
}
