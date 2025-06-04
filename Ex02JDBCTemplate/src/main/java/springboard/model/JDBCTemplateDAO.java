package springboard.model;

import java.util.Map;

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
	
//	public int getTotalCount(Map<String, Object> map) {
//		String sql = "SELECT COUNT(*) FROM springboard ";
//		if(map.get("Word")!=null) {
//			
//		}
//	}
}
