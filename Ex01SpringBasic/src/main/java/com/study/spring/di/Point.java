package com.study.spring.di;

public class Point
{
	public int x;
	public int y;
	
	public Point(int x, int y)
	{
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString()
	{
		return String.format("(x, y)=%d, ", x, y);
	}
	
	
	
	
}
