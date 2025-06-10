package com.study.spring.mybatis;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/*
해당 인터페이스는 컨트롤러와 DAO 사이에서 매개역할을 하는 서비스객체로
정의된다. @Service 어노테이션은 빈을 자동으로 생성하기 위한 용도이지만
Mybatis에서의 서비스객체는 단순한 매개역할만 하므로 자바빈은 생성할 필요가
없다. 단지 서비스객체의 역할이라는 정도로만 어노테이션을 부착한다. 
 */
@Service
public interface ServiceMyBoard
{
	/*
	방명록 테이블에서 게시물의 갯수를 카운트하여 정수로 반환한다. 
	별도의 매개변수가 없으므로 항상 전체 레코드수를 반환하게된다.
	 */
	public int getTotalCount();
	/*
	매개변수로 전달된 start, end를 통해 출력할 게시물을 인출한다. 
	인출된 게시물은 List컬렉션으로 반환된다. 
	 */
	public ArrayList<MyBoardDTO> listPage(int s, int e);
	
	public int write(@Param("_name") String name,
			@Param("_contents") String contents,
			@Param("_id") String id);
	
	public MyBoardDTO view(ParameterDTO parameterDTO);
	
	public int modify(MyBoardDTO myBoardDTO);
	
	public int delete(String idx, String id);
}
