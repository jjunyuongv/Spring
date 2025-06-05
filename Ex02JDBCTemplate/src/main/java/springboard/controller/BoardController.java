package springboard.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import springboard.model.JdbcTemplateConst;
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
	private JdbcTemplate template;
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		System.out.println("@Autowired=>JdbcTemplate 연결성공");
		
		JdbcTemplateConst.template = this.template;
	}
	IBoardService service = null;
	
	@RequestMapping("/board/list.do")
	public String list(Model model, HttpServletRequest req) {
		
		model.addAttribute("req", req);
		service = new ListExecute();
		service.execute(model);
		
		return "07Board/list";
	}
	
	@RequestMapping("/board/write.do")
	public String write(Model model) {
		
		return "07Board/write";
	}
	
	@RequestMapping(value="/board/writeAction.do",
			method=RequestMethod.POST)
	public String writeAction(Model model, HttpServletRequest req,
			SpringBoardDTO SpringBoardDTO) {
		
		model.addAttribute("req", req);
		model.addAttribute("SpringBoardDTO", SpringBoardDTO);
		
		service = new WriteActionExecute();
		service.execute(model);
		
		return "redirect:list.do?nowPage=1";
	}
	
	@RequestMapping("/board/view.do")
	public String view(Model model, HttpServletRequest req) {
		
		model.addAttribute("req", req);
		service = new ViewExecute();
		service.execute(model);
		
		return "07Board/view";
	}
	
	@RequestMapping("/board/password.do")
	public String password(Model model, HttpServletRequest req) {
		model.addAttribute("idx", req.getParameter("idx"));
		return "07Board/password";
	}
	
	@RequestMapping("/board/passwordAction.do")
	public String passwordAction(Model model, HttpServletRequest req) {
		model.addAttribute("req", req);
		service = new PasswordActionExecute();
		service.execute(model);
		
		Map<String, Object> paramMap = model.asMap();
		int existIdx = (Integer)paramMap.get("existIdx");
		System.out.println("existIdx="+ existIdx);
		
		String mode = req.getParameter("mode");
		String idx = req.getParameter("idx");
		String modePage = null;
		if(existIdx<=0) {
			model.addAttribute("isCorrMsg", "패스워드가 일치하지 않습니다.");
			model.addAttribute("idx", idx);
			
			modePage = "07Board/password";
		}
		else {
			System.out.println("검증완료");
			
			if(mode.equals("edit")) {
				model.addAttribute("req", req);
				service = new EditExecute();
				service.execute(model);
				
				modePage = "07Board/edit";
			}
			else if (mode.equals("delete")) {
				model.addAttribute("req", req);
				service = new DeleteActionExecute();
				service.execute(model);
				
				model.addAttribute("nowPage", req.getParameter("nowPage"));
				modePage = "redirect:list.do";
			}
		}
		
		return modePage;
	}
	
	@RequestMapping("/board/editAction.do")
	public String editAction(HttpServletRequest req, Model model, SpringBoardDTO dto) {
		model.addAttribute("req", req);
		model.addAttribute("SpringBoardDTO", dto);
		
		service = new EditActionExecute();
		service.execute(model);
		
		model.addAttribute("idx", req.getParameter("idx"));
		model.addAttribute("nowPage", req.getParameter("nowPage"));
		return "redirect:view.do?idx="+ dto.getIdx();
	}
	
	@RequestMapping("/board/reply.do")
	public String reply(HttpServletRequest req, Model model) {
		
		model.addAttribute("req", req);
		service = new ReplyExecute();
		service.execute(model);
		
		model.addAttribute("idx", req.getParameter("idx"));
		return "07Board/reply";
	}
	
	@RequestMapping("/board/replyAction.do")
	public String replyAction(HttpServletRequest req, Model model,
			SpringBoardDTO SpringBoardDTO) {
		model.addAttribute("SpringBoardDTO", SpringBoardDTO);
		model.addAttribute("req", req);
		service = new ReplyActionExecute();
		service.execute(model);
		
		model.addAttribute("nowPage", req.getParameter("nowPage"));
		return "redirect:list.do";
		
	}
}
