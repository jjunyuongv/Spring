package springboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import springboard.model.JdbcTemplateConst;
import springboard.service.IBoardService;
import springboard.service.ListExecute;

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
	
	
	
}
