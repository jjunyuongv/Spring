package springboard.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

public class JDBCTemplateDAO
{
	JdbcTemplate template;
	public JDBCTemplateDAO() {
		this.template = JdbcTemplateConst.template;
		System.out.println("JDBCTemplateDAO() 생성자 호출");
	}
	
	public void close() {
		
	}
	
	public int getTotalCount(Map<String, Object> map) {
		String sql = "SELECT COUNT(*) FROM springboard ";
		if(map.get("Word")!=null) {
			sql +=" WHERE "+map.get("Column")+" "
				+ "		LIKE '%"+map.get("Word")+"%' ";
		}
		System.out.println("sql="+sql);
		return template.queryForObject(sql, Integer.class);
	}
	
	public ArrayList<SpringBoardDTO> list(Map<String, Object> map){
		String sql = "SELECT * FROM springboard ";
		if(map.get("Word")!=null) {
			sql +=" WHERE "+map.get("Column")+" "
				+ " LIKE '%"+map.get("Word")+"%' ";
		}
		sql += " ORDER BY idx DESC";
		return (ArrayList<SpringBoardDTO>)template.query(sql, new BeanPropertyRowMapper<SpringBoardDTO>(SpringBoardDTO.class));
	}
	
	public int write(SpringBoardDTO SpringBoardDTO) {
		
		int result = template.update(new PreparedStatementCreator()
		{
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException
			{
				String sql = "INSERT INTO springboard ("
					+ " idx, name, title, contents, hits "
					+ " ,bgroup, bstep, bindent, pass) "
					+ " VALUES ("
					+ " springboard_seq.NEXTVAL,?,?,?,0,"
					+ " springboard_seq.NEXTVAL,0,0,?)";
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setString(1, SpringBoardDTO.getName());
				psmt.setString(2, SpringBoardDTO.getTitle());
				psmt.setString(3, SpringBoardDTO.getContents());
				psmt.setString(4, SpringBoardDTO.getPass());
				return psmt;
			}
		});
		return result;
	}
	
	public void updateHit(String idx) {
		String sql = "UPDATE springboard SET "
			+ " hits=hits+1 "
			+ " WHERE idx=? ";
		template.update(sql, new PreparedStatementSetter()
		{
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException
			{
				ps.setInt(1, Integer.parseInt(idx));
			}
		});
	}

	public SpringBoardDTO view(String idx) {
		updateHit(idx);
		
		SpringBoardDTO dto = new SpringBoardDTO();
		String sql = "SELECT * FROM springboard "
				+ " WHERE idx=?";
		try
		{
			dto = template.queryForObject(sql,
				new BeanPropertyRowMapper<SpringBoardDTO>(SpringBoardDTO.class),
				new Object[] {idx});
		} catch (Exception e)
		{
			System.out.println("View() 실행시 예외발생");
		}
		return dto;
	}
	
}


