package com.study.spring.di;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnotationBean
{
	@Bean
	public Circle circleBean() {
		Point point2 = new Point(7, 8);
		Circle circle2 = new Circle();
		circle2.setPoint(point2);
		circle2.setRadian(20);
		
		return circle2;
	}
	
	@Bean
	public Person personBean() {
		Person person2 = new Person();
		person2.setName("유겸");
		person2.setAge(10);
		
		ArrayList<String> hobbys = new ArrayList<String>();
		hobbys.add("기타");
		hobbys.add("칼림바");
		hobbys.add("피아노");
		person2.setHobbys(hobbys);
		
		return person2;
	}
}
