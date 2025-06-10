package com.study.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.spring.mybatis.MyBoardDTO;
import com.study.spring.mybatis.ServiceMyBoard;

@Controller
public class CollectionController
{
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/Collection/hashMap.do")
	public String hashMap(Model model) {
		Map<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("key_id", "musthave");
		hashMap.put("key_name", "스모");
		hashMap.put("key_contents", "마왕족발");
		
		ArrayList<MyBoardDTO> lists =
				sqlSession.getMapper(ServiceMyBoard.class)
					.hashMapUse(hashMap);
		
		String sql = sqlSession.getConfiguration()
				.getMappedStatement("hashMapUse")
					.getBoundSql(hashMap).getSql();
		System.out.println("sql="+sql);
		
		model.addAttribute("lists", lists);
		return "08CollectionUse/hashMapUse";
	}
}
