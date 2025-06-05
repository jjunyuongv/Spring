package springboard.model;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

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
}
