package com.study.spring.common;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/*
폼값의 유효성 검증을 위한 클래스 정의를 위해 Validator인터페이스를 구현한다.
그리고 supports(), validate() 메서드를 오버라이딩 해야한다. 
 */
public class MemberValidator implements Validator
{
	/*
	필수 오버라이딩1 : 매개변수로 전달된 객체가 유효성검증을 지원할 수 있는
		커맨드객체인지 판단한다. 만약 해당 메서드를 통과하지 못하면 유효성
		검증을 위한 validate() 메서드는 아예 호출되지 않는다. 해당 
		메서드는 자동으로 호출된다. 즉, 직접 호출하지 않는다.
	 */
	@Override
	public boolean supports(Class<?> clazz)
	{
		return MemberDTO.class.isAssignableFrom(clazz);
	}
	
	/*
	필수 오버라이딩2 : supports() 메서드가 true를 반환하는 경우에만
		호출되는 메서드로 실제 폼값에 대한 검증을 진행한다. 
	매개변수1 : 폼값을 저장한 커맨드 객체
	매개변수2 : 에러정보를 저장할 Errors 타입의 변수 지정
	 */
	@Override
	public void validate(Object obj, Errors errors)
	{
		System.out.println("validate() 메소드 호출");
		
		//커맨드객체를 Object로 받았으므로 사용을 위해 형변환한다. 
		MemberDTO memberDTO = (MemberDTO)obj;
		
		//검증1 : 아이디가 빈값인지 확인한다. if문을 통해 빈값인지 확인한다.
		String member_id = memberDTO.getId();
		//아이디가 null이거나 빈값인 경우 에러처리를 한다. 
		if(member_id==null || member_id.trim().isEmpty())
		{
			System.out.println("아이디가 null입니다. TT");
			//검증에 실패한 경우 해당 메서드를 통해 검증결과를 반환한다.
			/*
			형식] rejectValue(검증할필드명, 에러객체명, 메세지) 와 같이 
			사용한다. 
			 */
			errors.rejectValue("id", "idError");
		}
		
		//검증2 : 내장메서드를 통해 패스워드가 빈값인지 검증한다. 
		/* 
		유효성 검증을 위해 전달되는 객체가 커맨드객체가 아니라면 validate()
		메서드 내부로 진입자체가 불가능하다. 해당 함수에서 pw라는 문자열은
		getter()를 통해 DTO에 저장된 패스워드를 얻어오게된다.  
		
		형식] rejectIfEmptyOrWhitespace(Errors객체, 검증할필드, 
			에러객체명, 메세지)
		*/
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pw", "pwError", "디폴트메세지(pw검증실패)");
		
		/*
		검증3 : 사용자정의 메서드를 통해 이름이 빈값인지 검증한다. 
		 */
		boolean nameValidate = myEmptyOrWhitespace(memberDTO.getName());
		if (nameValidate==false)
		{
			System.out.println("이름이 null입니다. ㅠㅠ");
			errors.rejectValue("name", "nameError");
		}
	}

	/*
	사용자 정의 메서드 : 값이 null이거나 길이가 0인경우 false를 반환한다.
		단순히 빈값에 대한 검증정도는 내장메서드를 사용하면 되겠지만
		이보다 복잡한 검증이 많으므로 사용자정의 메서드가 필요하다. 
	 */
	public boolean myEmptyOrWhitespace(String value)
	{
		if(value==null || value.trim().length()==0)
		{
			return false;
		} else 
		{
			return true;
		}
	}
	
}
