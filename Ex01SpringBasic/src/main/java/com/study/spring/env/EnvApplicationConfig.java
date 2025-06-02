package com.study.spring.env;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class EnvApplicationConfig
{
	@Value("${board.size_per_page}")
	private String pageSize;
	
	@Value("${board.page_per_block}")
	private String blockSize;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer Properties() {
		PropertySourcesPlaceholderConfigurer config = new
			PropertySourcesPlaceholderConfigurer();
		Resource[] locations = new Resource[1];
		locations[0] = new ClassPathResource("EnvBoard.properties");
		config.setLocations(locations);
		return config;
	}
	
	@Bean
	public BoardVO boardVOFunc() {
		BoardVO boardVO = new BoardVO();
		boardVO.setPageSize(pageSize);
		boardVO.setBlockSize(blockSize);
		return boardVO;
	}
}
