package com.study.spring.env;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

//해당 어노테이션으로 XML설정파일의 역할을 대신하는 클래스로 정의한다.
@Configuration
public class EnvApplicationConfig
{
	/*
	멤버변수 : 프로퍼티소스에서 읽어온 설정값을 @Value 어노테이션을
		통해 변수에 할당한다. 
	*/
	@Value("${board.size_per_page}")
	private String pageSize;
	
	@Value("${board.page_per_block}")
	private String blockSize;
	
	//외부파일을 읽어온다. 
	@Bean
	public static PropertySourcesPlaceholderConfigurer Properties()
	{
		//프로퍼티소스 파일을 읽기 위한 객체생성
		PropertySourcesPlaceholderConfigurer config = new 
				PropertySourcesPlaceholderConfigurer();
		
		/* 프로퍼티 파일의 위치를 설정하기 위해 Resource타입의 객체
		배열을 생성한다. 만약 파일이 2개라면 크기는 2로 지정하면된다. */
		Resource[] locations = new Resource[1];
		/* classpath로 지정된 resources 폴더를 지정하여 프로퍼티
		파일의 위치를 설정한다. 
		classpath:xxxx.xml과 동일한 설정이다. 
		*/ 
		locations[0] = new ClassPathResource("EnvBoard.properties");
		/* 설정된 위치를 config 참조변수로 전달하여 파일을 읽어온다. */
		config.setLocations(locations);
		
		return config;
	}
	
	//프로퍼티 소스를 통해 읽어온 값을 통해 빈을 생성한다. 
	//함수명이 id속성의 역할을 한다. 즉 boardVOFunc라는 빈을 생성한다.
	@Bean
	public BoardVO boardVOFunc() {
		BoardVO boardVO = new BoardVO();
		boardVO.setPageSize(pageSize);
		boardVO.setBlockSize(blockSize);
		return boardVO;
	}
}
