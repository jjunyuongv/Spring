package com.study.spring.di;

//그래프 상에서 좌표값을 표현하는 클래스
public class Point
{
	//멤버변수
	public int x;
	public int y;
	
	//생성자 메서드만 정의
	public Point(int x, int y)
	{
		super();
		this.x = x;
		this.y = y;
		System.out.println("포인트 빈 생성");
	}

	/*
	객체의 참조변수를 그대로 출력하면 toString() 메서드에 오버라이딩 한
	내용이 출력된다. 즉, 객체를 문자열로 표현해준다. 
	 */
	@Override
	public String toString()
	{
		return String.format("(x, y)= %d, %d, ", x, y);
	}
}
