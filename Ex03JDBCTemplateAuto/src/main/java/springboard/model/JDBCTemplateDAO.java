package springboard.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCTemplateDAO
{
	/*
	멤버변수는 DB연결과 Spring-JDBC사용을 위해 선언한다. 
	컨트롤러에서 @Autowired를 통해 자동주입받은 빈을 정적변수인
	JdbcTemplateConst.template에 할당하였으므로, DB작업을 DAO
	에서도 수행할 수 있다. 
	 */
	@Autowired
	JdbcTemplate template;

	//생성자
	public JDBCTemplateDAO()
	{
		//Auto버전에서는 static타입의 변수가 필요없다. 자동주입 받기 때문이다. 
//		this.template = JdbcTemplateConst.template;
		System.out.println("JDBCTemplateDAO() 생성자 호출");
	}
	
	public void close() {
		//JDBCTemplate에서는 자원해제를 하지않는다. 
	}
	
	//게시물의 갯수 카운트 
	public int getTotalCount(Map<String, Object> map)
	{
		//count(*) 함수를 통해 게시물의 갯수를 카운트한다.
		String sql = "SELECT COUNT(*) FROM springboard ";
		//검색어가 있는 경우 where절을 추가한다. 
		if (map.get("Word")!=null)
		{
			sql += " WHERE " + map.get("Column") + " "
				+ "     LIKE '%"+map.get("Word")+"%' ";
		}
		System.out.println("sql="+sql);
		//쿼리문을 실행한 후 결과값을 정수형으로 반환한다. 
		return template.queryForObject(sql, Integer.class);
	}
	
	//게시판 리스트에 출력할 게시물을 인출한다.(페이지 처리 없음)
	public ArrayList<SpringBoardDTO> list(Map<String, Object> map)
	{
		//쿼리문 작성 및 검색어 처리
		String sql = "SELECT * FROM springboard ";
		if (map.get("Word")!=null)
		{
			sql += " WHERE " + map.get("Column") + " "
				+ "     LIKE '%"+map.get("Word")+"%' ";
		}
		//게시판 목록은 최근게시물이 위로 출력되야 하므로 내림차순 정렬
		//한 상태로 가져와야 한다. 
//		sql += " ORDER BY idx DESC";
		// 답글 있을 때 정렬
		sql += " ORDER BY bgroup DESC, bstep ASC ";
		
		return (ArrayList<SpringBoardDTO>)template.query(sql, 
				new BeanPropertyRowMapper<SpringBoardDTO>(SpringBoardDTO.class));
	}
	
	//글쓰기 처리
	public int write(SpringBoardDTO SpringBoardDTO)
	{		
		int result = template.update(new PreparedStatementCreator()
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException
			{
				//인파라미터가 있는 insert쿼리문 작성
				String sql = "INSERT INTO springboard ( "
						+ " idx, name, title, contents, hits, "
						+ " bgroup, bstep, bindent, pass)  "
						+ " VALUES ( "
						+ " springboard_seq.NEXTVAL, ?,?,?,0, "
						+ " springboard_seq.NEXTVAL,0,0,?) "; 
				
				//익명클래스 내부에서 prepared객체를 생성한다.
				PreparedStatement psmt = con.prepareStatement(sql);
				//쿼리문에 인파라미터를 설정한다. 
				psmt.setString(1, SpringBoardDTO.getName());
				psmt.setString(2, SpringBoardDTO.getTitle());
				psmt.setString(3, SpringBoardDTO.getContents());
				psmt.setString(4, SpringBoardDTO.getPass());
				return psmt;
			}
		});
		return result;
	}
	
	//내용보기시 조회수를 1증가 시킴.	
	public void updateHit(String idx)
	{
		//인파라미터가 있는 쿼리문을 작성한다. 
		String sql = "UPDATE springboard SET "
				+ " hits=hits+1 "
				+ " WHERE idx=? ";
		/*
		update() 메서드의 첫번째 인수로 쿼리문을 전달한다. 
		두번째 인수로 PreparedStatementSetter를 익명클래스로 정의
		한다. 오버라이딩 된 메서드를 이용해서 인파라미터를 설정한다. 
		 */
		template.update(sql, new PreparedStatementSetter()
		{
			@Override
			public void setValues(PreparedStatement ps) throws SQLException
			{
				ps.setInt(1, Integer.parseInt(idx));
			}
		});
	}
	
	//내용보기 : 일련번호에 해당하는 게시물을 인출
	public SpringBoardDTO view(String idx)
	{
		updateHit(idx);
		
		SpringBoardDTO dto = new SpringBoardDTO();
		String sql = "SELECT * FROM springboard "
				+ " WHERE idx=? ";
		
		try
		{
			dto = template.queryForObject(sql, 
					new BeanPropertyRowMapper<SpringBoardDTO>(SpringBoardDTO.class),
					new Object[] {idx});
		} catch (Exception e)
		{
			System.out.println("View()실행시 예외발생");
		}
		return dto;
	}
	
	//패스워드 검증 : 일련번호와 패스워드를 통해 검증한다. 
	public int password(String idx, String pass)
	{
		int resultIdx = 0;
		String sql = "SELECT * FROM springboard "
				+ " WHERE pass=? AND idx=? ";
		System.out.println(sql);
		try
		{
			SpringBoardDTO dto = template.queryForObject(sql, 
					new BeanPropertyRowMapper<SpringBoardDTO>(SpringBoardDTO.class),
					new Object[] {pass, idx});
			resultIdx = dto.getIdx();
		} catch (Exception e)
		{
			System.out.println("password() 예외 발생");
		}
		
		return resultIdx;
	}
	
	//수정 처리
	public void edit(SpringBoardDTO dto)
	{		 
		//인파라미터가 있는 update 쿼리문 작성
		String sql = "UPDATE springboard "
			+ " SET name=?, title=?, contents=?"
			+ " WHERE idx=? AND pass=?";
		template.update(sql, 
			new Object[] {dto.getName(), dto.getTitle(), 
				dto.getContents(), dto.getIdx(), dto.getPass()});
	}
	
	//삭제처리
	public void delete(String idx, String pass)
	{
		//인파라미터가 있는 delete 쿼리문 작성
		String sql = "DELETE FROM springboard "
				+ " WHERE idx=? AND pass=? ";
		template.update(sql, new PreparedStatementSetter()
		{
			@Override
			public void setValues(PreparedStatement ps) throws SQLException
			{
				ps.setString(1, idx);
				ps.setString(2, pass);
			}
		});
	}
	
	public boolean deleteConfirm(String idx)
	{
		//삭제할 게시물의 레코드를 확인한다.
		SpringBoardDTO dto = view(idx);
		//답변글이 있는지 확인한다. 
		String sql = "SELECT COUNT(*) FROM springboard "
				+ " WHERE bgroup=? AND bstep>? ";
		//인파라미터를 추가하고 쿼리문을 실행한다. 
		int replyCount = template.queryForObject(sql, Integer.class,
				new Object[] {dto.getBgroup(), dto.getBstep()});
		if(replyCount==0)
		{
			//답변글이 없으면 true
			return true;
		} else 
		{
			//답변글이 있으면 false
			return false;
		}
	}
	
	//답변글 쓰기 처리
	public void reply(final SpringBoardDTO dto)
	{
		//답변글 추가전에 bstep(그룹내의 정렬)을 일괄적으로 업데이트한다.
		replyPrevUpdate(dto.getBgroup(), dto.getBstep());

		String sql = "INSERT INTO springboard "
				+ " (idx, name, title, contents, pass, "
				+ " bgroup, bstep, bindent) "
				+ " VALUES "
				+ " (springboard_seq.nextval, ?, ?, ?, ?, "
				+ " ?, ?, ?) ";
		
		template.update(sql, new PreparedStatementSetter()
		{
			@Override
			public void setValues(PreparedStatement ps) throws SQLException
			{
				ps.setString(1, dto.getName());
				ps.setString(2, dto.getTitle());
				ps.setString(3, dto.getContents());
				ps.setString(4, dto.getPass());
				ps.setInt(5, dto.getBgroup());
				ps.setInt(6, dto.getBstep()+1);
				ps.setInt(7, dto.getBindent()+1);
			}
		});
	}
	
	public void replyPrevUpdate(int bGroup, int bStep)
	{
		String sql = "UPDATE springboard SET bstep=bstep+1 "
				+ " WHERE bgroup=? AND bstep>? ";
		template.update(sql, new Object[] {bGroup, bStep});
	}
	
	// listPage() 메소드 추가(페이징 처리 있음.)
	public ArrayList<SpringBoardDTO> listPage(Map<String, Object> map)
	{	
		int start = Integer.parseInt(map.get("start").toString());
		int end = Integer.parseInt(map.get("end").toString());
		
		String sql = ""
				+"SELECT * FROM ("
				+"    SELECT Tb.*, rownum rNum FROM ("
				+"        SELECT * FROM springboard ";				
			if(map.get("Word")!=null){
				sql +=" WHERE "+map.get("Column")+" "
					+ " LIKE '%"+map.get("Word")+"%' ";				
			}			
			sql += " ORDER BY bgroup DESC, bstep ASC"
			+"    ) Tb"
			+")"
			+" WHERE rNum BETWEEN "+start+" and "+end;
		
		return (ArrayList<SpringBoardDTO>)
			template.query(sql, 
				new BeanPropertyRowMapper<SpringBoardDTO>(SpringBoardDTO.class));
	}
}
