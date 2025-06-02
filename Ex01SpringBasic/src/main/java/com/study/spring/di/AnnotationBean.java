package com.study.spring.di;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
@Configuration
	: 해당 클래스를 스프링 설정파일로 사용하겠다는 선언으로 
	XML설정 파일을 사용하는 대신 Java파일을 설정파일로 사용한다. 
 */
@Configuration
public class AnnotationBean
{
	/*
	@Bean
		: <bean> 엘리먼트와 같이 자바빈을 생성할때 선언한다. 
		생성된 빈은 컨트롤러에서 getBean()메서드를 통해 주입받을수 있다. 
		메서드명은 주입받을때의 참조변수명으로 사용한다. 
		즉 Circle circleBean = new Circle()와 동일하다.
	 */
	@Bean
	public Circle circleBean()
	{
		Point point2 = new Point(7, 8);
		Circle circle2 = new Circle();
		circle2.setPoint(point2);
		circle2.setRadian(20);
		
		return circle2;
	}
	
	@Bean
	public Person personBean()
	{
		Person person2 = new Person();
		person2.setName("홍길동");
		person2.setAge(10);
		
		ArrayList<String> hobbys = new ArrayList<String>();
		hobbys.add("기타");
		hobbys.add("칼림바");
		hobbys.add("피아노");
		person2.setHobbys(hobbys);
		
		return person2;
	}
}
