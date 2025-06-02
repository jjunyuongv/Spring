package com.study.spring.env;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class BoardVO implements InitializingBean, DisposableBean
{


	@Override
	public void afterPropertiesSet() throws Exception
	{
		System.out.println("BoardVO => afterPropertiesSet() 호출됨");
		
	}
	
	@Override
	public void destroy() throws Exception
	{
		System.out.println("BoardVO => destroy() 호출됨");
		
	}
	
	private String pageSize;
	private String blockSize;
	public String getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(String pageSize)
	{
		this.pageSize = pageSize;
	}

	public String getBlockSize()
	{
		return blockSize;
	}

	public void setBlockSize(String blockSize)
	{
		this.blockSize = blockSize;
	}
	
}
