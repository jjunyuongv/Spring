package com.study.spring.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TicketDAO
{
	JdbcTemplate template;
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	PlatformTransactionManager transactionManager;
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	public TicketDAO() {
		System.out.println("TicketDAO 생성자호출:"+ template);
	}
	
	public void buyTicket(final TicketDTO dto) {
		
		System.out.println("buyTicket()메소드 호출");
		System.out.println(dto.getUserid() +"님이 "
				+ "티켓 "+ dto.getAmount() +"장을 "
				+ "구매합니다.");
		
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try
		{
			template.update(new PreparedStatementCreator()
			{
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException
				{
					String query = "INSERT INTO "
						+ "transaction_pay (customerId, amount)"
						+ "VALUES (?, ?)";
					PreparedStatement psmt =
						con.prepareStatement(query);
					psmt.setString(1, dto.getUserid());
					psmt.setInt(2, dto.getAmount()*10000);
					return psmt;
				}
			});
			template.update(new PreparedStatementCreator()
			{
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException
				{
					String query = "INSERT INTO "
						+ "transaction_ticket (customerId, "
						+ "countNum) VALUES (?, ?)";
					PreparedStatement psmt =
						con.prepareStatement(query);
					psmt.setString(1, dto.getUserid());
					psmt.setInt(2, dto.getAmount());
					return psmt;
				}
			});
			System.out.println("카드결재와 티켓구매 모두"+ "정상처리 되었습니다.");
			transactionManager.commit(status);
		} catch (Exception e)
		{
			System.out.println("제약조건을 위배하여 "
				+ "카드결재와 티켓구매 모두가 취소"
				+ "되었습니다.");
			transactionManager.rollback(status);
		}
	}
}
