package com.study.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.spring.transaction.TicketDTO;
import com.study.spring.transaction.TicketTplDAO;


@Controller
public class TransactionController {
 
	private TicketTplDAO daoTpl; 
	@Autowired
	public void setDaoTpl(TicketTplDAO daoTpl) {
		this.daoTpl = daoTpl;
	}
	//티켓 구매 페이지에 대한 매핑
	@RequestMapping("/transaction/buyTicketTpl.do")
	public String buyTicketTpl() {		
		return "08Transaction/buyTicketTpl";
	}
	
	//티켓 구매 처리
	@RequestMapping("/transaction/buyTicketTplAction.do")
	public String buyTicketTplAction(TicketDTO ticketDTO, 
			Model model)	{
		
		boolean isBool = daoTpl.buyTicket(ticketDTO);
		if(isBool==true) {
			model.addAttribute("successOrFail", "티켓구매가 정상처리 되었습니다");
		}
		else {
			model.addAttribute("successOrFail", "티켓구매가 취소 되었습니다. " + "다시 시도해 주세요");
		}
		
		model.addAttribute("ticketInfo", ticketDTO);
		
		return "08Transaction/buyTicketTplAction";
	}
	
	
}