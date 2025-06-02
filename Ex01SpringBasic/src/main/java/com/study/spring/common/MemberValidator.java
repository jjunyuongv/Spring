package com.study.spring.common;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class MemberValidator implements Validator
{

	@Override
	public boolean supports(Class<?> clazz)
	{
		return MemberDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors)
	{
		System.out.println("validate() 메소드 호출됨");
		
		MemberDTO memberDTO = (MemberDTO)obj;
		
		String member_id = memberDTO.getId();
		if(member_id == null || member_id.trim().isEmpty()) {
			System.out.println("아이디가 null입니다. ㅠㅠ");
			errors.rejectValue("id", "idError");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pw", "pwError" );
		
		boolean nameValidate = 
		myEmptyOrWhitespace(memberDTO.getName());
		if(nameValidate == false) {
			System.out.println("이름이 null입니다. ㅠㅠ");
			errors.rejectValue("name", "nameError");
		}
	}
	
	public boolean myEmptyOrWhitespace(String value)
	{
		if(value == null || value.trim().length()==0) {
			return false;
		}
		else {
			return true;
		}
	}
}
